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
public class StandardSerializerFactory extends SerializerFactory {

    private static Map<String, ISerializer> serializers = new ConcurrentHashMap<>();

    @Override
    public ISerializer getSerializer(String id) throws AAPSDKException {
        ISerializer s = serializers.get(id);
        if (null == s) {
            s = createSerializer(id);
        }
        return s;
    }
    
    public static ISerializer getOrCreate(String id) throws AAPSDKException {
        ISerializer s = serializers.get(id);
        if (null == s) {
            s = createSerializer(id);
        }
        return s;
    }

    @Override
    public <T> Serializer<T> getDefaultSerializer(T o) throws AAPSDKException {
        return (Serializer<T>) getSerializer(o.getClass().getName());
    }
    
    /**
     * Gets a default registered serializer for the type.
     * @param clazz The data type to be serialized.
     * @return A default registered serializer for the type.
     * @throws AAPSDKException
     */
    public <T> Serializer<T> getDefaultSerializer(Class<T> clazz) throws AAPSDKException {
        return (Serializer<T>) getSerializer(clazz.getName());
    }

    @Override
    public void registerSerializer(String typeName, ISerializer s, boolean overrideDefault) {
        serializers.put(typeName, s);
    }

    private static Serializer<?> createSerializer(String id) throws AAPSDKException {
        Serializer<?> s = null;
        switch (id) {
            case "java.lang.Boolean":
                s = new BooleanSerializer();
                break;
            case "java.lang.Integer":
                s = new IntegerSerializer();
                break;
            case "java.lang.Byte":
                s = new ByteSerializer();
                break;
            case "java.lang.Double":
                s = new DoubleSerializer();
                break;
            case "java.lang.Float":
                s = new FloatSerializer();
                break;
            case "java.lang.String":
                s = new StringSerializer();
                break;
            case "java.lang.Character":
                s = new CharSerializer();
                break;
            case "java.util.UUID":
                s = new UuidSerializer();
                break;
            default:
                throw new AAPSDKException(AAPSDKResource.getResource("R_InvalidSerializerName"));
        }
        serializers.put(id, s);
        return s;
    }
}
