package db.mysql;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * db.mysql
 * Created by ASUS on 2017/7/16.
 * 11:01
 */
public class MysqlGenUtils {
    private static String lineSeparator = System.getProperty("line.separator", "\n");

    private static String configPath = "src/main/resources/template/";

    private static final Pattern stFind = Pattern.compile("生成代码开始"), edFind = Pattern.compile("生成代码结束");

    public static void genrate(Map<String, Object> root) {
        try {
            gen(RuntimeEnv.pp.getModelOutPath(), root, "java.ftl", RuntimeEnv.pp.getClassName() + ".java", RuntimeEnv.pp.isOverwrite());
            gen(RuntimeEnv.pp.getMapperOutPath(), root, "Mapper.ftl", RuntimeEnv.pp.getMapperName() + ".java", RuntimeEnv.pp.isOverwrite());
            gen(RuntimeEnv.pp.getMapperXmlOutPath(), root, "MapperXml.ftl", RuntimeEnv.pp.getMapperXmlName() + ".xml", RuntimeEnv.pp.isOverwrite());
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void gen(String outPath, Map<String, Object> root, String templateName, String fileName, boolean overwrite) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(configPath));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template temp = cfg.getTemplate(templateName);

        // Create the root hash

        File dir = new File(outPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, fileName);
        if (!file.exists() || overwrite) {
            OutputStream fos = new FileOutputStream(new File(dir, fileName)); //文件的生成目录
            Writer out = new OutputStreamWriter(fos);
            temp.process(root, out);
            fos.flush();
            fos.close();
            System.out.println(fileName + "gen code success!");
        } else {
            replaceOldContent(root, dir, fileName, temp);
        }
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored"})
    private static void replaceOldContent(Map<String, Object> root, File dir, String fileName, Template temp) {
        String tempFileStr = fileName + "temp";
        String midFileStr = fileName + "mid";
        OutputStream fos = null;
        Writer out = null;
        File tempFile = new File(dir, tempFileStr);
        File oldFile = new File(dir, fileName);
        File midFile = new File(dir, midFileStr);
        BufferedReader oldFileReader = null;
        BufferedReader newFileReader = null;
        FileWriter midFileWrite = null;

        try {
            //先生成临时目录
            fos = new FileOutputStream(tempFile);
            out = new OutputStreamWriter(fos);
            temp.process(root, out);
            fos.flush();
            out.close();
            fos.close();

            //再读取存在的文件
            oldFileReader = new BufferedReader(new FileReader(oldFile));
            midFileWrite = new FileWriter(midFile);
            String line = null;
            boolean st = false, ed = false;

            while ((line = oldFileReader.readLine()) != null) {
                midFileWrite.write(line + lineSeparator);
                if (stFind.matcher(line).find()) {
                    st = true;
                    break;
                }
            }
            if (st = false) {
                throw new IllegalAccessException("未找到代码起始段");
            }
            //找到生成文件的起始段
            newFileReader = new BufferedReader(new FileReader(tempFile));
            while ((line = newFileReader.readLine()) != null) {
                if (stFind.matcher(line).find()) {
                    break;
                }
            }
            //輸入生成文件段
            while ((line = newFileReader.readLine()) != null) {
                midFileWrite.write(line + lineSeparator);
                if (edFind.matcher(line).find()) {
                    break;
                }
            }

            while ((line = newFileReader.readLine()) != null) {
                midFileWrite.write(line + lineSeparator);
            }
            newFileReader.close();
            oldFileReader.close();
            midFileWrite.close();

            BufferedWriter newFileWrite = new BufferedWriter(new FileWriter(oldFile));
            BufferedReader midFileReader = new BufferedReader(new FileReader(midFile));

            while ((line = midFileReader.readLine()) != null) {
                newFileWrite.write(line + lineSeparator);
            }
            newFileWrite.close();
            midFileReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //删除中间文件
                if (midFile.exists()) {
                    midFile.delete();
                }
                if (tempFile.exists()) {
                    tempFile.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
