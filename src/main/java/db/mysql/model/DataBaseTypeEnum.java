package db.mysql.model;

import db.mysql.process.metadata.DataBaseMetaDataProcess;
import db.mysql.process.metadata.MysqlMetaDataProcess;

/**
 * db.mysql.model
 *
 * @author mymx.jlh
 * @date 2018/1/21 20:25
 */
public enum DataBaseTypeEnum {
    Mysql("mysql","com.mysql.jdbc.Driver",new  MysqlMetaDataProcess()),
    Oracle("oracle","oracle.jdbc.driver.OracleDriver",null),
    Postgres("postgres","org.postgresql.Driver",null)
    ;


    DataBaseTypeEnum(String dataBaseTypeName, String driver, DataBaseMetaDataProcess metaDataProcess) {
        this.dataBaseTypeName = dataBaseTypeName;
        this.driver = driver;
        this.metaDataProcess = metaDataProcess;
    }

    private String dataBaseTypeName;
    private String driver;
    private DataBaseMetaDataProcess metaDataProcess;

    public DataBaseMetaDataProcess getMetaDataProcess() {
        return metaDataProcess;
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
