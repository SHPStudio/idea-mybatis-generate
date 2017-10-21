package db;

import db.mframe.MainFrame;
import db.mysql.RuntimeEnv;

import java.io.IOException;

/**
 * db
 *
 * @author ASUS
 * @date 2017/10/21 10:15
 */
public class Main {
    public static void main(String[] args) throws IOException {
        RuntimeEnv.reader();
        new MainFrame();
        RuntimeEnv.storage();
    }
}

