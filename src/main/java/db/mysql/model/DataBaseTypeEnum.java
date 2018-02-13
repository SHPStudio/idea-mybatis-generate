package db.mysql.model;

import db.mysql.process.MysqlTypeSwitch;
import db.mysql.process.PostgreTypeSwitch;
import db.mysql.process.TypeSwitch;
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
    Mysql("mysql","com.mysql.jdbc.Driver",new  MysqlMetaDataProcess(),"`",new MysqlTypeSwitch()),
    Oracle("oracle","oracle.jdbc.driver.OracleDriver",null,"`",null),
    Postgres("postgres","org.postgresql.Driver",new PostgresMetaDataProcess(),"\"", new PostgreTypeSwitch())
    ;


    DataBaseTypeEnum(String dataBaseTypeName, String driver, DataBaseMetaDataProcess metaDataProcess, String sense,TypeSwitch typeSwitch) {
        this.dataBaseTypeName = dataBaseTypeName;
        this.driver = driver;
        this.metaDataProcess = metaDataProcess;
        this.sense = sense;
        this.typeSwitch = typeSwitch;
    }

    private String dataBaseTypeName;
    private String driver;
    private DataBaseMetaDataProcess metaDataProcess;
    private String sense;
    private TypeSwitch typeSwitch;

    public DataBaseMetaDataProcess getMetaDataProcess() {
        return metaDataProcess;
    }

    public String getSense() {
        return sense;
    }

    public void setSense(String sense) {
        this.sense = sense;
    }

    public TypeSwitch getTypeSwitch() {
        return typeSwitch;
    }

    public void setTypeSwitch(TypeSwitch typeSwitch) {
        this.typeSwitch = typeSwitch;
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
