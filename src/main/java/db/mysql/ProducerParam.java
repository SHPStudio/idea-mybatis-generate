package db.mysql;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * PACKAGE_NAME
 *
 * @author ASUS
 * @date 2017/10/20 15:37
 */
public class ProducerParam {
    //默认后缀
    public static String Suffix_Mapper = "Mapper";

    //链接数据库属性
    @JSONField(ordinal = 0)
    private String url = "url";
    @JSONField(ordinal = 1)
    private String user = "user";
    @JSONField(ordinal = 2)
    private String password = "password";
    @JSONField(ordinal = 3)
    private String schema = "schema";
    @JSONField(ordinal = 4)
    private String tableName = "tableName";
    //输出文件属性
    @JSONField(ordinal = 5)
    private String modelOutPath = "modelOutPath";
    @JSONField(ordinal = 6)
    private String mapperOutPath = "mapperOutPath";
    @JSONField(ordinal = 7)
    private String mapperXmlOutPath = "mapperXmlOutPath";
    @JSONField(ordinal = 8)
    private String packageModel = "packageModel";
    @JSONField(ordinal = 9)
    private String packageMapper = "packageMapper";
    @JSONField(ordinal = 10)
    private String packageXmlMapper = "packageXmlMapper";
    @JSONField(ordinal = 11)
    private String className = "className";
    @JSONField(ordinal = 12)
    private String mapperName = className + Suffix_Mapper;
    @JSONField(ordinal = 13)
    private String mapperXmlName = className + Suffix_Mapper;
    @JSONField(ordinal = 14)
    private String author = "jlh";
    @JSONField(ordinal = 15)
    private boolean overwrite = false;
    @JSONField(ordinal = 16)
    private String modelWorkSpace = "modelworkspace";
    @JSONField(ordinal = 16)
    private String mapperWorkSpace = "mapperworkspace";
    @JSONField(ordinal = 16)
    private String xmlWorkSpace = "xmlworkspace";
    @JSONField(ordinal = 17)
    private boolean producePackFile = false;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getModelOutPath() {
        return modelOutPath;
    }

    public void setModelOutPath(String modelOutPath) {
        this.modelOutPath = modelOutPath;
    }

    public String getMapperOutPath() {
        return mapperOutPath;
    }

    public void setMapperOutPath(String mapperOutPath) {
        this.mapperOutPath = mapperOutPath;
    }

    public String getMapperXmlOutPath() {
        return mapperXmlOutPath;
    }

    public void setMapperXmlOutPath(String mapperXmlOutPath) {
        this.mapperXmlOutPath = mapperXmlOutPath;
    }

    public String getPackageModel() {
        return packageModel;
    }

    public void setPackageModel(String packageModel) {
        this.packageModel = packageModel;
    }

    public String getPackageMapper() {
        return packageMapper;
    }

    public void setPackageMapper(String packageMapper) {
        this.packageMapper = packageMapper;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getMapperXmlName() {
        return mapperXmlName;
    }

    public void setMapperXmlName(String mapperXmlName) {
        this.mapperXmlName = mapperXmlName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public String getPackageXmlMapper() {
        return packageXmlMapper;
    }

    public void setPackageXmlMapper(String packageXmlMapper) {
        this.packageXmlMapper = packageXmlMapper;
    }

    public String getModelWorkSpace() {
        return modelWorkSpace;
    }

    public void setModelWorkSpace(String modelWorkSpace) {
        this.modelWorkSpace = modelWorkSpace;
    }

    public String getMapperWorkSpace() {
        return mapperWorkSpace;
    }

    public void setMapperWorkSpace(String mapperWorkSpace) {
        this.mapperWorkSpace = mapperWorkSpace;
    }

    public String getXmlWorkSpace() {
        return xmlWorkSpace;
    }

    public void setXmlWorkSpace(String xmlWorkSpace) {
        this.xmlWorkSpace = xmlWorkSpace;
    }

    public boolean isProducePackFile() {
        return producePackFile;
    }

    public void setProducePackFile(boolean producePackFile) {
        this.producePackFile = producePackFile;
    }

    @Override
    public String toString() {
        return "ProducerParam{" +
                "url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", schema='" + schema + '\'' +
                ", tableName='" + tableName + '\'' +
                ", modelOutPath='" + modelOutPath + '\'' +
                ", mapperOutPath='" + mapperOutPath + '\'' +
                ", mapperXmlOutPath='" + mapperXmlOutPath + '\'' +
                ", packageModel='" + packageModel + '\'' +
                ", packageMapper='" + packageMapper + '\'' +
                ", packageXmlMapper='" + packageXmlMapper + '\'' +
                ", className='" + className + '\'' +
                ", mapperName='" + mapperName + '\'' +
                ", mapperXmlName='" + mapperXmlName + '\'' +
                ", author='" + author + '\'' +
                ", overwrite=" + overwrite +
                ", modelWorkSpace='" + modelWorkSpace + '\'' +
                ", mapperWorkSpace='" + mapperWorkSpace + '\'' +
                ", xmlWorkSpace='" + xmlWorkSpace + '\'' +
                ", producePackFile=" + producePackFile +
                '}';
    }
}
