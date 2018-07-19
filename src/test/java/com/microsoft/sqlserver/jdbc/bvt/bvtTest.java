/*
 * Microsoft JDBC Driver for SQL Server
 * 
 * Copyright(c) Microsoft Corporation All rights reserved.
 * 
 * This program is made available under the terms of the MIT License. See the LICENSE file in the project root for more information.
 */
package com.microsoft.sqlserver.jdbc.bvt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.microsoft.sqlserver.testframework.DBConnection;
import com.microsoft.sqlserver.testframework.DBPreparedStatement;
import com.microsoft.sqlserver.testframework.DBResultSet;
import com.microsoft.sqlserver.testframework.DBResultSetTypes;
import com.microsoft.sqlserver.testframework.DBStatement;

import com.microsoft.sqlserver.jdbc.TestResource;;

@RunWith(JUnitPlatform.class)
@DisplayName("BVT Test")
public class bvtTest extends bvtTestSetup {
    private static String driverNamePattern = "Microsoft JDBC Driver \\d.\\d for SQL Server";

    /**
     * Connect to specified server and close the connection
     * 
     * @throws SQLException
     */
    @Test
    @DisplayName("test connection")
    public void testConnection() throws SQLException {
        try (Connection conn = DriverManager.getConnection(connectionString)) {}
    }

