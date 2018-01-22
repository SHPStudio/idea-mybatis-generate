package db.mysql.model;

/**
 * db.mysql.model
 *
 * @author mymx.jlh
 * @date 2018/1/21 20:25
 */
public enum DataBaseTypeEnum {
    Mysql("mysql","com.mysql.jdbc.Driver"),
    Oracle("oracle","oracle.jdbc.driver.OracleDriver"),
    Postgres("postgres","org.postgresql.Driver")
    ;


    DataBaseTypeEnum(String dataBaseTypeName,String driver) {
        this.dataBaseTypeName = dataBaseTypeName;
        this.driver = driver;
    }

    private String dataBaseTypeName;
    private String driver;



    public static String getDriver(String dataBaseTypeName){
        for (DataBaseTypeEnum dataBaseTypeEnum :DataBaseTypeEnum.values()){
            if (dataBaseTypeEnum.dataBaseTypeName.equals(dataBaseTypeName)){
                return dataBaseTypeEnum.driver;
            }
        }
        throw new RuntimeException("illegal database type");
    }
}
