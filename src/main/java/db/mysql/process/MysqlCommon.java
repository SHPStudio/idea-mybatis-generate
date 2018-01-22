package db.mysql.process;

import db.mysql.env.RuntimeEnv;
import db.mysql.model.DataBaseTypeEnum;
import db.mysql.model.MySqlData;
import db.mysql.model.TableData;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

    public MysqlCommon(String url, String schema, String user, String password) throws SQLException, ClassNotFoundException {
        this.url = url;
        this.schema=schema;
        this.user = user;
        this.password = password;
        getDatabaseMetaData();
    }
    public MysqlCommon() throws SQLException, ClassNotFoundException {
        this.url = RuntimeEnv.pp.getUrl();
        this.schema=RuntimeEnv.pp.getSchema();
        this.user = RuntimeEnv.pp.getUser();
        this.password = RuntimeEnv.pp.getPassword();
        getDatabaseMetaData();
    }

    /**
     * @return 元数据获取
     */
    private void getDatabaseMetaData() throws ClassNotFoundException, SQLException {
        Class.forName(DataBaseTypeEnum.getDriver(RuntimeEnv.pp.getDataBaseType()));
        String url = this.url;
        String user = this.user;
        String password = this.password;
        DriverManager.setLoginTimeout(5);
        con = DriverManager.getConnection(url, user, password);
        databaseMetaData= con.getMetaData();
    }


    public List<String> getTableList (){
        try {
            if (tables==null) {
                List<String> tables = new ArrayList<>();
                ResultSet rs = databaseMetaData.getTables(null, null, null,null);
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
                mySqlData.setIsBetween(TypeSwitch.isBetween(mySqlData.getTypeName()));
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

    private void getIndexInfo(String tableName) {
        try {
            ResultSet rs3 = databaseMetaData.getIndexInfo(null, null, tableName, false, true);
            while (rs3.next()) {
                System.out.println("数据库名: "+ rs3.getString(1));
                System.out.println("表模式: "+ rs3.getString(2));
                System.out.println("表名称: "+ rs3.getString(3));
                System.out.println("索引值是否可以不唯一: "+ rs3.getString(4));
                System.out.println("索引类别: "+ rs3.getString(5));
                System.out.println("索引名称: "+ rs3.getString(6));
                System.out.println("索引类型: "+ rs3.getString(7));
                System.out.println("索引中的列序列号: "+ rs3.getString(8));
                System.out.println("列名称: "+ rs3.getString(9));
                System.out.println("列排序序列: "+ rs3.getString(10));
                System.out.println("TYPE为 tableIndexStatistic时它是表中的行数否则它是索引中唯一值的数量: "+ rs3.getString(11));
                System.out.println("TYPE为 tableIndexStatisic时它是用于表的页数否则它是用于当前索引的页数: "+ rs3.getString(12));
                System.out.println("过滤器条件: "+ rs3.getString(13));
            }
            rs3.close();
        }catch (Exception e ){
            e.printStackTrace();
        }
    }


    public TableData getTableData(String tableName){
        TableData tableData = new TableData();
        tableData.setTableName(tableName);
        tableData.setColumns(this.getTableColumns(tableName));
        Optional<MySqlData> optionalMySqlData =tableData.getColumns().stream().filter(m->!m.getIsAuto().equals("NO")).findFirst();
        optionalMySqlData.ifPresent(mySqlData -> tableData.setAutoKey(mySqlData.getColumnName()));
        return tableData;
    }
}
