package db.mysql.process;

import db.mysql.env.RuntimeEnv;
import db.mysql.model.DataBaseTypeEnum;

/**
 * @author mymx.jlh
 * @version 1.0.0 2018/2/13 10:16
 */
public class PostgreTypeSwitch implements TypeSwitch {
    @Override
    public String transfer(String typeName) {
        typeName = typeName.replaceAll(" UNSIGNED", "").toUpperCase();
        switch (typeName) {
            case "VARCHAR":
                return "String";
            case "CHAR":
                return "String";
            case "TEXT":
                return "String";
            case "INTEGER":
                return "Long";
            case "BIGINT":
                return "Long";
            case "TINYINT":
                return "Integer";
            case "SMALLINT":
                return "Integer";
            case "MEDIUMINT":
                return "Integer";
            case "INT":
                return "Integer";
            case "FLOAT":
                return "Float";
            case "DOUBLE":
                return "Double";
            case "DECIMAL":
                return "java.math.BigDecimal";
            case "DATE":
                return "java.time.LocalDate";
            case "TIME":
                return "java.time.LocalTime";
            case "TIMESTAMP":
                return "java.time.LocalDateTime";
            case "MEDIUMTEXT":
                return "String";
            case "LONGTEXT":
                return "String";
            case "DATETIME":
                return "java.time.LocalDateTime";
            case "BIT":
                return "String";
            case "INT8":
                return "Long";
            case "INT4":
                return "Integer";
            case "INT2":
                return "Integer";
            case "BOOL":
                return "Boolean";
            case "FLOAT4":
                return "Float";
            case "FLOAT8":
                return "Double";
            case "BIGSERIAL":
                return "Long";
            case "SERIAL":
                return "Integer";
            default:
                throw new IllegalArgumentException(typeName + " no such typeName,please edit db.mysql.process.MysqlTypeSwitch");
        }
    }
}
