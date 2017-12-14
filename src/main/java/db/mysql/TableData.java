package db.mysql;

import java.util.List;

/**
 * db.mysql
 *
 * @author mymx.jlh
 * @date 2017/12/14 10:10
 */
public class TableData {
    private String tableName;
    private String autoKey;

    private List<MySqlData> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getAutoKey() {
        return autoKey;
    }

    public void setAutoKey(String autoKey) {
        this.autoKey = autoKey;
    }

    public List<MySqlData> getColumns() {
        return columns;
    }

    public void setColumns(List<MySqlData> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "TableData{" +
                "tableName='" + tableName + '\'' +
                ", autoKey='" + autoKey + '\'' +
                ", columns=" + columns +
                '}';
    }
}
