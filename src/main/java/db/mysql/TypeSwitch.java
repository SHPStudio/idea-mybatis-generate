package db.mysql;

/**
 * db.mysql
 * Created by ASUS on 2017/7/16.
 * 10:37
 */
public class TypeSwitch {
    public static String  transfer(String typeName){
        typeName=typeName.replaceAll(" UNSIGNED","");
        switch (typeName){
            case "VARCHAR": return "String";
            case "CHAR" :return "String";
            case "TEXT" :return "String";
            case "INTEGER" : return "Long";
            case "BIGINT": return "Long";
            case "TINYINT": return "Integer";
            case "SMALLINT":return "Integer";
            case "MEDIUMINT": return "Integer";
            case "INT":return "Integer";
            case "FLOAT":return "Float";
            case "DOUBLE":return "Double";
            case "DECIMAL" :return "java.math.BigDecimal";
            case "DATE":return "java.time.LocalDate";
            case "TIME":return "java.time.LocalTime";
            case "TIMESTAMP":return "java.time.LocalDateTime";
            case "MEDIUMTEXT":return "String";
            case "LONGTEXT":return "String";
            case "DATETIME":return "java.time.LocalDate";
            default:
                throw new IllegalArgumentException(typeName+" no such typeName,please edit db.mysql.TypeSwitch");
        }
    }
    public static String  isTime(String typeName){
        typeName=typeName.replaceAll(" UNSIGNED","");
        switch (typeName){
            case "VARCHAR": return "no";
            case "CHAR" :return "no";
            case "TEXT" :return "no";
            case "INTEGER" : return "no";
            case "BIGINT": return "no";
            case "TINYINT": return "no";
            case "SMALLINT":return "no";
            case "MEDIUMINT": return "no";
            case "INT":return "no";
            case "FLOAT":return "no";
            case "DOUBLE":return "no";
            case "DECIMAL" :return "no";
            case "DATE":return "yes";
            case "TIME":return "yes";
            case "TIMESTAMP":return "yes";
            case "MEDIUMTEXT":return "no";
            case "LONGTEXT":return "no";
            case "DATETIME":return "yes";
            default:
                throw new IllegalArgumentException(typeName+" no such typeName,please edit db.mysql.TypeSwitch");
        }
    }
}
