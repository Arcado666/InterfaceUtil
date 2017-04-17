package com.qiang.db.slim;

import com.qiang.db.ConfigConstants;
import com.qiang.db.services.DbConnectionFactory;

public class DbSlimSetup {

    public static String DEFAULT_CONNECTION_POOL_NAME = "default";
    public static String DEV_CONNECTION_POOL_NAME = "dev";
    public static String MEMBER_CONNECTION_POOL_NAME = "memberdev";
    public static String MEMBER_TEST_CONNECTION_POOL_NAME = "member";
    public static int DEFAULT_CONNECTION_POOL_MIN_IDLE = 100;
    public static int DEFAULT_CONNECTION_POOL_MAX_AXTIVE = 2000;
    public static int DEFAULT_WAIT_TIMEOUT = 105000;

    public DbSlimSetup() throws Exception {
        DbConnectionFactory.getDataSource(DEFAULT_CONNECTION_POOL_NAME, ConfigConstants.JDBC_DRIVER_CLASS, ConfigConstants.JDBC_CONNECT_URL, ConfigConstants.JDBC_USERNAME, ConfigConstants.JDBC_PASSWORD, DEFAULT_CONNECTION_POOL_MIN_IDLE, DEFAULT_CONNECTION_POOL_MAX_AXTIVE);
    }


    /***
     * 根据数据库链接KEY来判断是实例化那个环境库的链接
     * @param connectionPoolName 数据库链接KEY
     * @throws Exception
     */
    public DbSlimSetup(String connectionPoolName) throws Exception {
        if (!"".equals(connectionPoolName)) {
            //测试环境库
            if (connectionPoolName.equals(DEFAULT_CONNECTION_POOL_NAME)) {
                DbConnectionFactory.getDataSource(DEFAULT_CONNECTION_POOL_NAME, ConfigConstants.JDBC_DRIVER_CLASS, ConfigConstants.JDBC_CONNECT_URL, ConfigConstants.JDBC_USERNAME, ConfigConstants.JDBC_PASSWORD, DEFAULT_CONNECTION_POOL_MIN_IDLE, DEFAULT_CONNECTION_POOL_MAX_AXTIVE);
            }
            //开发环境库
            else if (connectionPoolName.toUpperCase().equals(DEV_CONNECTION_POOL_NAME.toUpperCase())) {
                DbConnectionFactory.getDataSource(DEV_CONNECTION_POOL_NAME,
                        ConfigConstants.JDBC_DRIVER_CLASS, ConfigConstants.DEV_JDBC_CONNECT_URL, ConfigConstants.DEV_JDBC_USERNAME, ConfigConstants.DEV_JDBC_PASSWORD, DEFAULT_CONNECTION_POOL_MIN_IDLE, DEFAULT_CONNECTION_POOL_MAX_AXTIVE);
            }
            //爱理财 开发 数据库链接初始化
            else if (connectionPoolName.toUpperCase().equals(MEMBER_CONNECTION_POOL_NAME.toUpperCase())) {
                DbConnectionFactory.getDataSource(MEMBER_CONNECTION_POOL_NAME, ConfigConstants.JDBC_DRIVER_CLASS, ConfigConstants.MEMBER_JDBC_CONNECT_URL, ConfigConstants.MEMBER_JDBC_USERNAME, ConfigConstants.MEMBER_JDBC_PASSWORD, DEFAULT_CONNECTION_POOL_MIN_IDLE, DEFAULT_CONNECTION_POOL_MAX_AXTIVE);
            }
            //爱理 财测 试数据库链接初始化
            else if (connectionPoolName.toUpperCase().equals(MEMBER_TEST_CONNECTION_POOL_NAME.toUpperCase())) {
                DbConnectionFactory.getDataSource(MEMBER_TEST_CONNECTION_POOL_NAME, ConfigConstants.JDBC_DRIVER_CLASS, ConfigConstants.MEMBER_TEST_JDBC_CONNECT_URL, ConfigConstants.MEMBER_TEST_JDBC_USERNAME, ConfigConstants.MEMBER_TEST_JDBC_PASSWORD, DEFAULT_CONNECTION_POOL_MIN_IDLE, DEFAULT_CONNECTION_POOL_MAX_AXTIVE);
            }
        } else {
            DbConnectionFactory.getDataSource(DEFAULT_CONNECTION_POOL_NAME, ConfigConstants.JDBC_DRIVER_CLASS, ConfigConstants.JDBC_CONNECT_URL, ConfigConstants.JDBC_USERNAME, ConfigConstants.JDBC_PASSWORD, DEFAULT_CONNECTION_POOL_MIN_IDLE, DEFAULT_CONNECTION_POOL_MAX_AXTIVE);
        }

    }

    public DbSlimSetup(String host, String port, String username, String password) {
        this(host, port, username, password, "");
    }

    public DbSlimSetup(String host, String port, String username, String password, String database) {
        try {
            DbConnectionFactory.getDataSource(makeDbName(host, port, username, password, database), ConfigConstants.JDBC_DRIVER_CLASS, "jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false", username, password, DEFAULT_CONNECTION_POOL_MIN_IDLE, DEFAULT_CONNECTION_POOL_MAX_AXTIVE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String makeDbName(String host, String port, String username, String password, String database) {
        return host + "_" + port + "_" + (database == null ? "" : database) + "_" + username + "_" + password;
    }

}

