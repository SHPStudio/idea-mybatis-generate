package db.mysql.model;

import jdk.nashorn.internal.objects.annotations.Function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * db.mysql.model
 *
 * @author mymx.jlh
 * @date 2018/1/21 20:25
 */
public enum DataBaseTypeEnum {
    Mysql("mysql",(ip -> "jdbc:mysql://"+ip),"com.mysql.jdbc.Driver"),
    Oracle("oracle",(ip -> "jdbc:oracle:thin:"+ip),"oracle.jdbc.driver.OracleDriver")
    ;


    DataBaseTypeEnum(String dataBaseTypeName, UrlProcessFunction function,String driver) {
        this.dataBaseTypeName = dataBaseTypeName;
        this.function = function;
        this.driver = driver;
    }

    private String dataBaseTypeName;
    private UrlProcessFunction function;
    private String driver;



    @FunctionalInterface
    private static interface UrlProcessFunction{
        String process(String ip);
    }



    public static String getUrl(String dataBaseTypeName,String ip){
        for (DataBaseTypeEnum dataBaseTypeEnum :DataBaseTypeEnum.values()){
            if (dataBaseTypeEnum.dataBaseTypeName.equals(dataBaseTypeName)){
                return dataBaseTypeEnum.function.process(ip);
            }
        }
        throw new RuntimeException("illegal database type");
    }

    public static String getDriver(String dataBaseTypeName){
        for (DataBaseTypeEnum dataBaseTypeEnum :DataBaseTypeEnum.values()){
            if (dataBaseTypeEnum.dataBaseTypeName.equals(dataBaseTypeName)){
                return dataBaseTypeEnum.driver;
            }
        }
        throw new RuntimeException("illegal database type");
    }
}
