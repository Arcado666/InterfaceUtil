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

public class DbSlimSelectQuery {

    private String connectionPoolName;
    private String sql;
    private List<List<List<String>>> dataTable = null;

    public DbSlimSelectQuery() {
        this(DbSlimSetup.DEFAULT_CONNECTION_POOL_NAME, null);
    }

    public DbSlimSelectQuery(String sql) {
        this(DbSlimSetup.DEFAULT_CONNECTION_POOL_NAME, sql);
    }

    public DbSlimSelectQuery(String connectionPoolName, String sql) {
        this.connectionPoolName = connectionPoolName;
        try {
            new DbSlimSetup(connectionPoolName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.sql = SqlUtils.format(sql);
    }

    public DbSlimSelectQuery(String host, String port, String username, String password) {
        try {
            connectionPoolName = DbSlimSetup.makeDbName(host, port, username, password, "");
            new DbSlimSetup(host, port, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DbSlimSelectQuery(String host, String port, String username, String password, String database) {
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

    public List<Object> query() {

        List<List<List<String>>> dataTable = getDataTable();
        return new ArrayList<Object>(dataTable);

    }

    public String queryByName(String name) {
        dataTable = (dataTable == null ? getDataTable() : dataTable);
        if (null == dataTable || dataTable.size() == 0)
            return "";
        //循环第一行
        ok:
        for (int i = 0; i < dataTable.get(0).size(); i++) {
            List<String> dataItem = dataTable.get(0).get(i);
            for (int j = 0; j < dataItem.size(); j++) {
                String key = dataItem.get(j);
                String value = dataItem.get(j + 1);
                if (key.equals(name)) {
                    return value;
                } else {
                    continue ok;
                }
            }
        }
        return "";
    }

    public String queryByName(String name, String sql) {
        this.sql = SqlUtils.format(sql);
        return queryByName(name);
    }

    public String data(String columnId, String rowIndex) {
        try {
            Integer.parseInt(columnId);
            return dataByColumnIndexAndRowIndex(columnId, rowIndex);
        } catch (NumberFormatException e) {
            return dataByColumnNameAndRowIndex(columnId, rowIndex);
        }

    }

    public String dataByColumnIndexAndRowIndex(String columnIndex, String rowIndex) {
        int rowIndexInteger = Integer.parseInt(rowIndex);
        int columnIndexInteger = Integer.parseInt(columnIndex);
        dataTable = (dataTable == null ? getDataTable() : dataTable);
        return dataTable.get(rowIndexInteger).get(columnIndexInteger).get(1);
    }

    public String dataByColumnNameAndRowIndex(String columnName, String rowIndex) {
        int rowIndexInteger = Integer.parseInt(rowIndex);
        dataTable = (dataTable == null ? getDataTable() : dataTable);
        List<List<String>> dataRow = dataTable.get(rowIndexInteger);
        for (List<String> dataItem : dataRow) {
            if (String.valueOf(dataItem.get(0)).toUpperCase().equals(String.valueOf(columnName).toUpperCase())) {
                return dataItem.get(1);
            }
        }
        return null;
    }

    private List<List<List<String>>> getDataTable() {

        DataSource dataSource = DbConnectionFactory.getDataSource(connectionPoolName);

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        ArrayList<List<List<String>>> dataTable = new ArrayList<List<List<String>>>();

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rset = stmt.executeQuery(sql);

            ArrayList<String> columnNames = new ArrayList<String>();

            int numcols = rset.getMetaData().getColumnCount();
            for (int i = 1; i <= numcols; i++) {
                columnNames.add(rset.getMetaData().getColumnName(i));
            }
            while (rset.next()) {
                ArrayList<List<String>> dataRow = new ArrayList<List<String>>();
                for (int i = 1; i <= numcols; i++) {
                    ArrayList<String> dataItem = new ArrayList<String>();
                    dataItem.add(String.valueOf(columnNames.get(i - 1)));
                    dataItem.add(String.valueOf(rset.getString(i)));
                    dataRow.add(dataItem);
                }
                dataTable.add(dataRow);
            }

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

    public static void main(String[] args) throws Exception {
//        DbSlimSetup dbSlimSetup = new DbSlimSetup();
//        DbSlimSelectQuery dbSlimSelectQuery = new DbSlimSelectQuery("select * from hims_test.iw_user LIMIT 1");
//        System.out.println(dbSlimSelectQuery.queryByName("mobile"));
    	
    	DbSlimSelectQuery db = new DbSlimSelectQuery("121.40.127.92", "3309", "qa_tmp", "mjl84_)l23jda0l_23");
		String verify = db.queryByName("content","SELECT * FROM sms_fyb_beta.iw_sms where mobile = '18516081513' order by id desc limit 1;");
		System.out.println(verify);

    }
}
