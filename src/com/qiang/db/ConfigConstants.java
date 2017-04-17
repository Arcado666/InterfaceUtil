package com.qiang.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.qiang.db.slim.DbSlimSelectQuery;
import com.qiang.db.slim.DbSlimSetup;


public class ConfigConstants {
    public final static String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";

    public final static String JDBC_CONNECT_URL = "jdbc:mysql://20.0.0.2:3306/hims_test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
    public final static String JDBC_USERNAME = "root";
    public final static String JDBC_PASSWORD = "QA@2015";
    //开发库

    public final static String DEV_JDBC_CONNECT_URL = "jdbc:mysql://20.0.0.3:3306/hims_test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
    public final static String DEV_JDBC_USERNAME = "root";
    public final static String DEV_JDBC_PASSWORD = "Manyi@123";

    //爱理财开发库

    public final static String MEMBER_JDBC_CONNECT_URL = "jdbc:mysql://30.0.0.5:3306/member?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
    public final static String MEMBER_JDBC_USERNAME = "admin";
    public final static String MEMBER_JDBC_PASSWORD = "adminuser";


    //爱理财测试库

    public final static String MEMBER_TEST_JDBC_CONNECT_URL = "jdbc:mysql://30.0.0.12:3306/member?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
    public final static String MEMBER_TEST_JDBC_USERNAME = "admin";
    public final static String MEMBER_TEST_JDBC_PASSWORD = "adminuser";


    public static void testJDBC() {
        List<Object> list = null;
        try {
            DbSlimSetup db = new DbSlimSetup();
            DbSlimSelectQuery dbsq = new DbSlimSelectQuery("select * from iw400.iw_user_callback_check limit 100");
            list = dbsq.query();
            System.out.println(list);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /***
     * 测试数据库连接
     * @param args
     */
    public static void main(String[] args) {

        testJDBC();
        try {
            // 加载驱动程序
            Class.forName(JDBC_DRIVER_CLASS);
            // 连续数据库
            Connection conn = DriverManager.getConnection(JDBC_CONNECT_URL,
                    JDBC_USERNAME, JDBC_PASSWORD);
            if (!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            // statement用来执行SQL语句
            Statement statement = conn.createStatement();
            // 要执行的SQL语句
            String sql = "select * from hims_test.iw_user limit 100";
            ResultSet rs = statement.executeQuery(sql);
            String name = "";
            while (rs.next()) {
                name = rs.getString("mobile");
                System.out.println(name);
            }
            rs.close();
            conn.close();
        } catch (Exception e) {

        }
    }
}
