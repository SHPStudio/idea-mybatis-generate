package db.mysql.model;

import db.mysql.process.metadata.DataBaseMetaDataProcess;
import db.mysql.process.metadata.MysqlMetaDataProcess;
import db.mysql.process.metadata.PostgresMetaDataProcess;

/**
 * db.mysql.model
 *
 * @author mymx.jlh
 * @date 2018/1/21 20:25
 */
public enum DataBaseTypeEnum {
    Mysql("mysql","com.mysql.jdbc.Driver",new  MysqlMetaDataProcess(),"`"),
    Oracle("oracle","oracle.jdbc.driver.OracleDriver",null,"`"),
    Postgres("postgres","org.postgresql.Driver",new PostgresMetaDataProcess(),"\"")
    ;


    DataBaseTypeEnum(String dataBaseTypeName, String driver, DataBaseMetaDataProcess metaDataProcess, String sense) {
        this.dataBaseTypeName = dataBaseTypeName;
        this.driver = driver;
        this.metaDataProcess = metaDataProcess;
        this.sense = sense;
    }

    private String dataBaseTypeName;
    private String driver;
    private DataBaseMetaDataProcess metaDataProcess;
    private String sense;

    public DataBaseMetaDataProcess getMetaDataProcess() {
        return metaDataProcess;
    }

    public String getSense() {
        return sense;
    }

    public void setSense(String sense) {
        this.sense = sense;
    }

    public void setMetaDataProcess(DataBaseMetaDataProcess metaDataProcess) {
        this.metaDataProcess = metaDataProcess;
    }

    public String getDataBaseTypeName() {
        return dataBaseTypeName;
    }

    public void setDataBaseTypeName(String dataBaseTypeName) {
        this.dataBaseTypeName = dataBaseTypeName;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public static DataBaseTypeEnum getByName(String dataBaseTypeName){
        for (DataBaseTypeEnum dataBaseTypeEnum :DataBaseTypeEnum.values()){
            if (dataBaseTypeEnum.dataBaseTypeName.equals(dataBaseTypeName)){
                return dataBaseTypeEnum;
            }
        }
        throw new RuntimeException("illegal database type");
    }
}
