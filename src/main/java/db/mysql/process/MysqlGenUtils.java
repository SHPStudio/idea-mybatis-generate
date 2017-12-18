package db.mysql.process;

import db.mysql.env.RuntimeEnv;
import db.mysql.model.GenrateParamReq;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.swing.*;
import java.io.*;
import java.util.Map;
import java.util.regex.Pattern;
import db.mysql.env.Constants;

/**
 * db.mysql
 * Created by ASUS on 2017/7/16.
 * 11:01
 */
public class MysqlGenUtils {

    public static void genrate(Map<String, Object> root) throws IOException, TemplateException {
        //是否读写分离
        if (!RuntimeEnv.pp.isSperateRead()) {
            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                .withOutPath(RuntimeEnv.pp.getModelOutPath())
                .withFileName(RuntimeEnv.pp.getClassName()+Constants.Java_Type_Suffix)
                .withTemplateName("java.ftl")
                .withTemplateParam(root)
                .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperOutPath())
                    .withFileName(RuntimeEnv.pp.getClassName()+Constants.Mapper_Suffix+Constants.Java_Type_Suffix)
                    .withTemplateName("Mapper.ftl")
                    .withTemplateParam(root)
                    .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperXmlOutPath())
                    .withFileName(RuntimeEnv.pp.getClassName()+Constants.Mapper_Suffix+Constants.Xml_Type_Suffix)
                    .withTemplateName("MapperXml.ftl")
                    .withTemplateParam(root)
                    .build());

        }
        else {
            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getModelOutPath())
                    .withFileName(RuntimeEnv.pp.getClassName()+Constants.Java_Type_Suffix)
                    .withTemplateName("java.ftl")
                    .withTemplateParam(root)
                    .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperOutPath()+Constants.Read_Path_Prefix)
                    .withFileName(RuntimeEnv.pp.getMapperName()+Constants.Read_Suffix+Constants.Java_Type_Suffix)
                    .withTemplateName("ReadMapper.ftl")
                    .withTemplateParam(root)
                    .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperOutPath()+Constants.Write_Path_Prefix)
                    .withFileName(RuntimeEnv.pp.getMapperName()+Constants.Write_Suffix+Constants.Java_Type_Suffix)
                    .withTemplateName("WriteMapper.ftl")
                    .withTemplateParam(root)
                    .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperXmlOutPath()+Constants.Read_Path_Prefix)
                    .withFileName(RuntimeEnv.pp.getMapperXmlName()+Constants.Read_Suffix+Constants.Xml_Type_Suffix)
                    .withTemplateName("ReadMapperXml.ftl")
                    .withTemplateParam(root)
                    .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperXmlOutPath()+Constants.Write_Path_Prefix)
                    .withFileName(RuntimeEnv.pp.getMapperXmlName()+Constants.Write_Suffix+Constants.Xml_Type_Suffix)
                    .withTemplateName("WriteMapperXml.ftl")
                    .withTemplateParam(root)
                    .build());

        }
    }
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void gen(GenrateParamReq req) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(MysqlGenUtils.class,"/template");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template temp = cfg.getTemplate(req.getTemplateName());

        // Create the root hash

        File dir = new File(req.getOutPath());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, req.getFileName());
        int isProduce;
        if (file.exists()){
            isProduce =JOptionPane.showConfirmDialog(null,req.getFileName() +"文件已存在，是否生成","提示",JOptionPane.YES_NO_OPTION);
        }else {
            isProduce = 0;
        }
        // todo 使用新的继承方式代替覆盖
        if (isProduce == 0) {
            if (!file.exists()) {
                OutputStream fos = new FileOutputStream(new File(dir, req.getFileName())); //文件的生成目录
                Writer out = new OutputStreamWriter(fos,"UTF-8");
                temp.process(req.getTemplateParam(), out);
                fos.flush();
                fos.close();
                System.out.println(req.getFileName() + "gen code success!");
            } else {
//                replaceOldContent(root, dir, fileName, temp);
            }
        }
    }
}
