/*
 * Microsoft JDBC Driver for SQL Server
 * 
 * Copyright(c) Microsoft Corporation All rights reserved.
 * 
 * This program is made available under the terms of the MIT License. See the LICENSE file in the project root for more information.
 */
package com.microsoft.sqlserver.jdbc.bvt;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.microsoft.sqlserver.testframework.AbstractTest;
import com.microsoft.sqlserver.testframework.DBConnection;
import com.microsoft.sqlserver.testframework.DBStatement;
import com.microsoft.sqlserver.testframework.DBTable;

/**
 * 
 * Setting up the test
 */
@RunWith(JUnitPlatform.class)
public class bvtTestSetup extends AbstractTest {

    DBTable table1;
    DBTable table2;

    @BeforeEach
    public void init() throws SQLException {
        try (DBConnection conn = new DBConnection(connectionString);
             DBStatement stmt = conn.createStatement()) {
            // create tables
            table1 = new DBTable(true);
            stmt.createTable(table1);
            stmt.populateTable(table1);
            table2 = new DBTable(true);
            stmt.createTable(table2);
            stmt.populateTable(table2);
        }
        
        System.out.println("name of t1 : " + table1.getTableName());
        System.out.println("name of t2 : " + table2.getTableName());
        
        System.out.println("total table size of t1 : " + table1.getTotalRows());
        System.out.println("total table size of t2 : " + table2.getTotalRows());
        
        System.out.println("t1 all bigint data: ");
        for (int i = 0 ; i < table1.getTotalRows(); i++) {
            System.out.println(table1.getRowData(0, i));
        }
        
        System.out.println("t2 all bigint data: ");
        for (int i = 0 ; i < table2.getTotalRows(); i++) {
            System.out.println(table2.getRowData(0, i));
        }
    }
}
