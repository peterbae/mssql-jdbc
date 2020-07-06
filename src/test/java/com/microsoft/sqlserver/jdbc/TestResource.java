/*
 * Microsoft JDBC Driver for SQL Server Copyright(c) Microsoft Corporation All rights reserved. This program is made
 * available under the terms of the MIT License. See the LICENSE file in the project root for more information.
 */

package com.microsoft.sqlserver.jdbc;

import java.util.ListResourceBundle;

import com.microsoft.sqlserver.testframework.Constants;


/**
 * A simple resource bundle containing the strings for localizing.
 *
 */
public final class TestResource extends ListResourceBundle {
    public static String getResource(String key) {
        return TestResource.getBundle(Constants.MSSQL_JDBC_PACKAGE + ".TestResource").getString(key);
    }

    public static String formatErrorMsg(String resource) {
        return (".*\\Q" + getResource(resource) + "\\E").replaceAll("\\{+[0-9]+\\}", "\\\\E.*\\\\Q");
    }

    protected Object[][] getContents() {
        return contents;
    }

    // the keys must be prefixed with R_ to denote they are resource strings and their names should follow the
    // camelCasing
    // convention and be descriptive
    static final Object[][] contents = {{"R_wrongEnv", "Aborting test: As this is not the right environement: "},
            {"R_fipsPropertyNotSet", "Aborting test case as FIPS_ENV property is not set."},
            {"R_invalidTrustCert", "Invalid TrustServerCertificate value."},
            {"R_invalidEncrypt", "Invalid encrypt value."}, {"R_notImplemented", "not implemented"},
            {"R_resultsetClosed", "The result set is closed."}, {"R_statementClosed", "The statement is closed."},
            {"R_resultsetNull", "The result set is null."},
            {"R_invalidFipsConfig", "Unable to verify FIPS mode settings."},
            {"R_shouldBeEnabled", "should be enabled."},
            {"R_StoredProcedureNotFound", "Could not find stored procedure"},
            {"R_givenValueType", "The given value of type"},
            {"R_lengthTruncated", " The inserted length is truncated or not correct!"},
            {"R_timeValueTruncated", " The time value is truncated or not correct!"},
            {"R_invalidErrorMessage", "Invalid Error Message: "},
            {"R_expectedFailPassed", "Expected failure did not fail"}, {"R_dataTypeNotFound", "Cannot find data type"},
            {"R_illegalCharWktPosition", "Illegal character in Well-Known text at position {0}."},
            {"R_illegalCharWkt", "Illegal Well-Known text. Please make sure Well-Known text is valid."},
            {"R_errorMessage", " Error message: "},
            {"R_createDropViewFailed", "Create/drop view with preparedStatement failed!"},
            {"R_createDropSchemaFailed", "Create/drop schema with preparedStatement failed!"},
            {"R_createDropTableFailed", "Create/drop table failed!"},
            {"R_tableNotFound", "Table {0} not found in database."},
            {"R_createDropAlterTableFailed", "Create/drop/alter table with preparedStatement failed!"},
            {"R_grantFailed", "grant table with preparedStatement failed!"},
            {"R_connectionIsClosed", "The connection is closed."},
            {"R_ConnectionURLNull", "The connection URL is null."},
            {"R_connectionIsNotClosed", "The connection is not closed."},
            {"R_invalidExceptionMessage", "Invalid exception message"},
            {"R_failedValidate", "failed to validate values in $0} "}, {"R_tableNotDropped", "table not dropped. "},
            {"R_connectionReset", "Connection reset"}, {"R_unknownException", "Unknown exception"},
            {"R_deadConnection", "Dead connection should be invalid"},
            {"R_wrongExceptionMessage", "Wrong exception message"},
            {"R_wrongSqlState", "Wrong sql state"},
            {"R_parameterNotDefined", "Parameter {0} was not defined"},
            {"R_unexpectedExceptionContent", "Unexpected content in exception message"},
            {"R_connectionClosed", "The connection has been closed"},
            {"R_conversionFailed", "Conversion failed when converting {0} to {1} data type"},
            {"R_invalidQueryTimeout", "The query timeout value {0} is not valid."},
            {"R_skipAzure", "Skipping test case on Azure SQL."},
            {"R_issueAzureDW", "This is a known failure in DW for now."},
            {"R_cursorAzureDW", "Cursor support is not implemented for Azure DW."},
            {"R_spatialDWNotSupported", "Geometry/Geography is not supported for DW."},
            {"R_expectedExceptionNotThrown", "Expected exception is not thrown."},
            {"R_errorNotCalled", "Error occurred is not called."}, {"R_errorCalled", "Error occurred is called."},
            {"R_supportUnwrapping", "{0} supports unwrapping."},
            {"R_cantAccessSnapshot", "Cant access the TRANSACTION_SNAPSHOT "},
            {"R_newConnectionShouldBeValid", "Newly created connection should be valid"},
            {"R_closedConnectionShouldBeInvalid", "Closed connection should be invalid"},
            {"R_noExceptionNegativeTimeout", "No exception thrown with negative timeout"},
            {"R_noExceptionClosedConnection",
                    "No exception thrown calling getClientConnectionId on a closed connection"},
            {"R_clientConnectionIdNull", "ClientConnectionId is null"},
            {"R_valuesAreDifferent", "Values are different"},
            {"R_parrentLoggerNameWrong", "Parent Logger name is wrong"},
            {"R_unexpectedWrongDB", "Unexpected: ClientConnectionId is not in exception message due to wrong DB"},
            {"R_unexpectedWrongHost", "Unexpected: ClientConnectionId is in exception message due to wrong host"},
            {"R_cannotOpenDatabase", "Cannot open database"}, {"R_shouldNotConnect", "Should not have connected"},
            {"R_loginFailed", "Login failed"}, {"R_exitedMoreSeconds", "Exited in more than {0} seconds."},
            {"R_exitedLessSeconds", "Exited in less than {0} seconds."},
            {"R_threadInterruptNotSet", "Thread's interrupt status is not set."},
            {"R_connectMirrored", "Connecting to a mirrored"},
            {"R_trustStorePasswordNotSet", "The DataSource trustStore password needs to be set."},
            {"R_invalidObjectName", "Invalid object name"},
            {"R_tempTAbleNotRemoved", "Temporary table is not removed."},
            {"R_firstConnectionNotClosed", "First connection is not closed"},
            {"R_connectionNotClosedWithPoolClose", "Connection is not closed with pool close"},
            {"R_connectionNotClosedWithPoolClose", "Unexpected: ClientConnectionId is null from Pool"},
            {"R_idFromPoolNotSame", "ClientConnection Ids from pool are not the same."},
            {"R_noProtocolVersion", "protocol version is not enabled or not supported by the client."},
            {"R_protocolException", "Any protocol other than TLSv1, TLSv1.1, and TLSv1.2 should throw Exception"},
            {"R_invalidProtocolLabel",
                    "SSL Protocol {0} label is not valid. Only TLS, TLSv1, TLSv1.1, and TLSv1.2 are supported."},
            {"R_SQLServerResourceMessage", "Message should be from SQL Server resources: "},
            {"R_shouldThrowException", "Exception should have been thrown"},
            {"R_tcpipConnectionToHost", "The TCP/IP connection to the host"},
            {"R_queryTimedOut", "The query has timed out."}, {"R_readTimedOut", "Read timed out"},
            {"R_unexpectedErrorMessage", "Unexpected error message occurred!"},
            {"R_warningsNotFound", "Warnings not found!"}, {"R_warningsFound", "Warnings found!"},
            {"R_causeShouldNotBeNull", "Cause should not be null."},
            {"R_causeShouldBeInstance", "Cause should be instance of {0}."},
            {"R_connShouldNotBeClosed", "Connection should not be closed"},
            {"R_connShouldNotBeOpen", "Connection should not be open"},
            {"R_incorrectDriverNameFormat", "Driver name is not a correct format! "},
            {"R_incorrectDriverVersionFormat", "Driver version number should be four parts! "},
            {"R_previousShouldThrow", "Previous should have thrown an exception"},
            {"R_objectMissingOrEmpty", "An object or column name is missing or empty."},
            {"R_dbNameIsCurrentDB",
                    "The database name component of the object qualifier must be the name of the current database."},
            {"R_numKeysIncorrect", "number of foreign key entries is incorrect."},
            {"R_manifestNotFound", "Manifest file does not exist on classpath so ignoring test"},
            {"R_buildVersionError", "Build version should always be same as driver version."},
            {"R_getURLContainsPwd", "Get URL should not have password attribute / property."},
            {"R_userNameNull", "Username should not be null"},
            {"R_userNameNotMatch", "Username does not match UserName from Connection String."},
            {"R_nameEmpty", "{0} name should not be empty."}, {"R_nameNull", "{0} name should not be NULL."},
            {"R_atLeastOneFound", "At least one {0} should be found."},
            {"R_noSchemaShouldFail", "As we are not supplying schema it should fail."},
            {"R_addBatchFailed", "addBatch does not add the SQL Statements to Batch, call to addBatch failed"},
            {"R_insertBatchFailed", "affected rows does not match with batch size. Insert failed"},
            {"R_savePointError", "Savepoint {0} should be {1}."},
            {"R_Incompat_SQLServerVersion",
                    "Aborting test case as SQL Server version is not compatible with Always encrypted"},
            {"R_noKeyStore", "Aborting test case as no java key store and alias name exists."},
            {"R_badStreamLength", "The stream value is not the specified length. The specified length was"},
            {"R_streamReadError", "An error occurred while reading the value from the stream object. Error"},
            {"R_SQLStateNull", "SQLState should not be null"}, {"R_blobFreed", "This Blob object has been freed."},
            {"R_streamNull", "Stream is null when data is not."}, {"R_incorrectUpdateCount", "Incorrect updateCount."},
            {"R_testInterleaved", "Test interleaved inserts and warnings"},
            {"R_errorFollowInserts", "Test error followed by inserts"},
            {"R_errorFollow50280", "Test insert followed by non-fatal error (50280)"},
            {"R_syntaxErrorDateConvert", "Syntax error converting date"},
            {"R_syntaxErrorDateConvertDW", "Conversion failed when converting date and/or time from character string."},
            {"R_dateConvertError", "Conversion failed when converting date"},
            {"R_incompatJDBC", "Aborting test case as JDBC version is not compatible."},
            {"R_unexpectedException", "Unexpected exception occurred"}, {"R_addBatchFailed", "addBatch failed"},
            {"R_executeBatchFailed", "executeBatch failed"},
            {"R_customErrorMessage", "Custom error message: col1 should be higher than 10"},
            {"R_setDataNotEqual", "Received data not equal to setdata"},
            {"R_syntaxMatchError", "Syntax translation does not match for query"},
            {"R_valueNotMatch", "Value does not match: "}, {"R_incorrectDefault", "Incorrect default"},
            {"R_shouldBeSupported", "should be supported."},
            {"R_operationNotSupported", "This operation is not supported."},
            {"R_paramNotRecognized", "Not all parameters are recognized by driver."},
            {"R_invalidGetPreparedStatementHandle",
                    "Invalid use of getPreparedStatementHandle() after statement close expected."},
            {"R_invalidserverPreparedStatementDiscardThreshold",
                    "The serverPreparedStatementDiscardThreshold %s is not valid."},
            {"R_invalidenablePrepareOnFirstPreparedStatementCall",
                    "The property enablePrepareOnFirstPreparedStatementCall does not contain a valid boolean value. Only true or false can be used."},
            {"R_cancellationFailed", "Cancellation failed."}, {"R_executionNotTimeout", "Execution did not timeout."},
            {"R_executionTooLong", "Execution took too long."},
            {"R_executionNotLong", "Execution did not take long enough."},
            {"R_queryCancelled", "The query was canceled."},
            {"R_statementShouldBeClosed", "statement should be closed since resultset is closed."},
            {"R_statementShouldBeOpened", "statement should be opened since resultset is opened."},
            {"R_shouldBeWrapper", "{0} should be a wrapper for {1}."},
            {"R_shouldNotBeWrapper", "{0} should not be a wrapper for {1}."},
            {"R_outputParamFailed", "Test for output parameter failed."},
            {"R_inputParamFailed", "Test for input parameter failed."}, {"R_decryptionFailed", "Decryption failed"},
            {"R_expectedValue", "Expected value: "}, {"R_expectedValueAtIndex", "Expected value at index: "},
            {"R_switchFailed", "Switch case is not matched with data"},
            {"R_resultsetNotInstance", "Result set is not instance of SQLServerResultSet"},
            {"R_noJRESupport", "No JRE support for {0}"},
            {"R_incorrectColumnNum", "Column name or number of supplied values does not match table definition."},
            {"R_incorrectColumnNumInsert",
                    "There are fewer columns in the INSERT statement than values specified in the VALUES clause. The number of values in the VALUES clause must match the number of columns specified in the INSERT statement."},
            {"R_incorrectColumnNumInsertDW",
                    "Column name or number of supplied values does not match table definition."},
            {"R_incorrectSyntaxTable", "Incorrect syntax near the keyword 'table'."},
            {"R_incorrectSyntaxTableDW", "Incorrect syntax near 'table'."},
            {"R_incorrectSyntaxP0", "Incorrect syntax near '@P0'."},
            {"R_ConnectionStringNull", "Connection String should not be null"},
            {"R_OperandTypeClash", "Operand type clash"},
            {"R_NoPrivilege", "The EXECUTE permission was denied on the object {0}"},
            {"R_resultSetEmpty", "Result set is empty."}, {"R_AlterAEv2Error", "Alter Column Encryption failed."},
            {"R_RichQueryError", "Rich query failed."}, {"R_reqExternalSetup", "External setup for test required."},
            {"R_invalidEnclaveSessionFailed", "invalidate enclave session failed."},
            {"R_invalidEnclaveType", "Invalid enclave type {0}."},
            {"R_keystorePassword", "keystore password was incorrect"},
            {"R_enclaveNotEnabled", "The statement triggers enclave computations"},
            {"R_aeStreamReadError", "The multi-part identifier"},
            {"R_dataClassificationNotSupported", "Data Classification is not supported on this server."}};
}
