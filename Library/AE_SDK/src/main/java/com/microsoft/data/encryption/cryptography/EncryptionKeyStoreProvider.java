/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 
 * Defines the abtract class for a SQL Server Column Encryption key store provider Extend this class to implement a
 * custom key store provider.
 *
 */
public abstract class EncryptionKeyStoreProvider {

    // A cache of key encryption keys (once they are unwrapped). This is useful for rapidly decrypting multiple data
    // values.
    protected Map<String, byte[]> dataEncryptionKeyCache = new ConcurrentHashMap<String, byte[]>();

    // A cache for storing the results of signature verification of key encryption key metadata.
    protected Map<Quadruple<String, String, Boolean, String>, Boolean> keyEncryptionKeyMetadataSignatureVerificationCache = new ConcurrentHashMap<Quadruple<String, String, Boolean, String>, Boolean>() {};

    // The unique name that identifies a particular implementation of the abstract EncryptionKeyStoreProvider.
    public String providerName;

    /**
     * Getter for provider name.
     * @return provider name
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * Unwraps the specified encryptedKey of a data encryption key. The encrypted value is expected to be encrypted
     * using the key encryption key with the specified encryptionKeyId and using the specified algorithm.
     * 
     * @param encryptionKeyId
     *        The key Id tells the provider where to find the key.
     * @param algorithm
     *        The encryption algorithm.
     * @param encryptedKey
     *        The ciphertext key.
     * @return The unwrapped data encryption key.
     */
    public abstract byte[] unwrapKey(String encryptionKeyId, KeyEncryptionKeyAlgorithm algorithm, byte[] encryptedKey) throws AAPSDKException;

    /**
     * Wraps a data encryption key using the key encryption key with the specified encryptionKeyId and using the
     * specified algorithm.
     * 
     * @param encryptionKeyId
     *        The key Id tells the provider where to find the key.
     * @param algorithm
     *        The encryption algorithm.
     * @param key
     *        The plaintext key
     * @return The wrapped data encryption key.
     */
    public abstract byte[] wrapKey(String encryptionKeyId, KeyEncryptionKeyAlgorithm algorithm, byte[] key) throws AAPSDKException;

    /**
     * When implemented in a derived class, digitally signs the key encryption key metadata with the key encryption key
     * referenced by the encryptionKeyId parameter. The input values used to generate the signature should be the
     * specified values of the encryptionKeyId and allowEnclaveComputations parameters.
     * 
     * @param encryptionKeyId
     *        The key Id tells the provider where to find the key.
     * @param allowEnclaveComputations
     *        Indicates whether the key encryption key supports enclave computations.
     * @return The signature of the key encryption key metadata.
     */
    public abstract byte[] sign(String encryptionKeyId, boolean allowEnclaveComputations) throws AAPSDKException;

    /**
     * When implemented in a derived class, this method is expected to verify the specified signature is valid for the
     * key encryption key with the specified encryptionKeyId and the specified enclave behavior.
     * 
     * @param encryptionKeyId
     *        The key Id tells the provider where to find the key.
     * @param allowEnclaveComputations
     *        Indicates whether the key encryption key supports enclave computations.
     * @param signature
     *        The signature of the key encryption key metadata.
     * @return
     */
    public abstract boolean verify(String encryptionKeyId, boolean allowEnclaveComputations, byte[] signature) throws AAPSDKException;

    protected byte[] getOrCreateDataEncryptionKey(String encryptedDataEncryptionKey, byte[] createItem) {
        return dataEncryptionKeyCache.put(encryptedDataEncryptionKey, createItem);
    }

    protected boolean getOrCreateSignatureVerificationResult(Quadruple<String, String, Boolean, String> keyInformation,
            boolean createItem) {
        return keyEncryptionKeyMetadataSignatureVerificationCache.put(keyInformation, createItem);
    }
}
