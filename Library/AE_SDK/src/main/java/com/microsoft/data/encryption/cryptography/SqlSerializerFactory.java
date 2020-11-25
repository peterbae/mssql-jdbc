/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Provides methods for getting serializer implementations, such as by type and ID.
 *
 */
public class SqlSerializerFactory extends SerializerFactory {
    
    public final static String BIGINT_ID = "bigint";
    public final static String BINARY_ID = "binary";
    public final static String BIT_ID = "bit";
    public final static String CHAR_ID = "char";
    public final static String DATE_ID = "date";
    public final static String DATETIME2_ID = "datetime2";
    public final static String DATETIMEOFFSET_ID = "datetimeoffset";
    public final static String DATETIME_ID = "datetime";
    public final static String DECIMAL_ID = "decimal";
    public final static String FLOAT_ID = "float";
    public final static String INTEGER_ID = "integer";
    public final static String MONEY_ID = "money";
    public final static String NCHAR_ID = "nchar";
    public final static String NVARCHAR_ID = "nvarchar";
    public final static String REAL_ID = "real";
    public final static String SMALLDATETIME_ID = "smalldatetime";
    public final static String SMALLINT_ID = "smallint";
    public final static String SMALLMONEY_ID = "smallmoney";
    public final static String TIME_ID = "time";
    public final static String TINYINT_ID = "tinyint";
    public final static String UNIQUEIDENTIFIER_ID = "uniqueidentifier";
    public final static String VARBINARY_ID = "varbinary";
    public final static String VARCHAR_ID = "varchar";
    
    private static Map<String, ISerializer> serializerByIdentifier = new ConcurrentHashMap<String, ISerializer>();
    private static Map<Type, ISerializer> serializerByType = new ConcurrentHashMap<Type, ISerializer>();

    @Override
    public ISerializer getSerializer(String id) throws AAPSDKException {
        ISerializer s = serializerByIdentifier.get(id);
        if (null == s) {
            s = createSerializer(id, 0, 0, 0, false);
        }
        return s;
    }

    /**
     * Returns a cached instance of the ISerializer or, if not present, creates a new one.
     * 
     * @param id
     *        The type of serializer to get or create.
     * @param size
     *        The maximum size of value.
     * @param precision
     *        The maximum number of digits.
     * @param scale
     *        The number of decimal places.
     * @return
     * @throws AAPSDKException
     */
    public static ISerializer getOrCreate(String id, int size, int precision, int scale) throws AAPSDKException {
        ISerializer s = serializerByType.get(new Type(id, size, precision, scale));
        if (null == s) {
            s = createSerializer(id, size, precision, scale, true);
        }
        return s;
    }

    /**
     * Returns a cached instance of the ISerializer or, if not present, creates a new one.
     * 
     * @param id
     *        The type of serializer to get or create.
     * @param size
     *        The maximum size of value.
     * @param precision
     *        The maximum number of digits.
     * @param scale
     *        The number of decimal places.
     * @return
     * @throws AAPSDKException
     */
    public static ISerializer getOrCreate(String id, int size, int precision, int scale,
            String codepage) throws AAPSDKException {
        return createSerializer(id, size, precision, scale, true, codepage);
    }

    private static ISerializer createSerializer(String id, int size, int precision, int scale, boolean isByType,
            String codepage) throws AAPSDKException {
        ISerializer s = null;
        switch (id.toLowerCase()) {
            case "char":
                s = new SqlCharSerializer(size, precision, scale);
                ((SqlCharSerializer) s).setCodepage(codepage);
                break;
            case "varchar":
                s = new SqlVarcharSerializer(size, precision, scale);
                ((SqlVarcharSerializer) s).setCodepage(codepage);
                break;
            case "nchar":
                s = new SqlNCharSerializer(size, precision, scale);
                ((SqlNCharSerializer) s).setCodepage(codepage);
                break;
            case "nvarchar":
                s = new SqlNVarcharSerializer(size, precision, scale);
                ((SqlNVarcharSerializer) s).setCodepage(codepage);
                break;
            default:
                throw new AAPSDKException(AAPSDKResource.getResource("R_InvalidSerializerName"));
        }
        return s;
    }

    private static ISerializer createSerializer(String id, int size, int precision, int scale,
            boolean isByType) throws AAPSDKException {
        ISerializer s = null;
        switch (id.toLowerCase()) {
            case BIGINT_ID:
                s = new SqlBigIntSerializer(size, precision, scale);
                break;
            case BINARY_ID:
                s = new SqlBinarySerializer(size, precision, scale);
                break;
            case BIT_ID:
                s = new SqlBooleanSerializer(size, precision, scale);
                break;
            case CHAR_ID:
                s = new SqlCharSerializer(size, precision, scale);
                break;
            case DATE_ID:
                s = new SqlDateSerializer(size, precision, scale);
                break;
            case DATETIME2_ID:
                s = new SqlDateTime2Serializer(size, precision, scale);
                break;
            case DATETIMEOFFSET_ID:
                s = new SqlDateTimeOffsetSerializer(size, precision, scale);
                break;
            case DATETIME_ID:
                s = new SqlDateTimeSerializer(size, precision, scale);
                break;
            case DECIMAL_ID:
                s = new SqlDecimalSerializer(size, precision, scale);
                break;
            case FLOAT_ID:
                s = new SqlFloatSerializer(size, precision, scale);
                break;
            case INTEGER_ID:
                s = new SqlIntegerSerializer(size, precision, scale);
                break;
            case MONEY_ID:
                s = new SqlMoneySerializer(size, precision, scale);
                break;
            case NCHAR_ID:
                s = new SqlNCharSerializer(size, precision, scale);
                break;
            case NVARCHAR_ID:
                s = new SqlNVarcharSerializer(size, precision, scale);
                break;
            case REAL_ID:
                s = new SqlRealSerializer(size, precision, scale);
                break;
            case SMALLDATETIME_ID:
                s = new SqlSmalldatetimeSerializer(size, precision, scale);
                break;
            case SMALLINT_ID:
                s = new SqlSmallintSerializer(size, precision, scale);
                break;
            case SMALLMONEY_ID:
                s = new SqlSmallmoneySerializer(size, precision, scale);
                break;
            case TIME_ID:
                s = new SqlTimeSerializer(size, precision, scale);
                break;
            case TINYINT_ID:
                s = new SqlTinyintSerializer(size, precision, scale);
                break;
            case UNIQUEIDENTIFIER_ID:
                s = new SqlUniqueidentifierSerializer(size, precision, scale);
                break;
            case VARBINARY_ID:
                s = new SqlVarbinarySerializer(size, precision, scale);
                break;
            case VARCHAR_ID:
                s = new SqlVarcharSerializer(size, precision, scale);
                break;
            default:
                throw new AAPSDKException(AAPSDKResource.getResource("R_InvalidSerializerName"));
        }
        if (isByType) {
            serializerByType.put(new Type(id, size, precision, scale), s);
        } else {
            serializerByIdentifier.put(id, s);
        }
        return s;
    }

    @Override
    public <T> ISerializer getDefaultSerializer(T o) throws AAPSDKException {
        return getSerializer(o.getClass().getName());
    }

    @Override
    public void registerSerializer(String typeName, ISerializer sqlSerializer, boolean overrideDefault) {
        serializerByIdentifier.put(typeName, sqlSerializer);
    }
}
