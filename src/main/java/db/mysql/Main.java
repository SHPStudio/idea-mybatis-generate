package db.mysql;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PACKAGE_NAME
 * Created by ASUS on 2017/7/3.
 * 14:27
 */
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(RuntimeEnv.pp);
        RuntimeEnv.reader();
        generate();
        RuntimeEnv.storage();
        System.out.println(RuntimeEnv.pp);
    }
    public static void generate(){
        List<MySqlData> mySqlDataList = RuntimeEnv.mc.getTableColumns(RuntimeEnv.pp.getTableName());
        Map<String,Object> root = new HashMap<>();
        root.put("schema",RuntimeEnv.pp.getSchema());
        root.put("packageModel",RuntimeEnv.pp.getPackageModel());
        root.put("packageMapper",RuntimeEnv.pp.getPackageMapper());
        root.put("tableName",RuntimeEnv.pp.getTableName());
        root.put("className",RuntimeEnv.pp.getClassName());
        root.put("author",RuntimeEnv.pp.getAuthor());
        root.put("attrs",mySqlDataList);
        MysqlGenUtils.genrate(root);
    }

}
