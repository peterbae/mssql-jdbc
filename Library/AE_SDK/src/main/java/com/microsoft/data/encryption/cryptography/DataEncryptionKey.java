/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

import static java.nio.charset.StandardCharsets.UTF_16LE;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;


/**
 * An encryption key that is used to encrypt and decrypt data.
 *
 */
abstract class DataEncryptionKey {
    protected String rootKeyHexString;
    private final int KEY_SIZE_IN_BITS = 256;
    public final int KEY_SIZE_IN_BYTES = KEY_SIZE_IN_BITS / 8;

    public String name;
    private byte[] rootKeyBytes;
    private byte[] encryptionKeyBytes;
    private byte[] macKeyBytes;
    private byte[] ivKeyBytes;

    protected DataEncryptionKey(String name,
            byte[] rootKey) throws AAPSDKException, InvalidKeyException, NoSuchAlgorithmException {
        if (name == null || name.trim().isEmpty()) {
            throw new AAPSDKException(AAPSDKResource.getResource("R_InvalidDataEncryptionKey"));
        }

        if (KEY_SIZE_IN_BYTES != rootKey.length) {
            MessageFormat form = new MessageFormat(AAPSDKResource.getResource("R_InvalidKeySize"));
            Object[] msgArgs = {KEY_SIZE_IN_BYTES};
            throw new AAPSDKException(form.format(msgArgs));
        }

        this.name = name;

        String encryptionKeySalt = "Microsoft SQL Server cell encryption key with encryption algorithm:AEAD_AES_256_CBC_HMAC_SHA256 and key length:"
                + KEY_SIZE_IN_BITS;
        String macKeySalt = "Microsoft SQL Server cell MAC key with encryption algorithm:AEAD_AES_256_CBC_HMAC_SHA256 and key length:"
                + KEY_SIZE_IN_BITS;
        String ivKeySalt = "Microsoft SQL Server cell IV key with encryption algorithm:AEAD_AES_256_CBC_HMAC_SHA256 and key length:"
                + KEY_SIZE_IN_BITS;

        byte[] encryptionKeyBytes = SqlServerSecurityUtility.getHMACWithSHA256(encryptionKeySalt.getBytes(UTF_16LE),
                rootKey, KEY_SIZE_IN_BYTES);
        byte[] macKeyBytes = SqlServerSecurityUtility.getHMACWithSHA256(macKeySalt.getBytes(UTF_16LE), rootKey,
                KEY_SIZE_IN_BYTES);
        byte[] ivKeyBytes = SqlServerSecurityUtility.getHMACWithSHA256(ivKeySalt.getBytes(UTF_16LE), rootKey,
                KEY_SIZE_IN_BYTES);

        this.rootKeyBytes = rootKey;
        this.encryptionKeyBytes = encryptionKeyBytes;
        this.macKeyBytes = macKeyBytes;
        this.ivKeyBytes = ivKeyBytes;
    }

    /**
     * Getter for name.
     * 
     * @return name of this DataEncryptionKey object.
     */
    public String getName() {
        return name;
    }

    protected void setName(String value) {
        name = value;
    }

    protected byte[] getRootKeyBytes() {
        return rootKeyBytes;
    }

    protected void setRootKeyBytes(byte[] value) {
        rootKeyBytes = value;
        rootKeyHexString = CryptographyExtensions.toBase64String(value);
    }

    protected String getRootKeyHexString() {
        return rootKeyHexString;
    }

    protected byte[] getEncryptionKeyBytes() {
        return encryptionKeyBytes;
    }

    protected void setEncryptionKeyBytes(byte[] value) {
        encryptionKeyBytes = value;
    }

    protected byte[] getMacKeyBytes() {
        return macKeyBytes;
    }

    protected void setMacKeyBytes(byte[] value) {
        macKeyBytes = value;
    }

    protected byte[] getIvKeyBytes() {
        return ivKeyBytes;
    }

    protected void setIvKeyBytes(byte[] value) {
        ivKeyBytes = value;
    }
}