    /**
     * Verify isClosed()
     * 
     * @throws SQLException
     */
    @Test
    public void testConnectionIsClosed() throws SQLException {
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            assertTrue(!conn.isClosed(), TestResource.getResource("R_connShouldNotBeClosed"));
            conn.close();
            assertTrue(conn.isClosed(), TestResource.getResource("R_connShouldNotBeOpen"));

        }
    }

    /**
     * Verify Driver Name and Version from MetaData
     * 
     * @throws SQLException
     */
    @Test
    public void testDriverNameAndDriverVersion() throws SQLException {
        try (Connection conn = DriverManager.getConnection(connectionString)) {
            DatabaseMetaData metaData = conn.getMetaData();
            Pattern p = Pattern.compile(driverNamePattern);
            Matcher m = p.matcher(metaData.getDriverName());
            assertTrue(m.find(), TestResource.getResource("R_incorrectDriverNameFormat"));
            String[] parts = metaData.getDriverVersion().split("\\.");
            if (parts.length != 4)
                assertTrue(true, TestResource.getResource("R_incorrectDriverVewrsionFormat"));
        }
    }

    /**
     * Create a statement, call close
     * 
     * @throws SQLException
     */
    @Test
    public void testCreateStatement() throws SQLException {

    	String query = "SELECT * FROM " + table1.getEscapedTableName() + ";";
    	
        try (Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement();
            ResultSet rsjdbc = stmt.executeQuery(query);
            DBResultSet rs = new DBResultSet(null, rsjdbc)) {
            rs.verify(table1);
        }
    }

    /**
     * Create a statement with a query timeout
     * 
     * @throws SQLException
     */
    @Test
    public void testCreateStatementWithQueryTimeout() throws SQLException {

        try (Connection conn = DriverManager.getConnection(connectionString + ";querytimeout=10");
            Statement stmt = conn.createStatement()) {
            assertEquals(10, stmt.getQueryTimeout());
        }
    }

    /**
     * Create a statement ResultSet.Type_forward_only, ResultSet.CONCUR_READ_ONLY, executeQuery verify cursor by using next and previous and verify
     * data
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void testStmtForwardOnlyReadOnly() throws SQLException, ClassNotFoundException {

    	String query = "SELECT * FROM " + table1.getEscapedTableName();
    	System.out.println("starting testStmtForwardOnlyReadOnly");
        try (Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rsjdbc = stmt.executeQuery(query);
            DBResultSet rs = new DBResultSet(null, rsjdbc)) {

            rs.next();
            rs.verifyCurrentRow(table1);
            rs.next();
            rs.verifyCurrentRow(table1);

            try {
                rs.previous();
                assertTrue(false, "Previous should have thrown an exception");
            }
            catch (SQLException ex) {
                // expected exception
            }
            rs.verify(table1);
        }
        System.out.println("ending testStmtForwardOnlyReadOnly");
    }

    /**
     * Create a statement, ResultSet.SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, executeQuery verify cursor by using next, afterlast and previous
     * and verify data
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void testStmtScrollInsensitiveReadOnly() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM " + table1.getEscapedTableName();
        System.out.println("starting testStmtScrollInsensitiveReadOnly");
        try (Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rsjdbc = stmt.executeQuery(query);
            DBResultSet rs = new DBResultSet(null, rsjdbc)) {
            rs.next();
            rs.verifyCurrentRow(table1);
            rs.afterLast();
            rs.previous();
            rs.verifyCurrentRow(table1);
            rs.verify(table1);
        }
        System.out.println("ending testStmtScrollInsensitiveReadOnly");
    }

    /**
     * Create a statement ResultSet.SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY, executeQuery verify cursor by using next and absolute and verify
     * data
     * 
     * @throws SQLException
     */
    @Test
    public void testStmtScrollSensitiveReadOnly() throws SQLException {
        System.out.println("starting testStmtScrollSensitiveReadOnly");
        String query = "SELECT * FROM " + table1.getEscapedTableName();
        
        try (Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rsjdbc = stmt.executeQuery(query);
            DBResultSet rs = new DBResultSet(null, rsjdbc)) {
            rs.next();
            rs.next();
            rs.verifyCurrentRow(table1);
            rs.absolute(3);
            rs.verifyCurrentRow(table1);
            rs.absolute(1);
            rs.verify(table1);

        }
        System.out.println("ending testStmtScrollSensitiveReadOnly");
    }

    /**
     * Create a statement ResultSet.Type_forward_only, ResultSet.CONCUR_UPDATABLE, executeQuery verify cursor by using next and previous and verify
     * data
     * 
     * @throws SQLException
     */
    @Test
    public void testStmtForwardOnlyUpdateable() throws SQLException {
        
    	String query = "SELECT * FROM " + table1.getEscapedTableName();
    	System.out.println("starting testStmtForwardOnlyUpdateable");

        try (Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rsjdbc = stmt.executeQuery(query);
            DBResultSet rs = new DBResultSet(null, rsjdbc)) {
            rs.next();

            // Verify resultset behavior
            rs.next();
            rs.verifyCurrentRow(table1);
            rs.next();
            rs.verifyCurrentRow(table1);
            try {
                rs.previous();
                assertTrue(false, TestResource.getResource("R_previousShouldThrow"));
            }
            catch (SQLException ex) {
                // expected exception
            }
            rs.verify(table1);
        }
        System.out.println("ending testStmtForwardOnlyUpdateable");
    }

    /**
     * Create a statement ResultSet.SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, executeQuery verify cursor by using next and previous and verify
     * data
     * 
     * @throws SQLException
     */
    @Test
    public void testStmtScrollSensitiveUpdatable() throws SQLException {

    	String query = "SELECT * FROM " + table1.getEscapedTableName();
    	System.out.println("starting testStmtScrollSensitiveUpdatable");
        try (Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rsjdbc = stmt.executeQuery(query);
            DBResultSet rs = new DBResultSet(null, rsjdbc)) {

            // Verify resultset behavior
            rs.next();
            rs.next();
            rs.verifyCurrentRow(table1);
            rs.absolute(3);
            rs.verifyCurrentRow(table1);
            rs.absolute(1);
            rs.verify(table1);
        }
        System.out.println("ending testStmtScrollSensitiveUpdatable");
    }

    /**
     * Create a statement TYPE_SS_SCROLL_DYNAMIC, CONCUR_SS_OPTIMISTIC_CC, executeQuery verify cursor by using next and previous and verify data
     * 
     * @throws SQLException
     */
    @Test
    public void testStmtSSScrollDynamicOptimisticCC() throws SQLException {
        String query = "SELECT * FROM " + table1.getEscapedTableName();
        System.out.println("starting testStmtSSScrollDynamicOptimisticCC");
        try (Connection conn = DriverManager.getConnection(connectionString);
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE + 1, ResultSet.CONCUR_UPDATABLE + 2);
            ResultSet rsjdbc = stmt.executeQuery(query);
            DBResultSet rs = new DBResultSet(null, rsjdbc, table1)) {

            // Verify resultset behavior
            rs.next();
            rs.afterLast();
            rs.previous();
            rs.verify(table1);
        }
        System.out.println("ending testStmtSSScrollDynamicOptimisticCC");
    }

    /**
     * Create a statement TYPE_SS_SEVER_CURSOR_FORWARD_ONLY, CONCUR_READ_ONLY, executeQuery verify cursor by using next and verify data
     * 
     * @throws SQLException
     */
    @Test
    public void testStmtSserverCursorForwardOnly() throws SQLException {

        DBResultSetTypes rsType = DBResultSetTypes.TYPE_FORWARD_ONLY_CONCUR_READ_ONLY;
        String query = "SELECT * FROM " + table1.getEscapedTableName();
        System.out.println("starting testStmtSserverCursorForwardOnly");
        try (Connection conn = DriverManager.getConnection(connectionString);
            Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rsjdbc = stmt.executeQuery(query);
            DBResultSet rs = new DBResultSet(null, rsjdbc)) {
            // Verify resultset behavior
            rs.next();
            rs.verify(table1);
        }
        System.out.println("ending testStmtSserverCursorForwardOnly");
    }

    /**
     * Create a preparedStatement, call close
     * 
     * @throws SQLException
     */
    @Test
    public void testCreatepreparedStatement() throws SQLException {

        String colName = table1.getColumnName(7);
        String value = table1.getRowData(7, 0).toString();
        String query = "SELECT * from " + table1.getEscapedTableName() + " where [" + colName + "] = ? ";
        System.out.println("starting testCreatepreparedStatement");
        try (Connection conn = DriverManager.getConnection(connectionString);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setObject(1, new BigDecimal(value));
            ResultSet rsjdbc = pstmt.executeQuery();
            DBResultSet rs = new DBResultSet(null, rsjdbc);
            rs.verify(table1);
            rs.close();
        }
        System.out.println("ending testCreatepreparedStatement");
    }

    /**
     * Verify resultset using ResultSetMetaData
     * 
     * @throws SQLException
     */
    @Test
    public void testResultSet() throws SQLException {

        String query = "SELECT * FROM " + table1.getEscapedTableName();
        System.out.println("starting testResultSet");
        try (Connection conn = DriverManager.getConnection(connectionString);
             Statement stmt = conn.createStatement();
            ResultSet rsjdbc = stmt.executeQuery(query);
            DBResultSet rs = new DBResultSet(null, rsjdbc)) {
            // verify resultSet
            rs.verify(table1);
        }
        System.out.println("ending testResultSet");
    }

    /**
     * Verify resultset and close resultSet
     * 
     * @throws SQLException
     */
    @Test
    public void testResultSetAndClose() throws SQLException {

        String query = "SELECT * FROM " + table1.getEscapedTableName();
        
        try (Connection conn = DriverManager.getConnection(connectionString);
             Statement stmt = conn.createStatement();
            ResultSet rsjdbc = stmt.executeQuery(query);
            DBResultSet rs = new DBResultSet(null, rsjdbc)) {

            try {
                if (null != rs)
                    rs.close();
            }
            catch (SQLException e) {
                fail(e.toString());
            }
        }
    }

    /**
     * Verify two concurrent resultsets from same connection, separate statements
     * 
     * @throws SQLException
     */
    @Test
    public void testTwoResultsetsDifferentStmt() throws SQLException {
    	
        String query = "SELECT * FROM " + table1.getEscapedTableName();
        String query2 = "SELECT * FROM " + table2.getEscapedTableName();
        System.out.println("starting testTwoResultsetsDifferentStmt");
        try (Connection conn = DriverManager.getConnection(connectionString);
             Statement stmt1 = conn.createStatement();
             Statement stmt2 = conn.createStatement()) {
            
            ResultSet rsjdbc1 = stmt1.executeQuery(query);
            DBResultSet rs1 = new DBResultSet(null, rsjdbc1);
                    
            ResultSet rsjdbc2 = stmt2.executeQuery(query2);
            DBResultSet rs2 = new DBResultSet(null, rsjdbc2);
            
            // Interleave resultset calls
            rs1.next();
            rs1.verifyCurrentRow(table1);
            rs2.next();
            rs2.verifyCurrentRow(table2);
            rs1.next();
            rs1.verifyCurrentRow(table1);
            rs1.verify(table1);
            rs1.close();
            rs2.next();
            rs2.verify(table2);
            rs2.close();
        }
        System.out.println("ending testTwoResultsetsDifferentStmt");
    }

    /**
     * Verify two concurrent resultsets from same connection, same statement
     * 
     * @throws SQLException
     */
    @Test
    public void testTwoResultsetsSameStmt() throws SQLException {

        String query = "SELECT * FROM " + table1.getEscapedTableName();
        String query2 = "SELECT * FROM " + table2.getEscapedTableName();
        System.out.println("starting testTwoResultsetsSameStmt");
        try (Connection conn = DriverManager.getConnection(connectionString);
             Statement stmt = conn.createStatement()) {

            ResultSet rsjdbc1 = stmt.executeQuery(query);
            DBResultSet rs1 = new DBResultSet(null, rsjdbc1);
                    
            ResultSet rsjdbc2 = stmt.executeQuery(query2);
            DBResultSet rs2 = new DBResultSet(null, rsjdbc2);
            // Interleave resultset calls. rs is expected to be closed
            try {
                rs1.next();
            }
            catch (SQLException e) {
                assertEquals(e.getMessage(), TestResource.getResource("R_resultsetClosed"));        
            }
            rs2.next();
            rs2.verifyCurrentRow(table2);
            try {
                rs1.next();
            }
            catch (SQLException e) {
                assertEquals(e.getMessage(), TestResource.getResource("R_resultsetClosed"));
            }
            rs1.close();
            rs2.next();
            rs2.verify(table2);
            rs2.close();
        }
        System.out.println("ending testTwoResultsetsSameStmt");
    }

    /**
     * Verify resultset closed after statement is closed
     * 
     * @throws SQLException
     */
    @Test
    public void testResultSetAndCloseStmt() throws SQLException {
        System.out.println("starting testResultSetAndCloseStmt");
        String query = "SELECT * FROM " + table1.getEscapedTableName();
        try (Connection conn = DriverManager.getConnection(connectionString);
             Statement stmt = conn.createStatement();
            ResultSet rsjdbc = stmt.executeQuery(query);
            DBResultSet rs = new DBResultSet(null, rsjdbc)) {

            stmt.close(); // this should close the resultSet
            try {
                rs.next();
            }
            catch (SQLException e) {
                assertEquals(e.getMessage(), TestResource.getResource("R_resultsetClosed"));
            }
            assertTrue(true, "Previous one should have thrown exception!");
        }
        System.out.println("ending testResultSetAndCloseStmt");
    }

    /**
     * Verify resultset using SelectMethod
     * 
     * @throws SQLException
     */
    @Test
    public void testResultSetSelectMethod() throws SQLException {
        System.out.println("starting testResultSetSelectMethod");
        String query = "SELECT * FROM " + table1.getEscapedTableName();
        try (Connection conn = DriverManager.getConnection(connectionString + ";selectMethod=cursor;");
             Statement stmt = conn.createStatement();
            ResultSet rsjdbc = stmt.executeQuery(query);
            DBResultSet rs = new DBResultSet(null, rsjdbc)) {
            rs.verify(table1);
        }
        System.out.println("ending testResultSetSelectMethod");
    }

    /**
     * drops tables
     * 
     * @throws SQLException
     */
    @AfterEach
    public void terminate() throws SQLException {

        try (Connection conn = DriverManager.getConnection(connectionString);
             Statement stmt = conn.createStatement()) {
            stmt.execute("if object_id('" + table1.getEscapedTableName() + "','U') is not null" + " drop table " + table1.getEscapedTableName());
            stmt.execute("if object_id('" + table2.getEscapedTableName() + "','U') is not null" + " drop table " + table2.getEscapedTableName());
        }
    }
}
