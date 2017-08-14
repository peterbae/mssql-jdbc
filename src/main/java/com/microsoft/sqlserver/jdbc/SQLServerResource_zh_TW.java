package com.microsoft.sqlserver.jdbc;

import java.util.*;

public final class SQLServerResource_zh_TW extends ListResourceBundle {
    protected Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
        {"R_timedOutBeforeRouting","連接至路由目的地前連接逾期。"},
        {"R_invalidRoutingInfo","收到非預期的路由資訊。請檢查您的連接屬性及 SQL Server 設定。"},
        {"R_multipleRedirections","發生兩個以上的重新導向。每一個登入嘗試只允許有一個重新導向。"},
        {"R_dbMirroringWithMultiSubnetFailover","不支援使用 multiSubnetFailover 連接選項連接到鏡像 SQL Server 執行個體。"},
        {"R_dbMirroringWithReadOnlyIntent","不支援使用 ApplicationIntent ReadOnly 連接選項連接到鏡像 SQL Server 執行個體。"},
        {"R_ipAddressLimitWithMultiSubnetFailover","不支援使用 multiSubnetFailover 連接屬性連接到設定了超過 {0} 個 IP 位址的 SQL Server 執行個體。"},
        {"R_connectionTimedOut","連接逾時: 沒有進一步的資訊"},
        {"R_invalidPositionIndex","位置索引 {0} 無效。"},
        {"R_invalidLength","長度 {0} 無效。"},
        {"R_unknownSSType","無效的 SQL Server 資料類型 {0}。"},
        {"R_unknownJDBCType","無效的 JDBC 資料類型 {0}。"},
        {"R_notSQLServer","驅動程式收到非預期的登入前回應。請確認連接屬性並檢查 SQL Server 的執行個體是否正在主機上執行，而且通訊埠可接收 TCP/IP 連接。此驅動程式只可以搭配 SQL Server 2000 或更新版本使用。"},
        {"R_tcpOpenFailed","{0}。確認連接屬性。確認 SQL Server 執行個體是否在主機上執行並接受在通訊埠的 TCP/IP 連接。確認防火牆未封鎖通訊埠的 TCP 連接。"},
        {"R_unsupportedJREVersion","此驅動程式不支援 Java Runtime Environment (JRE) 版本 {0}。請使用提供 JDBC 4.0 支援的 sqljdbc4.jar 類別庫。"},
        {"R_unsupportedServerVersion","此驅動程式不支援 SQL Server 版本 {0}。"},
        {"R_noServerResponse","SQL Server 未傳回回應。已關閉連接。"},
        {"R_truncatedServerResponse","SQL Server 傳回的回應不完整。已關閉連接。"},
        {"R_queryTimedOut","查詢逾時。"},
        {"R_queryCancelled","查詢已取消。"},
        {"R_errorReadingStream","讀取資料流物件中的值時發生錯誤。錯誤: \"{0}\""},
        {"R_streamReadReturnedInvalidValue","資料流讀取作業傳回的資料讀取量值無效。"},
        {"R_mismatchedStreamLength","資料流值不是指定的長度。指定的長度是 {0}，實際的長度是 {1}。"},
        {"R_notSupported","不支援此作業。"},
        {"R_invalidOutputParameter","輸出參數的索引 {0} 無效。"},
        {"R_outputParameterNotRegisteredForOutput","沒有為輸出登錄輸出參數 {0}。"},
        {"R_parameterNotDefinedForProcedure","沒有為預存程序 {1} 定義參數 {0}。"},
        {"R_connectionIsClosed","連接已關閉。"},
        {"R_invalidBooleanValue","屬性 {0} 不包含有效的布林值。只可以使用 true 或 false。"},
        {"R_propertyMaximumExceedsChars","{0} 屬性超出 {1} 個字元的上限。"},
        {"R_invalidPortNumber","連接埠號碼 {0} 無效。"},
        {"R_invalidTimeOut","timeout {0} 無效。"},
        {"R_invalidLockTimeOut","lockTimeOut {0} 無效。"},
        {"R_invalidAuthenticationScheme","authenticationScheme {0} 無效。"},
        {"R_invalidPacketSize","packetSize {0} 無效。"},
        {"R_packetSizeTooBigForSSL","SSL 加密無法與大小超過 {0} 位元組的網路封包一起使用。請檢查您的連接屬性以及 SQL Server 組態。"},
        {"R_tcpipConnectionFailed","連接到主機 {0} (連接埠 {1}) 的 TCP/IP 連接已經失敗。錯誤: \"{2}\"。"},
        {"R_invalidTransactionLevel","交易層級 {0} 無效。"},
        {"R_cantInvokeRollback","AutoCommit 模式設為 \"true\" 時，無法叫用回復作業。"},
        {"R_cantSetSavepoint","AutoCommit 模式設為 \"true\" 時無法設定儲存點。"},
        {"R_sqlServerHoldability","SQL Server 僅在連接層級支援保留性。請使用 connection.setHoldability() 方法。"},
        {"R_invalidHoldability","保留性的值 {0} 無效。"},
        {"R_invalidColumnArrayLength","資料行陣列無效。長度必須為 1。"},
        {"R_valueNotSetForParameter","未設定參數號碼 {0} 的值。"},
        {"R_sqlBrowserFailed","連接到主機 {0}，具名執行個體 {1} 的連接失敗。錯誤: \"{2}\"。請確認伺服器和執行個體名稱，並檢查防火牆未封鎖連到通訊埠 1434 的 UDP 流量。如果是 SQL Server 2005 或更新版本，請確認 SQL Server Browser Service 正在主機上執行。"},
        {"R_notConfiguredToListentcpip","未將伺服器 {0} 設定為使用 TCP/IP 來接聽。"},
        {"R_cantIdentifyTableMetadata","無法識別中繼資料的資料表 {0}。"},
        {"R_metaDataErrorForParameter","發生參數 {0} 的中繼資料錯誤。"},
        {"R_invalidParameterNumber","參數號碼 {0} 無效。"},
        {"R_noMetadata","沒有中繼資料。"},
        {"R_resultsetClosed","結果集已關閉。"},
        {"R_invalidColumnName","資料行名稱 {0} 無效。"},
        {"R_resultsetNotUpdatable","結果集無法更新。"},
        {"R_indexOutOfRange","索引 {0} 超出範圍。"},
        {"R_savepointNotNamed","savepoint 未命名。"},
        {"R_savepointNamed","savepoint {0} 已命名。"},
        {"R_resultsetNoCurrentRow","結果集目前沒有資料列。"},
        {"R_mustBeOnInsertRow","資料指標不在插入資料列上。"},
        {"R_mustNotBeOnInsertRow","無法在插入資料列上執行要求的作業。"},
        {"R_cantUpdateDeletedRow","無法更新已刪除的資料列。"},
        {"R_noResultset","陳述式沒有傳回結果集。"},
        {"R_resultsetGeneratedForUpdate","產生結果集以進行更新。"},
        {"R_statementIsClosed","陳述式已關閉。"},
        {"R_invalidRowcount","結果集的最大資料列計數 {0} 必須為非負整數。"},
        {"R_invalidQueryTimeOutValue","查詢逾時值 {0} 無效。"},
        {"R_invalidFetchDirection","提取方向 {0} 無效。"},
        {"R_invalidFetchSize","提取大小不能為負值。"},
        {"R_noColumnParameterValue","未指定資料行參數值以更新資料列。"},
        {"R_statementMustBeExecuted","取得任何結果之前必須執行陳述式。"},
        {"R_modeSuppliedNotValid","提供的模式無效。"},
        {"R_variantNotSupported","不支援 \"variant\" 資料類型。"},
        {"R_errorConnectionString","連接字串包含錯誤格式的名稱或值。"},
        {"R_errorProcessingComplexQuery","處理複雜查詢時發生錯誤。"},
        {"R_invalidOffset","位移 {0} 無效。"},
        {"R_nullConnection","連接 URL 為 Null。"},
        {"R_invalidConnection","連接 URL 無效。"},
        {"R_cannotTakeArgumentsPreparedOrCallable","方法 {0} 無法在 PreparedStatement 或 CallableStatement 上使用引數。"},
        {"R_unsupportedConversionFromTo","不支援從 {0} 轉換到 {1}。"},
        {"R_errorConvertingValue","將 {0} 值轉換為 JDBC 資料類型 {1} 時發生錯誤。"},
        {"R_streamIsClosed","資料流已經關閉。"},
        {"R_invalidTDS","TDS 通訊協定資料流無效。"},
        {"R_unexpectedToken"," 非預期的 token {0}。"},
        {"R_selectNotPermittedinBatch","批次中不允許使用 SELECT 陳述式。"},
        {"R_failedToCreateXAConnection","無法建立 XA 控制連接。錯誤: \"{0}\"。"},
        {"R_codePageNotSupported","Java 環境不支援字碼頁 {0}。"},
        {"R_unknownSortId","此驅動程式不支援 SQL Server 定序 {0}。"},
        {"R_unknownLCID","此驅動程式不支援 Windows 定序 {0}。"},
        {"R_encodingErrorWritingTDS","將字串寫入 TDS 緩衝區時發生編碼錯誤。錯誤: \"{0}\"。"},
        {"R_processingError","發生處理錯誤 \"{0}\"。"},
        {"R_requestedOpNotSupportedOnForward","不支援在順向結果集上執行要求的作業。"},
        {"R_unsupportedCursor","不支援此資料指標類型。"},
        {"R_unsupportedCursorOperation","不支援使用此資料指標類型來執行要求的作業。"},
        {"R_unsupportedConcurrency","不支援並行。"},
        {"R_unsupportedCursorAndConcurrency","不支援此資料指標類型/並行的組合。"},
        {"R_stringReadError","在位移: {0} 發生字串讀取錯誤。"},
        {"R_stringWriteError","在位移: {0} 發生字串寫入錯誤。"},
        {"R_stringNotInHex","字串不是有效的十六進位格式。"},
        {"R_unknownType","Java 類型 {0} 不是受支援的類型。"},
        {"R_physicalConnectionIsClosed","此共用連接的實體連接已關閉。"},
        {"R_invalidDataSourceReference","無效的 DataSource 參照。"},
        {"R_cantGetColumnValueFromDeletedRow","無法從已刪除的資料列取得值。"},
        {"R_cantGetUpdatedColumnValue","直到呼叫 updateRow() 或 cancelRowUpdates() 之後，才能存取已更新的資料行。"},
        {"R_cantUpdateColumn","資料行值無法更新。"},
        {"R_positionedUpdatesNotSupported","不支援定位更新及刪除。"},
        {"R_invalidAutoGeneratedKeys","autoGeneratedKeys 參數值 {0} 無效。只可以使用 Statement.RETURN_GENERATED_KEYS 及 Statement.NO_GENERATED_KEYS 值。"},
        {"R_notConfiguredForIntegrated","並未針對整合式驗證設定此驅動程式。"},
        {"R_failoverPartnerWithoutDB","使用 failoverPartner 連接屬性時需要 databaseName。"},
        {"R_invalidPartnerConfiguration","並未針對資料庫鏡像設定伺服器 {1} 上的資料庫 {0}。"},
        {"R_invaliddisableStatementPooling","disableStatementPooling 值 {0} 無效。"},
        {"R_invalidselectMethod","selectMethod {0} 無效。"},
        {"R_invalidpropertyValue","連接屬性 {0} 的資料類型無效。此連接的所有屬性必須是字串類型。"},
        {"R_invalidArgument","引數 {0} 無效。"},
        {"R_streamWasNotMarkedBefore","尚未標示資料流。"},
        {"R_invalidresponseBuffering","responseBuffering 連接屬性 {0} 無效。"},
        {"R_invalidapplicationIntent","applicationIntent 連接屬性 {0} 無效。"},
        {"R_dataAlreadyAccessed","資料已存取，此資料行或參數無法使用。"},
        {"R_outParamsNotPermittedinBatch","批次中不允許 OUT 及 INOUT 參數。"},
        {"R_sslRequiredNoServerSupport","驅動程式無法使用安全通訊端層 (SSL) 加密建立與 SQL Server 的安全連接。應用程式已要求加密但伺服器未設定成支援 SSL。"},
        {"R_sslRequiredByServer","SQL Server 登入需要使用安全通訊端層 (SSL) 的加密連接。"},
        {"R_sslFailed","驅動程式無法使用安全通訊端層 (SSL) 加密建立與 SQL Server 的安全連接。錯誤: \"{0}\"。"},
        {"R_certNameFailed","無法驗證安全通訊端層 (SSL) 初始化期間憑證中的伺服器名稱。"},
        {"R_failedToInitializeXA","無法初始化預存程序 xp_sqljdbc_xa_init。狀態為: {0}。錯誤: \"{1}\""},
        {"R_failedFunctionXA","函數 {0} 失敗。狀態為 : {1}。錯誤: \"{2}\""},
        {"R_noTransactionCookie","函數 {0} 失敗。未傳回交易 Cookie。"},
        {"R_failedToEnlist","無法編列。錯誤: \"{0}\""},
        {"R_failedToUnEnlist","無法取消編列。錯誤: \"{0}\""},
        {"R_failedToReadRecoveryXIDs","無法讀取復原 XA 分支交易 ID (XID)。錯誤: \"{0}\""},
        {"R_userPropertyDescription","資料庫使用者。"},
        {"R_passwordPropertyDescription","資料庫密碼。"},
        {"R_databaseNamePropertyDescription","要連接的資料庫名稱。"},
        {"R_serverNamePropertyDescription","執行 SQL Server 的電腦。"},
        {"R_portNumberPropertyDescription","SQL Server 執行個體在接聽的 TCP 連接埠。"},
        {"R_serverSpnPropertyDescription","SQL Server SPN。"},
        {"R_columnEncryptionSettingPropertyDescription","資料行加密設定。"},
        {"R_serverNameAsACEPropertyDescription","依據 RFC 3490 的 ToASCII 作業定義，將 serverName 從 Unicode 轉譯成 ASCII 相容編碼 (ASCII Compatible Encoding (ACE))。"},
        {"R_sendStringParametersAsUnicodePropertyDescription","判斷字串參數要以 Unicode 或資料庫的字元集傳送到伺服器。"},
        {"R_multiSubnetFailoverPropertyDescription","指出應用程式是否正在連接至 Availability Group 或 Failover Cluster Instance 的 Availability Group Listener。"},
        {"R_applicationNamePropertyDescription","SQL Server 分析和記錄工具的應用程式名稱。"},
        {"R_lastUpdateCountPropertyDescription","確定傳送至伺服器的 SQL 陳述式只會傳回最後的更新記數。"},
        {"R_disableStatementPoolingPropertyDescription","停用陳述式共用功能。"},
        {"R_integratedSecurityPropertyDescription","指定是否使用 Windows 驗證來連接 SQL Server。"},
        {"R_authenticationSchemePropertyDescription","要用於整合式驗證的驗證配置。"},
        {"R_lockTimeoutPropertyDescription","資料庫回報鎖定逾時之前，要等候的毫秒數。"},
        {"R_loginTimeoutPropertyDescription","開始計算失敗連接的逾時之前，驅動程式要等候的秒數。"},
        {"R_instanceNamePropertyDescription","要連接的 SQL Server 執行個體名稱。"},
        {"R_xopenStatesPropertyDescription","判斷驅動程式是否要在例外狀況中傳回符合 XOPEN 的 SQL 狀態碼。"},
        {"R_selectMethodPropertyDescription","讓應用程式可以使用伺服器資料指標處理順向且唯讀的結果集。"},
        {"R_responseBufferingPropertyDescription","控制適應性緩衝行為，讓應用程式不需要伺服器資料指標就可以處理大型結果集。"},
        {"R_applicationIntentPropertyDescription","宣告連接到伺服器時的應用程式工作負載類型。可能的值為 [ReadOnly] 和 [ReadWrite]。"},
        {"R_workstationIDPropertyDescription","工作站的主機名稱。"},
        {"R_failoverPartnerPropertyDescription","資料庫鏡像組態中使用的容錯移轉伺服器名稱。"},
        {"R_packetSizePropertyDescription","與 SQL Server 通訊時使用的網路封包大小。"},
        {"R_encryptPropertyDescription","判斷是否應該在用戶端和伺服器之間使用安全通訊端層 (SSL) 加密。"},
        {"R_trustServerCertificatePropertyDescription","判斷驅動程式是否應該驗證 SQL 安全通訊端層 (SSL) 憑證。"},
        {"R_trustStoreTypePropertyDescription","信任存放區類型 (例如 JKS / PKCS12) 或任何 FIPS 提供者金鑰儲存區實作類型的類型。"},
        {"R_trustStorePropertyDescription","憑證信任存放區檔案的路徑。"},
        {"R_trustStorePasswordPropertyDescription","檢查信任存放區資料完整性時使用的密碼。"},
        {"R_hostNameInCertificatePropertyDescription","驗證 SQL Server 安全通訊端層 (SSL) 憑證時要使用的主機名稱。"},
        {"R_sendTimeAsDatetimePropertyDescription","決定是否使用 SQL Server datetime 資料類型將 java.sql.Time 值傳送至資料庫。"},
        {"R_TransparentNetworkIPResolutionPropertyDescription","指定是否要使用無形網路 IP 解析功能。"},
        {"R_queryTimeoutPropertyDescription","資料庫回報查詢逾時之前，要等候的秒數。"},
        {"R_socketTimeoutPropertyDescription","在引發 java.net.SocketTimeoutException 之前，要等候的毫秒數。"},
        {"R_serverPreparedStatementDiscardThresholdPropertyDescription","在伺服器上關閉已捨棄 prepare 陳述式的時機閾值 (呼叫一批次的 sp_unprepares)。1 (含) 以下的值會造成 sp_unprepare 在 PreparedStatment 關閉時立即呼叫。"},
        {"R_enablePrepareOnFirstPreparedStatementCallPropertyDescription","這項設定會指定準備好的陳述式是在第一次使用時 (property=true) 準備的 (sp_prepexec)，還是在第一次呼叫 sp_executesql 後的第二次時準備的 (property=false)。"},
        {"R_statementPoolingCacheSizePropertyDescription","此設定指定為連線準備之陳述式快取的大小。小於 1 的值表示沒有快取。"},
        {"R_gsscredentialPropertyDescription","用以存取 SQL Server 的模擬 GSS 認證。"},
        {"R_noParserSupport","起始必要的剖析器時發生錯誤。錯誤: \"{0}\""},
        {"R_writeOnlyXML","無法讀取此 SQLXML 執行個體。此執行個體只能進行資料寫入。"},
        {"R_dataHasBeenReadXML","無法讀取此 SQLXML 執行個體。資料已經讀取。"},
        {"R_readOnlyXML","無法寫入此 SQLXML 執行個體。此執行個體只能進行資料讀取。"},
        {"R_dataHasBeenSetXML","無法寫入此 SQLXML 執行個體。資料已經設定。"},
        {"R_noDataXML","此 SQLXML 執行個體中沒有設定任何資料。"},
        {"R_cantSetNull","無法設定 Null 值。"},
        {"R_failedToParseXML","無法剖析 XML。錯誤: \"{0}\""},
        {"R_isFreed","此 {0} 物件已經釋放，無法再存取。"},
        {"R_invalidProperty","不支援此屬性: {0}。"},
        {"R_referencingFailedTSP","必須設定 DataSource trustStore 密碼。"},
        {"R_valueOutOfRange","一個或多個值不在 {0} SQL Server 資料類型之值的範圍內。"},
        {"R_integratedAuthenticationFailed","整合式驗證失敗。"},
        {"R_permissionDenied","違反安全性。目標 \"{0}\" 的權限遭拒。"},
        {"R_getSchemaError","取得預設結構描述名稱時發生錯誤。"},
        {"R_setSchemaWarning","警告: setSchema 在此驅動程式版本中不作業。"},
        {"R_updateCountOutofRange","更新計數值超出範圍。"},
        {"R_limitOffsetNotSupported","不支援限制逸出順序中的 OFFSET 子句。"},
        {"R_limitEscapeSyntaxError","限制逸出語法中發生錯誤。無法剖析查詢。"},
        {"R_featureNotSupported","不支援 {0}。"},
        {"R_zoneOffsetError","擷取時區時差時發生錯誤。"},
        {"R_invalidMaxRows","支援的結果集最大資料行計數為 Integer.MAX_VALUE Z 以下。"},
        {"R_schemaMismatch","來源與目的地結構描述不相符。"},
        {"R_invalidColumn","資料行 {0} 無效。請檢查您的資料行對應。"},
        {"R_invalidDestinationTable","目的地資料表名稱遺漏或無效。"},
        {"R_unableRetrieveColMeta","無法擷取資料行中繼資料。"},
        {"R_invalidDestConnection","目的地連接必須是來自 Microsoft JDBC Driver for SQL Server 的連接。"},
        {"R_unableRetrieveSourceData","無法從來源擷取資料。"},
        {"R_ParsingError","無法剖析 {0} 類型的資料。"},
        {"R_BulkTypeNotSupported","大量複製不支援資料類型 {0}。"},
        {"R_invalidTransactionOption","當 UseInternalTransaction 選項與 Connection 物件搭配使用時，不可設為 TRUE。"},
        {"R_invalidNegativeArg","{0} 引數不可為負數。"},
        {"R_BulkColumnMappingsIsEmpty","如果唯一的對應是識別欄位且 KeepIdentity 設為 false，則無法執行大量複製作業。"},
        {"R_BulkCSVDataSchemaMismatch","來源資料不符合來源結構描述。"},
        {"R_CSVDataSchemaMismatch","來源資料不符合來源結構描述。"},
        {"R_BulkCSVDataDuplicateColumn","不允許重複的資料行名稱。"},
        {"R_invalidColumnOrdinal","資料行 {0} 無效。資料行數目必須大於零。"},
        {"R_unsupportedEncoding","不支援編碼 {0}。"},
        {"R_UnexpectedDescribeParamFormat","內部錯誤。sp_describe_parameter_encryption 傳回的結果集格式無效。缺少其中一個結果集。"},
        {"R_InvalidEncryptionKeyOridnal","內部錯誤。sp_describe_parameter_encryption 傳回的加密中繼資料中缺少所參考的資料行加密金鑰序數 \"{0}\"。最大序數為 \"{1}\"。"},
        {"R_MissingParamEncryptionMetadata","內部錯誤。sp_describe_parameter_encryption 傳回的結果集中缺少陳述式或程序 \"{0}\" 中某些參數的中繼資料。"},
        {"R_UnableRetrieveParameterMetadata","無法擷取參數加密中繼資料。"},
        {"R_InvalidCipherTextSize","指定的加密文字大小 ({0} 個位元組) 無效，低於解密所需的下限值 {1} 個位元組。"},
        {"R_InvalidAlgorithmVersion","指定加密文字的加密演算法版本 {0} 與預期的加密演算法版本 {1} 不符。"},
        {"R_InvalidAuthenticationTag","指定的加密文字包含無效的驗證標籤。"},
        {"R_EncryptionFailed","加密時發生內部錯誤: {0} "},
        {"R_DecryptionFailed","解密時發生內部錯誤: {0} "},
        {"R_InvalidKeySize","資料行加密金鑰已成功解密，但其長度 {0} 與演算法 \"{2}\" 的長度 {1} 不符。請驗證資料庫中資料行加密金鑰的加密值。"},
        {"R_InvalidEncryptionType","為資料庫資料行指定的加密類型 {0} 無效或已毀損。演算法 {1} 的有效加密類型為: {2}。"},
        {"R_UnknownColumnEncryptionAlgorithm","沒有演算法 {0}。Factory 中登錄的演算法為 {1}。"},
        {"R_KeyExtractionFailed","擷取金鑰失敗: {0}。"},
        {"R_UntrustedKeyPath","從伺服器 {1} 收到的資料行主要金鑰路徑 {0} 不是信任的金鑰路徑。該資料行主要金鑰路徑可能已毀損，或您應使用 SQLServerConnection.setColumnEncryptionTrustedMasterKeyPaths() 將 {0} 設為信任的金鑰路徑。"},
        {"R_UnrecognizedKeyStoreProviderName","無法解密資料行加密金鑰。金鑰存放區提供者名稱無效: {0}。金鑰存放區提供者名稱必須代表系統金鑰存放區提供者或已登錄的自訂金鑰存放區提供者。有效的系統金鑰提供者名稱: {1}。有效 (目前已登錄) 的自訂金鑰存放區提供者名稱: {2}。請檢查資料庫中資料行主要金鑰定義中的金鑰存放區提供者資訊，並確認您應用程式中所使用之自訂金鑰存放區提供者的登錄正確。"},
        {"R_UnsupportedDataTypeAE","不支援加密及解密資料類型 {0}。"},
        {"R_NormalizationErrorAE","解密資料類型 {0} 失敗。正規化錯誤。"},
        {"R_UnsupportedNormalizationVersionAE","從 SQL Server 收到的正規化版本 \"{0}\" 無效或已毀損。有效的正規化版本: {1}。"},
        {"R_NullCipherTextAE","內部錯誤。Ciphertext 值不得為 null。"},
        {"R_NullColumnEncryptionAlgorithmAE","內部錯誤。加密演算法不得為 null。有效的演算法: {1}。"},
        {"R_CustomCipherAlgorithmNotSupportedAE","不支援自訂加密演算法。"},
        {"R_PlainTextNullAE","內部錯誤。Plaintext 值不得為 null。"},
        {"R_StreamingDataTypeAE","加密 {1} 資料行不支援長於 {0} 的資料長度。"},
        {"R_AE_NotSupportedByServer","使用的 SQL Server 執行個體不支援資料行加密。"},
        {"R_InvalidAEVersionNumber","收到的 Always Encrypted 版本號碼 \"{0}\" 無效。"},
        {"R_NullEncryptedColumnEncryptionKey","內部錯誤。加密資料行的加密金鑰鑰不得為 null。"},
        {"R_EmptyEncryptedColumnEncryptionKey","內部錯誤。指定了空的加密資料行加密金鑰。"},
        {"R_InvalidMasterKeyDetails","指定了無效的主要金鑰詳細資料。"},
        {"R_CertificateError","從金鑰儲存區 \"{1}\" 擷取憑證 \"{0}\" 時發生錯誤。"},
        {"R_ByteToShortConversion","解密資料行加密金鑰時發生錯誤。"},
        {"R_InvalidCertificateSignature","指定之加密資料行的加密金鑰簽章與從 \"{0}\" 中之資料行主要金鑰 (憑證) 計算所得的簽章不符。加密資料行的加密金鑰可能已毀損，或指定的路徑可能不正確。"},
        {"R_CEKDecryptionFailed","解密加密資料行的加密金鑰時發生例外狀況: {0} "},
        {"R_NullKeyEncryptionAlgorithm","金鑰加密演算法不得為 null。"},
        {"R_NullKeyEncryptionAlgorithmInternal","內部錯誤。金鑰加密演算法不得為 null。"},
        {"R_InvalidKeyEncryptionAlgorithm","指定了無效的加密演算法: {0}。預期的值: {1}。"},
        {"R_InvalidKeyEncryptionAlgorithmInternal","內部錯誤。指定了無效的金鑰加密演算法: {0}。預期的值: {1}。"},
        {"R_NullColumnEncryptionKey","資料行加密金鑰不得為 null。"},
        {"R_EmptyColumnEncryptionKey","指定了空的資料行加密金鑰。"},
        {"R_CertificateNotFoundForAlias","在 {1} 提供的存放區中找不到別名為 {0} 的憑證。請確認該憑證已正確地匯入憑證位置/存放區。"},
        {"R_UnrecoverableKeyAE","無法使用憑證詳細資料 {0} 從 Keystore 復原私密金鑰。請確認匯入的 AE 憑證包含私密金鑰，以及提供的憑證密碼正確。"},
        {"R_KeyStoreNotFound","系統在指定的路徑中找不到金鑰存放區檔案。請確認該路徑正確，以及您有該路徑的適當存取權。"},
        {"R_CustomKeyStoreProviderMapNull","資料行加密金鑰存放區提供者對應不得為 null。應為非 null 值。"},
        {"R_EmptyCustomKeyStoreProviderName","指定了無效的金鑰存放區提供者名稱。金鑰存放區提供者名稱不得為 null 或空白。"},
        {"R_InvalidCustomKeyStoreProviderName","金鑰存放區提供者名稱 {0} 無效。前置詞 {1} 已保留供系統金鑰存放區提供者使用。"},
        {"R_CustomKeyStoreProviderValueNull","為金鑰存放區提供者 {0} 指定了 null 參考。應為非 null 值。"},
        {"R_CustomKeyStoreProviderSetOnce","金鑰存放區提供者不得重複設定。"},
        {"R_unknownColumnEncryptionType","資料行加密類型 {0} 無效。"},
        {"R_unsupportedStmtColEncSetting","SQLServerStatementColumnEncryptionSetting 不得為 null。"},
        {"R_unsupportedConversionAE","加密資料行不支援從 {0} 轉換成 {1}。"},
        {"R_InvalidDataForAE","資料來源之類型 {0} 的指定值無法轉換成指定目標資料行的類型 {1}。"},
        {"R_authenticationPropertyDescription","要使用的驗證。"},
        {"R_accessTokenPropertyDescription","Azure Active Directory 所要使用的存取權杖。"},
        {"R_FedAuthRequiredPreLoginResponseInvalidValue","伺服器傳送了未預期的 FedAuthRequired PreLogin 選項值。該值為 {0}。"},
        {"R_FedAuthInfoLengthTooShortForCountOfInfoIds","FedAuthInfo 權杖至少須包含 4 個位元組指出資訊識別碼的數量。"},
        {"R_FedAuthInfoInvalidOffset","FedAuthInfoDataOffset 指向無效的位置。目前的 dataOffset 為 {0}。"},
        {"R_FedAuthInfoFailedToReadData","無法讀取 FedAuthInfoData。"},
        {"R_FedAuthInfoLengthTooShortForData","FEDAUTHINFO 權杖資料流的長度不足 ({0})，無法包含其宣告的資料。"},
        {"R_FedAuthInfoDoesNotContainStsurlAndSpn","FEDAUTHINFO 權杖資料流未同時包含 STSURL 及 SPN。"},
        {"R_ADALExecution","Active Directory 無法驗證使用者 {0} (Authentication = {1})。"},
        {"R_UnrequestedFeatureAckReceived","收到未要求的功能認可。功能識別碼: {0}。"},
        {"R_FedAuthFeatureAckContainsExtraData","ADAL 與 Security Token 的同盟驗證功能延伸模組認可中包含了額外的資料。"},
        {"R_FedAuthFeatureAckUnknownLibraryType","正在嘗試使用未知的同盟驗證程式庫。程式庫識別碼: {0}。"},
        {"R_UnknownFeatureAck","收到未知功能認可。"},
        {"R_SetAuthenticationWhenIntegratedSecurityTrue","當 \"IntegratedSecurity\" 設為 \"true\" 時，無法設定 \"Authentication\"。"},
        {"R_SetAccesstokenWhenIntegratedSecurityTrue","當 \"IntegratedSecurity\" 連接字串關鍵字設為 \"true\" 時，無法設定 AccessToken 屬性。"},
        {"R_IntegratedAuthenticationWithUserPassword","\"Authentication=ActiveDirectoryIntegrated\" 不得與 \"User\"、\"UserName\" 或 \"Password\" 等連接字串關鍵字並用。"},
        {"R_AccessTokenWithUserPassword","若在連接字串中指定 \"User\"、\"UserName\" 或 \"Password\"，無法設定 AccessToken 屬性。"},
        {"R_AccessTokenCannotBeEmpty","AccesToken 不得空白。"},
        {"R_SetBothAuthenticationAndAccessToken","當連接字串中指定了 \"Authentication\" 時，無法設定 AccessToken 屬性。"},
        {"R_NoUserPasswordForActivePassword","當 \"Authentication=ActiveDirectoryPassword\" 時，必須同時指定 \"User\" (或 \"UserName\") 及 \"Password\" 連接字串關鍵字。"},
        {"R_NoUserPasswordForSqlPassword","當 \"Authentication=SqlPassword\" 時，必須同時指定 \"User\" (或 \"UserName\") 及 \"Password\" 連接字串關鍵字。"},
        {"R_ForceEncryptionTrue_HonorAEFalse","因為陳述式或程序 {1} 未啟用加密，所以無法將參數 {0} 的 [強制加密] 設為 true。"},
        {"R_ForceEncryptionTrue_HonorAETrue_UnencryptedColumn","因為參數 {1} 的 [強制加密] 設為 true，且資料庫需要此參數以純文字格式送出，所以無法執行陳述式或程序 {0}。可能是設定錯誤所致。"},
        {"R_ForceEncryptionTrue_HonorAEFalseRS","因為陳述式或程序未啟用加密，所以無法將參數 {0} 的 [強制加密] 設為 true。"},
        {"R_ForceEncryptionTrue_HonorAETrue_UnencryptedColumnRS","因為參數 {0} 的 [強制加密] 設為 true，且資料庫需要此參數以純文字格式送出，所以無法執行更新。可能是設定錯誤所致。"},
        {"R_NullValue","{0} 不得為 null。"},
        {"R_AKVPathNull","Azure Key Vault 金鑰路徑不得為 null。"},
        {"R_AKVURLInvalid","指定的 URL 無效: {0}。"},
        {"R_AKVMasterKeyPathInvalid","指定了無效的 Azure Key Vault 金鑰路徑: {0}。"},
        {"R_EmptyCEK","指定了空的資料行加密金鑰。"},
        {"R_EncryptedCEKNull","加密資料行的加密金鑰不得為 null。"},
        {"R_EmptyEncryptedCEK","加密資料行的加密金鑰長度不得為零。"},
        {"R_NonRSAKey","無法使用非 RSA 金鑰: {0}。"},
        {"R_GetAKVKeySize","無法取得 Azure Key Vault 公用金鑰大小 (位元組)。"},
        {"R_InvalidEcryptionAlgorithmVersion","指定加密資料行的加密金鑰包含無效的加密演算法版本 {0}。預期的版本為 {1}。"},
        {"R_AKVKeyLengthError","指定的加密資料行加密金鑰 ciphertext 長度 {0} 與在 {2} 中使用資料行主要金鑰 (Azure Key Vault 金鑰) 時的加密文字長度 {1} 不符。該加密資料行的加密金鑰可能已毀損，或指定的 Azure Key Vault 金鑰路徑可能不正確。"},
        {"R_AKVSignatureLengthError","指定的加密資料行加密金鑰簽章長度 {0} 與在 {2} 中使用資料行主要金鑰 (Azure Key Vault 金鑰) 時的簽章長度 {1} 不符。該加密資料行的加密金鑰可能已毀損，或指定的 Azure Key Vault 金鑰路徑可能不正確。"},
        {"R_HashNull","解密加密資料行的加密金鑰時，雜湊不得為 null。"},
        {"R_NoSHA256Algorithm","不支援 SHA-256 演算法。"},
        {"R_VerifySignature","無法驗證資料行加密金鑰的簽章。"},
        {"R_CEKSignatureNotMatchCMK","指定了加密資料行的加密金鑰，但其簽章與從 {0} 中之資料行主要金鑰 (Azure Key Vault 中的非對稱金鑰) 計算所得的簽章不符。該加密資料行的加密金鑰可能已毀損，或指定的路徑可能不正確。"},
        {"R_DecryptCEKError","無法使用指定的 Azure Key Vault 金鑰解密 CEK。"},
        {"R_EncryptCEKError","無法使用指定的 Azure Key Vault 金鑰加密 CEK。"},
        {"R_CipherTextLengthNotMatchRSASize","CipherText 長度與 RSA 金鑰大小不符。"},
        {"R_GenerateSignature","無法使用指定的 Azure Key Vault 金鑰 URL 產生簽章。"},
        {"R_SignedHashLengthError","簽署的雜湊長度與 RSA 金鑰大小不符。"},
        {"R_InvalidSignatureComputed","計算所得之加密資料行加密金鑰的簽章無效。"},
        {"R_UnableLoadADALSqlDll","無法載入 adalsql.dll。錯誤碼: 0x{0}。如需詳細資料，請參閱: http://go.microsoft.com/fwlink/?LinkID=513072"},
        {"R_ADALAuthenticationMiddleErrorMessage","錯誤碼 0x{0}; 狀態 {1}。"},
        {"R_unsupportedDataTypeTVP","Table-Valued Parameter 不支援資料類型 {0}。"},
        {"R_moreDataInRowThanColumnInTVP","輸入陣列比此資料表中的資料行數長。"},
        {"R_invalidTVPName","Table-Valued Parameter 的類型名稱必須有效。"},
        {"R_invalidThreePartName","TypeName 的 3 部分名稱無效。"},
        {"R_unsupportedConversionTVP","Table-Valued Parameter 不支援從 {0} 轉換成 {1}。"},
        {"R_TVPMixedSource","無法新增資料行中繼資料。此 Table-Valued Parameter 具有 ResultSet，而中繼資料會由此衍生。"},
        {"R_TVPEmptyMetadata","Structured 類型中的欄位數不足。Structured 類型至少須有一個欄位。"},
        {"R_TVPInvalidValue","為 Table-Valued Parameter {0} 提供的值無效。僅支援 SQLServerDataTable、ResultSet 及 ISQLServerDataRecord 物件。"},
        {"R_TVPInvalidColumnValue","輸入資料的格式錯誤。"},
        {"R_AADIntegratedOnNonWindows","只有 Windows 作業系統支援 ActiveDirectoryIntegrated。"},
        {"R_TVPSortOrdinalGreaterThanFieldCount","欄位 {1} 中的排序序數 {0} 超出了欄位數總計。"},
        {"R_TVPMissingSortOrderOrOrdinal","排序順序與序數必須同時指定或同時不指定 (SortOrder.Unspecified 及 -1)。指定的值: 順序 = {0}，序數 = {1}。"},
        {"R_TVPDuplicateSortOrdinal","排序序數 {0} 指定了兩次。"},
        {"R_TVPMissingSortOrdinal","排序序數 {0} 未指定。"},
        {"R_TVPDuplicateColumnName","資料行名稱 {0} 已屬於此 SQLServerDataTable。"},
        {"R_InvalidConnectionSetting","{0} 的值 \"{1}\" 無效。"},
        {"R_InvalidWindowsCertificateStoreEncryption","無法使用 Windows Certificate Store 加密資料行的加密金鑰。"},
        {"R_AEKeypathEmpty","內部錯誤。憑證路徑不得為 null。請使用下列格式:「憑證位置/憑證存放區/憑證指紋」，其中「憑證位置」可以是 LocalMachine 或 CurrentUser。"},
        {"R_AEWinApiErr","Windows API 原生錯誤。"},
        {"R_AECertpathBad","內部錯誤。憑證路徑 {0} 無效。請使用下列格式:「憑證位置/憑證存放區/憑證指紋」，其中「憑證位置」可以是 LocalMachine 或 CurrentUser。"},
        {"R_AECertLocBad","內部錯誤。憑證路徑 {1} 中的憑證位置 {0} 無效。請使用下列格式:「憑證位置/憑證存放區/憑證指紋」，其中「憑證位置」可以是 LocalMachine 或 CurrentUser。"},
        {"R_AECertStoreBad","內部錯誤。憑證路徑 {1} 中指定的憑證存放區 {0} 無效。預期的值: My。"},
        {"R_AECertHashEmpty","內部錯誤。憑證路徑 {0} 中指定了空的憑證指紋。"},
        {"R_AECertNotFound","在憑證位置 {0} 的憑證存放庫 {1} 中找不到指紋為 {2} 的憑證。請確認資料庫中資料行主要金鑰定義中的憑證路徑正確，且憑證已正確地匯入憑證位置/存放區。"},
        {"R_AEMaloc","記憶體配置失敗。"},
        {"R_AEKeypathLong","內部錯誤。指定的憑證路徑共有 {0} 個位元組，超過了 {1} 個位元組的長度上限。"},
        {"R_AEECEKLenBad","指定的加密資料行加密金鑰 ciphertext 長度 {0} 與在 \"{2}\" 中使用資料行主要金鑰 (憑證) 時的加密文字長度 {1} 不符。該加密資料行的加密金鑰可能已毀損，或指定的憑證路徑可能不正確。"},
        {"R_AEECEKSigLenBad","指定的加密資料行加密金鑰簽章長度 {0} 與在 \"{2}\" 中使用資料行主要金鑰 (憑證) 時的簽章長度 {1} 不符。該加密資料行的加密金鑰可能已毀損，或指定的憑證路徑可能不正確。"},
        {"R_AEKeyPathEmptyOrReserved","憑證路徑 \"{0}\" 無效。該路徑為空白，或包含保留的目錄名稱。"},
        {"R_AEKeyPathCurUser","在金鑰路徑中指定了 CurrentUser，但在取得目前使用者的初始工作目錄時發生錯誤。"},
        {"R_AEKeyFileOpenError","開啟憑證檔案 {0} 時發生錯誤。"},
        {"R_AEKeyFileReadError","讀取憑證檔案 {0} 時發生錯誤。"},
        {"R_keyStoreAuthenticationPropertyDescription","指出金鑰存放區的名稱。"},
        {"R_keyStoreSecretPropertyDescription","驗證祕密或尋找祕密所需的資訊。"},
        {"R_keyStoreLocationPropertyDescription","金鑰儲存位置。"},
        {"R_fipsProviderPropertyDescription","FIPS 提供者。"},
        {"R_keyStoreAuthenticationNotSet","如有指定 \"{0}\"，便須指定 \"keyStoreAuthentication\" 連接字串關鍵字。"},
        {"R_keyStoreSecretOrLocationNotSet","若連接字串中已指定 \"keyStoreAuthentication=JavaKeyStorePassword\"，則 \"keyStoreSecret\" 與 \"keyStoreLocation\" 皆需設定。"},
        {"R_certificateStoreInvalidKeyword","若連接字串中已指定 \"keyStoreAuthentication=CertificateStore\"，則無法設定 \"keyStoreSecret\"。"},
        {"R_certificateStoreLocationNotSet","若連接字串中已指定 \"keyStoreAuthentication=CertificateStore\"，則必須指定 \"keyStoreLocation\"。"},
        {"R_certificateStorePlatformInvalid","無法在 Windows 作業系統上設定 \"keyStoreAuthentication=CertificateStore\"。"},
        {"R_invalidKeyStoreFile","無法剖析 \"{0}\"。檔案格式無效，或密碼不正確。"},
        {"R_invalidCEKCacheTtl","指定的資料行加密金鑰快取存留時間無效。columnEncryptionKeyCacheTtl 值不可為負值，且 timeUnit 只能是 DAYS、HOURS、MINUTES 或 SECONDS。"},
        {"R_sendTimeAsDateTimeForAE","搭配 Always Encrypted 使用 sendTimeAsDateTime=false。"},
        {"R_TVPnotWorkWithSetObjectResultSet","資料表值參數不支援具有 ResultSet 的 setObject()。請使用 setStructured()"},
        {"R_invalidQueryTimeout","queryTimeout {0} 無效。"},
        {"R_invalidSocketTimeout","socketTimeout {0} 無效。"},
        {"R_fipsPropertyDescription","判斷是否要啟用用戶端與伺服器之間符合 FIPS 規範的 SSL 連線。"},
        {"R_invalidFipsConfig","無法啟用 FIPS。"},
        {"R_invalidFipsEncryptConfig","因為加密並非 true 或未使用信任的憑證設定，所以無法啟用 FIPS。"},
        {"R_invalidFipsProviderConfig","因為 FIPSProvider 或 TrustStoreType 無效，所以無法啟用 FIPS。"},
        {"R_serverPreparedStatementDiscardThreshold","serverPreparedStatementDiscardThreshold {0} 無效。"},
        {"R_statementPoolingCacheSize","statementPoolingCacheSize {0} 無效。"},
        {"R_kerberosLoginFailedForUsername","無法使用 Kerberos 主體 {0} 登入，請檢查您的認證。{1}"},
        {"R_kerberosLoginFailed","Kerberos 登入失敗: {0} 原因為 {1} ({2})"},
        {"R_StoredProcedureNotFound","找不到預存程序 ''''{0}''''。"},
        {"R_jaasConfigurationNamePropertyDescription","Kerberos 驗證的登入設定檔。"},
    };
}
