package db.mysql;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.swing.*;
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

    public static void genrate(Map<String, Object> root) throws IOException, TemplateException {
        //是否读写分离
        if (!RuntimeEnv.pp.isSperateRead()) {
            gen(RuntimeEnv.pp.getModelOutPath(), root, "java.ftl", RuntimeEnv.pp.getClassName() + ".java", RuntimeEnv.pp.isOverwrite());
            gen(RuntimeEnv.pp.getMapperOutPath(), root, "Mapper.ftl", RuntimeEnv.pp.getMapperName() + ".java", RuntimeEnv.pp.isOverwrite());
            gen(RuntimeEnv.pp.getMapperXmlOutPath(), root, "MapperXml.ftl", RuntimeEnv.pp.getMapperXmlName() + ".xml", RuntimeEnv.pp.isOverwrite());
        }
        else {
            gen(RuntimeEnv.pp.getModelOutPath(), root, "java.ftl", RuntimeEnv.pp.getClassName() + ".java", RuntimeEnv.pp.isOverwrite());
            gen(RuntimeEnv.pp.getMapperOutPath()+"/read", root, "ReadMapper.ftl", "Read"+RuntimeEnv.pp.getMapperName() + ".java", RuntimeEnv.pp.isOverwrite());
            gen(RuntimeEnv.pp.getMapperOutPath()+"/write", root, "WriteMapper.ftl", "Write"+RuntimeEnv.pp.getMapperName() + ".java", RuntimeEnv.pp.isOverwrite());
            gen(RuntimeEnv.pp.getMapperXmlOutPath()+"/read", root, "ReadMapperXml.ftl", "Read"+RuntimeEnv.pp.getMapperXmlName() + ".xml", RuntimeEnv.pp.isOverwrite());
            gen(RuntimeEnv.pp.getMapperXmlOutPath()+"/write", root, "WriteMapperXml.ftl", "Write"+RuntimeEnv.pp.getMapperXmlName() + ".xml", RuntimeEnv.pp.isOverwrite());

        }
    }
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void gen(String outPath, Map<String, Object> root, String templateName, String fileName, boolean overwrite) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(MysqlGenUtils.class,"/template");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template temp = cfg.getTemplate(templateName);

        // Create the root hash

        File dir = new File(outPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, fileName);
        int isProduce;
        if (file.exists()){
            isProduce =JOptionPane.showConfirmDialog(null,fileName +"文件已存在，是否生成","提示",JOptionPane.YES_NO_OPTION);
        }else {
            isProduce = 0;
        }
        if (isProduce == 0) {
            if (!file.exists() || overwrite) {
                OutputStream fos = new FileOutputStream(new File(dir, fileName)); //文件的生成目录
                Writer out = new OutputStreamWriter(fos,"UTF-8");
                temp.process(root, out);
                fos.flush();
                fos.close();
                System.out.println(fileName + "gen code success!");
            } else {
                replaceOldContent(root, dir, fileName, temp);
            }
        }
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored"})
    private static void replaceOldContent(Map<String, Object> root, File dir, String fileName, Template temp) {
        String tempFileStr = fileName + "temp";
        String midFileStr = fileName + "mid";
        OutputStream fos = null;
        OutputStreamWriter out = null;
        File tempFile = new File(dir, tempFileStr);
        File oldFile = new File(dir, fileName);
        File midFile = new File(dir, midFileStr);
        BufferedReader oldFileReader = null;
        BufferedReader newFileReader = null;
        OutputStreamWriter midFileWrite = null;

        try {
            //先生成临时目录
            fos = new FileOutputStream(tempFile);
            out = new OutputStreamWriter(fos,"UTF-8");
            temp.process(root, out);
            fos.flush();
            out.close();
            fos.close();

            //再读取存在的文件
            oldFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(oldFile),"UTF-8"));
            midFileWrite = new OutputStreamWriter(new FileOutputStream(midFile),"UTF-8");
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
            newFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(tempFile),"UTF-8"));
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

            BufferedWriter newFileWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(oldFile),"UTF-8"));
            BufferedReader midFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(midFile),"UTF-8"));

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
