/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

/**
 * Abstract factory for creating serializers.
 *
 */
public abstract class SerializerFactory {
    
    /**
     * Gets a registered serializer by its Identifier Property.
     * @param identifier The identifier uniquely identifies a particular Serializer implementation.
     * @return The ISerializer implementation
     * @throws AAPSDKException
     */
    public abstract ISerializer getSerializer(String identifier) throws AAPSDKException;
    
    /**
     * Gets a default registered serializer for the type.
     * @param object The data type to be serialized.
     * @return A default registered serializer for the type.
     * @throws AAPSDKException
     */
    public abstract <T> ISerializer getDefaultSerializer(T object) throws AAPSDKException;

    /**
     * Registers a custom serializer.
     * @param typeName The data type on which the Serializer operates
     * @param sqlSerializer The Serializer to register
     * @param overrideDefault Determines whether to override an existing default serializer for the same type.
     */
    public abstract void registerSerializer(String typeName, ISerializer sqlSerializer, boolean overrideDefault);

}