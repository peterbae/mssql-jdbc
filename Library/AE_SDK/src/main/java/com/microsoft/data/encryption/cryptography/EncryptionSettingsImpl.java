/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

/**
 *  Contains the settings to configure how encryption operations are performed on the data of arbitrary type T.
 */
public class EncryptionSettingsImpl<T> extends EncryptionSettings {

    // Used for serializing and deserializing data objects to and from an array of bytes.
    public Serializer<T> serializer;

    public Serializer<T> getSerializer() {
        return serializer;
    }

    /**
     * Initializes a new instance of the EncryptionSettingsImpl class.
     * @param dataEncryptionKey An encryption key is used to encrypt and decrypt data.
     * @param serializer A serializer is used for serializing and deserializing data objects to and from an array of bytes.
     */
    public EncryptionSettingsImpl(DataEncryptionKey dataEncryptionKey, Serializer<T> serializer) {
        this(dataEncryptionKey, GetDefaultEncryptionType(dataEncryptionKey), serializer);
    }

    /**
     * Initializes a new instance of the EncryptionSettingsImpl class.
     * @param dataEncryptionKey An encryption key is used to encrypt and decrypt data.
     * @param encryptionType The type of encryption.
     * @param serializer A serializer is used for serializing and deserializing data objects to and from an array of bytes.
     */
    public EncryptionSettingsImpl(DataEncryptionKey dataEncryptionKey, EncryptionType encryptionType, Serializer<T> serializer) {
        this.dataEncryptionKey = dataEncryptionKey;
        this.encryptionType = encryptionType;
        this.serializer = serializer;
    }

    @Override
    public ISerializer GetSerializer() {
        return this.serializer;
    }
}
