/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/**
 * A DataEncryptionKey, protected by a KeyEncryptionKey, that is used to encrypt and decrypt data.
 *
 */
public class ProtectedDataEncryptionKey extends DataEncryptionKey {

    private KeyEncryptionKey keyEncryptionKey;
    private byte[] encryptedValue;
    
    /**
     * Getter for keyEncryptionKey.
     * @return keyEncryptionKey value.
     */
    public KeyEncryptionKey getKeyEncryptionKey() {
        return keyEncryptionKey;
    }
    
    /**
     * Getter for encryptedValue.
     * @return encryptedValue value.
     */
    public byte[] getEncryptedValue() {
        return encryptedValue;
    }

    /**
     * Returns an instance of ProtectedDataEncryptionKey.
     * 
     * @param name
     *        The name by which the ProtectedDataEncryptionKey
     * @param keyEncryptionKey
     *        Specifies the the KeyEncryptionKey used for encrypting and decrypting the ProtectedDataEncryptionKey.
     * @param encryptedKey
     *        The encrypted ProtectedDataEncryptionKey value.
     * @return An ProtectedDataEncryptionKey object.
     * @throws Exception
     */
    public static ProtectedDataEncryptionKey getOrCreate(String name, KeyEncryptionKey keyEncryptionKey,
            byte[] encryptedKey) throws Exception {
        Utils.validateNotNullOrWhitespace(name, "name");
        Utils.validateNotNull(keyEncryptionKey, "keyEncryptionKey");
        Utils.validateNotNull(encryptedKey, "encryptedKey");

        return new ProtectedDataEncryptionKey(name, keyEncryptionKey, encryptedKey);
    }

    /**
     * Initializes a new instance of the ProtectedDataEncryptionKey class derived from generating an array of bytes with
     * a cryptographically strong random sequence of values.
     * 
     * @param name
     *        The name by which the ProtectedDataEncryptionKey will be known.
     * @param keyEncryptionKey
     *        Specifies the the KeyEncryptionKey used for encrypting and decrypting the ProtectedDataEncryptionKey.
     * @throws NoSuchAlgorithmException
     * @throws AAPSDKException
     * @throws InvalidKeyException
     */
    public ProtectedDataEncryptionKey(String name,
            KeyEncryptionKey keyEncryptionKey) throws NoSuchAlgorithmException, AAPSDKException, InvalidKeyException {
        this(name, keyEncryptionKey, generateNewColumnEncryptionKey(keyEncryptionKey));
    }

    /**
     * Initializes a new instance of the ProtectedDataEncryptionKey class derived from decrypting the encryptedKey.
     * @param name The name by which the ProtectedDataEncryptionKey will be known.
     * @param keyEncryptionKey Specifies the the KeyEncryptionKey used for encrypting and decrypting the ProtectedDataEncryptionKey.
     * @param encryptedKey The encrypted ProtectedDataEncryptionKey value.
     * @throws AAPSDKException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    public ProtectedDataEncryptionKey(String name, KeyEncryptionKey keyEncryptionKey,
            byte[] encryptedKey) throws AAPSDKException, InvalidKeyException, NoSuchAlgorithmException {
        super(name, keyEncryptionKey.decryptEncryptionKey(encryptedKey));

        Utils.validateNotNullOrWhitespace(name, "name");
        Utils.validateNotNull(keyEncryptionKey, "keyEncryptionKey");

        this.keyEncryptionKey = keyEncryptionKey;
        encryptedValue = encryptedKey;
    }

    /**
     * Initializes a new instance of the ProtectedDataEncryptionKey class derived from enerating an array of bytes with a cryptographically strong random sequence of values.
     * @param name
     * @param rootKey he name by which the ProtectedDataEncryptionKey will be known.
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     * @throws Exception Specifies the the KeyEncryptionKey used for encrypting and decrypting the ProtectedDataEncryptionKey.
     */
    public ProtectedDataEncryptionKey(String name, byte[] rootKey) throws AAPSDKException, InvalidKeyException, NoSuchAlgorithmException {
        super(name, rootKey);

        Utils.validateNotNullOrWhitespace(name, "name");
        Utils.validateNotNull(rootKey, "rootkey");
    }

    private static byte[] generateNewColumnEncryptionKey(
            KeyEncryptionKey masterKey) throws NoSuchAlgorithmException, AAPSDKException {
        byte[] plainTextColumnEncryptionKey = new byte[32];
        SecureRandom.getInstanceStrong().nextBytes(plainTextColumnEncryptionKey);
        return masterKey.encryptEncryptionKey(plainTextColumnEncryptionKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProtectedDataEncryptionKey)) {
            return false;
        }

        ProtectedDataEncryptionKey other = (ProtectedDataEncryptionKey) obj;

        if (null == keyEncryptionKey && null != other.keyEncryptionKey) {
            return false;
        }

        return name.equals(other.name) && keyEncryptionKey.equals(other.keyEncryptionKey)
                && rootKeyHexString.equals(other.rootKeyHexString);
    }

    @Override
    public int hashCode() {
        return new Triple<String, KeyEncryptionKey, String>(name, keyEncryptionKey, rootKeyHexString).hashCode();
    }
}
