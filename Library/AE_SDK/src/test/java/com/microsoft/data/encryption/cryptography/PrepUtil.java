/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;


/**
 * Utility Class for Tests. This will contains methods like Create Table, Drop Table, Initialize connection, create
 * statement etc. logger settings etc.
 * 
 */
public class PrepUtil {

    private PrepUtil() {
        // Just hide to restrict constructor invocation.
    }

    /**
     * It will create SQLServerConnection
     * 
     * @param connectionString
     * @param info
     * @return SQLServerConnection
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static SQLServerConnection getConnection(String connectionString, Properties info) throws SQLException {
        return (SQLServerConnection) DriverManager.getConnection(connectionString, info);
    }

    /**
     * It will create SQLServerConnection
     * 
     * @param connectionString
     * @return SQLServerConnection
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static SQLServerConnection getConnection(String connectionString) throws SQLException {
        return getConnection(connectionString, null);
    }

}
