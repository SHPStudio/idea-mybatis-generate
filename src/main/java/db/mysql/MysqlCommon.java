package db.mysql;

import com.alibaba.fastjson.JSON;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * PACKAGE_NAME
 * Created by ASUS on 2017/7/16.
 * 10:08
 */
public class MysqlCommon {
    private DatabaseMetaData databaseMetaData;
    private Connection con = null;

    private String url;
    private String schema;
    private String user;
    private String password;
    private List<String> tables;


    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

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

    public MysqlCommon(String url, String schema, String user, String password) {
        this.url = url;
        this.schema=schema;
        this.user = user;
        this.password = password;
        getDatabaseMetaData();
    }
    public MysqlCommon(){
        this.url = RuntimeEnv.pp.getUrl();
        this.schema=RuntimeEnv.pp.getSchema();
        this.user = RuntimeEnv.pp.getUser();
        this.password = RuntimeEnv.pp.getPassword();
        getDatabaseMetaData();
    }

    /**
     * @return 元数据获取
     */
    private void getDatabaseMetaData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = this.url+"/"+schema;
            String user = this.user;
            String password = this.password;
            con = DriverManager.getConnection(url, user, password);
            databaseMetaData= con.getMetaData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<String> getTableList (){
        try {
            if (tables==null) {
                List<String> tables = new ArrayList<>();
                ResultSet rs = databaseMetaData.getTables(null, schema, null, new String[]{"TABLE"});
                while (rs.next()) {
                    tables.add(rs.getString("TABLE_NAME"));
                }
                return tables;
            }
            else {
                return tables;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    /**
     * 获得表或视图中的所有列信息
     */
    public List<MySqlData> getTableColumns(String tableName) {
        List<MySqlData> mySqlDataList = new ArrayList<MySqlData>();
        try{

            Set<String> keySet = new HashSet<>();
            ResultSet rs;
            rs =databaseMetaData.getPrimaryKeys(null,schema,tableName);
            while (rs.next()){
                keySet.add(rs.getString("COLUMN_NAME"));
            }
            rs = databaseMetaData.getColumns(null, schema, tableName, "%");
            while (rs.next()){
                MySqlData mySqlData =new MySqlData();
                mySqlData.setColumnName(rs.getString("COLUMN_NAME"));
                mySqlData.setTypeId(rs.getInt("DATA_TYPE"));
                mySqlData.setTypeName(rs.getString("TYPE_NAME"));
                mySqlData.setJavaTypeName(TypeSwitch.transfer(mySqlData.getTypeName()));
                mySqlData.setRemarks(rs.getString("REMARKS"));
                mySqlData.setNullAble(rs.getInt("NULLABLE"));
                mySqlData.setColumnDef(rs.getString("COLUMN_DEF"));
                mySqlData.setIsAuto(rs.getString("IS_AUTOINCREMENT"));
                if (keySet.contains(mySqlData.getColumnName())){
                    mySqlData.setIsKey(1);
                }else {
                    mySqlData.setIsKey(0);
                }
                mySqlDataList.add(mySqlData);
//                String tableCat = rs.getString("TABLE_CAT");//表目录（可能为空）
//                String tableSchemaName = rs.getString("TABLE_SCHEM");//表的架构（可能为空）
//                String tableName_ = rs.getString("TABLE_NAME");//表名
//                String columnName = rs.getString("COLUMN_NAME");//列名
//                int dataType = rs.getInt("DATA_TYPE"); //对应的java.sql.Types类型
//                String dataTypeName = rs.getString("TYPE_NAME");//java.sql.Types类型   名称
//                int columnSize = rs.getInt("COLUMN_SIZE");//列大小
//                int decimalDigits = rs.getInt("DECIMAL_DIGITS");//小数位数
//                int numPrecRadix = rs.getInt("NUM_PREC_RADIX");//基数（通常是10或2）
//                int nullAble = rs.getInt("NULLABLE");//是否允许为null
//                String remarks = rs.getString("REMARKS");//列描述
//                String columnDef = rs.getString("COLUMN_DEF");//默认值
//                int sqlDataType = rs.getInt("SQL_DATA_TYPE");//sql数据类型
//                int sqlDatetimeSub = rs.getInt("SQL_DATETIME_SUB");   //SQL日期时间分?
//                int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH");   //char类型的列中的最大字节数
//                int ordinalPosition = rs.getInt("ORDINAL_POSITION");  //表中列的索引（从1开始）
//
//                /**
//                 * ISO规则用来确定某一列的为空性。
//                 * 是---如果该参数可以包括空值;
//                 * 无---如果参数不能包含空值
//                 * 空字符串---如果参数为空性是未知的
//                 */
//                String isNullAble = rs.getString("IS_NULLABLE");
//
//                /**
//                 * 指示此列是否是自动递增
//                 * 是---如果该列是自动递增
//                 * 无---如果不是自动递增列
//                 * 空字串---如果不能确定它是否
//                 * 列是自动递增的参数是未知
//                 */
//                String isAutoincrement = rs.getString("IS_AUTOINCREMENT");
//
//                System.out.println(tableCat + "-" + tableSchemaName + "-" + tableName_ + "-" + columnName + "-"
//                        + dataType + "-" + dataTypeName + "-" + columnSize + "-" + decimalDigits + "-" + numPrecRadix
//                        + "-" + nullAble + "-" + remarks + "-" + columnDef + "-" + sqlDataType + "-" + sqlDatetimeSub
//                        + charOctetLength + "-" + ordinalPosition + "-" + isNullAble + "-" + isAutoincrement + "-");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return mySqlDataList;
    }

    public static void main(String[] args) {
        MysqlCommon mysqlCommon = new MysqlCommon("jdbc:mysql://139.129.23.72:3307","cookbook","root","123456");
        mysqlCommon.getDatabaseMetaData();
        List<MySqlData> mySqlDataList=mysqlCommon.getTableColumns("test");
        System.out.println(JSON.toJSONString(mySqlDataList));
        System.out.println(1);
    }
}
