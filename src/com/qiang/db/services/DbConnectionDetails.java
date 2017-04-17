package com.qiang.db.services;

import javax.sql.DataSource;

class DbConnectionDetails {


    public DataSource dataSource = null;

    public String jdbcDriverClass = null;
    public String connectURI = null;
    public String username = null;
    public String password = null;
    public int minIdle = 3;
    public int maxActive = 1000;


    public DbConnectionDetails() {

    }

}

