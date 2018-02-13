package db.mysql.process;

/**
 * @author mymx.jlh
 * @version 1.0.0 2018/2/13 10:13
 */
public interface TypeSwitch {

    String transfer(String typeName);

    public static String  isBetween(String typeName){
        typeName=typeName.replaceAll(" UNSIGNED","").toUpperCase();
        switch (typeName){
            case "VARCHAR": return "no";
            case "CHAR" :return "no";
            case "TEXT" :return "no";
            case "INTEGER" : return "yes";
            case "BIGINT": return "yes";
            case "TINYINT": return "yes";
            case "SMALLINT":return "yes";
            case "MEDIUMINT": return "yes";
            case "INT":return "yes";
            case "FLOAT":return "yes";
            case "DOUBLE":return "yes";
            case "DECIMAL" :return "yes";
            case "DATE":return "yes";
            case "TIME":return "yes";
            case "TIMESTAMP":return "yes";
            case "MEDIUMTEXT":return "no";
            case "LONGTEXT":return "no";
            case "DATETIME":return "yes";
            case "BIT":return "yes";
            case "INT8":return "yes";
            case "INT4":return "yes";
            case "INT2":return "yes";
            case "BOOL":return "no";
            case "FLOAT4":return "yes";
            case "FLOAT8":return "yes";
            case "BIGSERIAL":return "yes";
            case "SERIAL":return "yes";
            default:
                throw new IllegalArgumentException(typeName+" no such typeName,please edit db.mysql.process.TypeSwitch");
        }
    }
}
