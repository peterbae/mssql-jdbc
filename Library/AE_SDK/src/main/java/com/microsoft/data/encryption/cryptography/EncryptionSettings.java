/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

/**
 * Contains the settings to configure how encryption operations are performed on data.
 *
 */
public abstract class EncryptionSettings {
    public DataEncryptionKey dataEncryptionKey;

    /**
     * Getter for dataEncryptionKey.
     * @return dataEncryptionKey value
     */
    public DataEncryptionKey getDataEncryptionKey() {
        return dataEncryptionKey;
    }

    protected void setDataEncryptionKey(DataEncryptionKey d) {
        if (null == d) {
            encryptionType = EncryptionType.Plaintext;
        }
        dataEncryptionKey = d;
    }

    public EncryptionType encryptionType = EncryptionType.Randomized;

    /**
     * Getter for encryptionType.
     * @return encryptionType value
     */
    public EncryptionType getEncryptionType() {
        return encryptionType;
    }

    protected void setEncryptionType(EncryptionType e) {
        encryptionType = e;
    }

    /**
     * Gets the ISerializer.
     * @return ISerializer instance
     */
    public abstract ISerializer GetSerializer();

    protected static EncryptionType GetDefaultEncryptionType(DataEncryptionKey d) {
        return (null == d) ? EncryptionType.Plaintext : EncryptionType.Randomized;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof EncryptionSettings)) {
            return false;
        }

        if (null == dataEncryptionKey && null != ((EncryptionSettings) other).dataEncryptionKey) {
            return false;
        }

        return dataEncryptionKey.equals(((EncryptionSettings) other).dataEncryptionKey)
                && encryptionType.equals(((EncryptionSettings) other).encryptionType)
                && GetSerializer().getClass() == ((EncryptionSettings) other).GetSerializer().getClass();
    }
}
