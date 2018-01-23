package db.mysql.process;

import db.mysql.env.RuntimeEnv;
import db.mysql.model.DataBaseTypeEnum;
import db.mysql.model.TableData;
import db.mysql.process.metadata.DataBaseMetaDataProcess;

import java.sql.SQLException;
import java.util.List;

/**
 * PACKAGE_NAME
 * Created by ASUS on 2017/7/16.
 * 10:08
 */
public class MysqlCommon {
    private DataBaseMetaDataProcess dataBaseMetaDataProcess;

    public MysqlCommon() throws SQLException, ClassNotFoundException {
        dataBaseMetaDataProcess  = DataBaseTypeEnum.getByName(RuntimeEnv.pp.getDataBaseType()).getMetaDataProcess();
        dataBaseMetaDataProcess.connect();
    }


    public List<String> getTableList(){
        return dataBaseMetaDataProcess.getTableList();
    }

    public TableData getTableData(String tableName){
        return dataBaseMetaDataProcess.getTableData(tableName);
    }

}
