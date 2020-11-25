/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

/**
 * Contains the methods for serializing and deserializing data objects.
 *
 */
public interface ISerializer {
    /**
     * Serializes the provided value.
     * @param value The value to be serialized
     * @return The serialized data as a byte array
     * @throws AAPSDKException
     */
    byte[] serialize(Object value) throws AAPSDKException;

    /**
     * Deserializes the provided bytes
     * @param bytes The data to be deserialized
     * @return The serialized data
     * @throws AAPSDKException
     */
    Object deserialize(byte[] bytes) throws AAPSDKException;
}

/**
 * Contains the methods for serializing and deserializing data objects.
 *
 */
abstract class Serializer<T> implements ISerializer {
    // The Identifier uniquely identifies a particular Serializer implementation.
    public String identifier;

    public String getIdentifier() {
        return identifier;
    }

    /**
     * Serializes the provided value.
     * @param value The value to be serialized
     * @return The serialized data as a byte array
     */
    public abstract byte[] serialize(Object value) throws AAPSDKException;
    
    /**
     * Deserializes the provided value.
     * @param value The value to be deserialized
     * @return The deserialized data
     */
    public abstract T deserialize(byte[] bytes) throws AAPSDKException;
}
