package db.mysql.model;

import java.util.Map;

/**
 * db.mysql.model
 *
 * @author mymx.jlh
 * @date 2017/12/18 20:40
 */
public class GenrateParamReq {
    private String outPath;
    private String fileName;
    private Map<String, Object> templateParam;
    private String templateName;

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, Object> getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(Map<String, Object> templateParam) {
        this.templateParam = templateParam;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public String toString() {
        return "GenrateParamReq{" +
                "outPath='" + outPath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", templateParam=" + templateParam +
                ", templateName='" + templateName + '\'' +
                '}';
    }


    public static final class GenrateParamReqBuilder {
        private String outPath;
        private String fileName;
        private Map<String, Object> templateParam;
        private String templateName;

        private GenrateParamReqBuilder() {
        }

        public static GenrateParamReqBuilder aGenrateParamReq() {
            return new GenrateParamReqBuilder();
        }

        public GenrateParamReqBuilder withOutPath(String outPath) {
            this.outPath = outPath;
            return this;
        }

        public GenrateParamReqBuilder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public GenrateParamReqBuilder withTemplateParam(Map<String, Object> templateParam) {
            this.templateParam = templateParam;
            return this;
        }

        public GenrateParamReqBuilder withTemplateName(String templateName) {
            this.templateName = templateName;
            return this;
        }

        public GenrateParamReq build() {
            GenrateParamReq genrateParamReq = new GenrateParamReq();
            genrateParamReq.setOutPath(outPath);
            genrateParamReq.setFileName(fileName);
            genrateParamReq.setTemplateParam(templateParam);
            genrateParamReq.setTemplateName(templateName);
            return genrateParamReq;
        }
    }
}
