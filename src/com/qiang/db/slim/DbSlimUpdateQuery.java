package com.qiang.db.slim;

import javax.sql.DataSource;

import com.qiang.db.services.DbConnectionFactory;
import com.qiang.utils.SqlUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbSlimUpdateQuery {

    private String connectionPoolName;
    private String sql;

    public DbSlimUpdateQuery() {
        this(DbSlimSetup.DEFAULT_CONNECTION_POOL_NAME, null);
    }

    public DbSlimUpdateQuery(String sql) {
        this(DbSlimSetup.DEFAULT_CONNECTION_POOL_NAME, sql);
    }

    public DbSlimUpdateQuery(String connectionPoolName, String sql) {
        this.connectionPoolName = connectionPoolName;
        try {
            new DbSlimSetup(connectionPoolName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.sql = SqlUtils.format(sql);
    }

    public DbSlimUpdateQuery(String host, String port, String username, String password) {
        try {
            connectionPoolName = DbSlimSetup.makeDbName(host, port, username, password, "");
            new DbSlimSetup(host, port, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DbSlimUpdateQuery(String host, String port, String username, String password, String database) {
        try {
            connectionPoolName = DbSlimSetup.makeDbName(host, port, username, password, database);
            new DbSlimSetup(host, port, username, password, database);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSql(String sql) {
        this.sql = SqlUtils.format(sql);
    }

    public String rowsUpdated() {
        List<List<List<String>>> dataTable = getDataTable();
        return dataTable.get(0).get(0).get(1);
    }

    public String rowsUpdated(String sql) {
        this.sql = SqlUtils.format(sql);
        return rowsUpdated();
    }

    protected List<List<List<String>>> getDataTable() {

        DataSource dataSource = DbConnectionFactory.getDataSource(connectionPoolName);
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        ArrayList<List<List<String>>> dataTable = new ArrayList<List<List<String>>>();

        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(true);
            stmt = conn.createStatement();
            int rowsUpdated = stmt.executeUpdate(sql);

            ArrayList<List<String>> dataRow = new ArrayList<List<String>>();
            ArrayList<String> dataItem = new ArrayList<String>();
            dataItem.add(String.valueOf("rowsUpdated"));
            dataItem.add(String.valueOf(rowsUpdated));

            dataRow.add(dataItem);
            dataTable.add(dataRow);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (rset != null)
                    rset.close();
            } catch (Exception e) {
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }

        return dataTable;
    }
}

