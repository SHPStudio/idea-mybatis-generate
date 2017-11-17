package db.mysql;

import com.alibaba.fastjson.JSON;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.Map;

/**
 * PACKAGE_NAME
 * Created by ASUS on 2017/7/3.
 * 15:01
 */
public class GenUtils {
    private static String outPath="D:/template";
    private static String configPath="src/main/resources/template/";

    public static String getOutPath() {
        return outPath;
    }

    public static void setOutPath(String outPath) {
        GenUtils.outPath = outPath;
    }

    public static String getConfigPath() {
        return configPath;
    }

    public static void setConfigPath(String configPath) {
        GenUtils.configPath = configPath;
    }

    public static void genJava(Map<String,Object> root){
        try {
            Map<String,Object> data= copy(root);
            data.put("fileName",data.get("className")+".java");
            gen(data,"java.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
    public static void genMapper(Map<String,Object> root){
        try {
            Map<String,Object> data=copy(root);
            data.put("fileName",data.get("className")+"Mapper.java");
            gen(data,"Mapper.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private static void gen(Map<String ,Object> root,String templateName) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(configPath));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template temp = cfg.getTemplate(templateName);

        // Create the root hash

        File dir = new File(outPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        OutputStream fos = new FileOutputStream( new File(dir, String.valueOf(root.get("fileName")))); //java文件的生成目录
        Writer out = new OutputStreamWriter(fos,"UTF-8");
        temp.process(root, out);

        fos.flush();
        fos.close();
        System.out.println("gen code success!");
    }


    private static Map<String,Object> copy(Map<String,Object> source){
        return JSON.parseObject(JSON.toJSONString(source));
    }
}
