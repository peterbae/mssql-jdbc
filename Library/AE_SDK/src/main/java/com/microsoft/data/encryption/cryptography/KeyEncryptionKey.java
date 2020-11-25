/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

/**
 * A "KeyEncryptionKey" represents a key-protecting key stored in an external key store. The key protects (encrypts) one
 * or more DataEncryptionKeys.
 */
public class KeyEncryptionKey {
    private static final KeyEncryptionKeyAlgorithm encryptionAlgorithm = KeyEncryptionKeyAlgorithm.RSA_OAEP;

    // The name of the KeyEncryptionKey.
    private String name;
    private EncryptionKeyStoreProvider keyStoreProvider;
    private String path;
    
    /*
     * Specifies that the KeyEncryptionKey is enclave-enabled. You can share all encryption keys, encrypted with the
     * KeyEncryptionKey, with a secure enclave and use them for computations inside the enclave.
     */
    private boolean isEnclaveSupported;
    

    /*
     * A binary result of digitally signing key path and the IsEnclaveSupported setting with the master key. The
     * signature reflects whether IsEnclaveSupported is specified or not. The signature protects the signed values from
     * being altered by unauthorized users.
     */
    private byte[] signature;
    
    /**
     * Getter for name.
     * @return name of this KeyEncryptionKey.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for KeyStoreProvider.
     * @return keyStoreProvider value.
     */
    public EncryptionKeyStoreProvider getKeyStoreProvider() {
        return keyStoreProvider;
    }

    /**
     * Getter for path.
     * @return path value.
     */
    public String getPath() {
        return path;
    }

    /**
     * Getter for isEnclaveSupported.
     * @return whether enclave is supported.
     */
    public boolean getIsEnclaveSupported() {
        return isEnclaveSupported;
    }

    /**
     * Getter for signature.
     * @return signature value.
     */
    public byte[] getSignature() {
        return signature;
    }

    /**
     * Returns a cached instance of the KeyEncryptionKey or, if not present, creates a new one.
     * @param name The name of the KeyEncryptionKey.
     * @param path The path of the key in the KeyEncryptionKey store.
     * @param keystore A component that has access to the KeyEncryptionKey.
     * @param isEnclaveSupported Specifies whether the KeyEncryptionKey is enclave-enabled.
     * @return A KeyEncryptionKey object.
     * @throws Exception
     */
    public static KeyEncryptionKey getOrCreate(String name, String path, EncryptionKeyStoreProvider keystore,
            boolean isEnclaveSupported) throws Exception {
        Utils.validateNotNullOrWhitespace(name, "name");
        Utils.validateNotNullOrWhitespace(path, "path");
        Utils.validateNotNull(keystore, "keystore");

        return new KeyEncryptionKey(name, path, keystore, isEnclaveSupported);
    }

    /**
     * Constructs a new master KeyEncryptionKey.
     * @param name The name of the KeyEncryptionKey
     * @param path The path of the key in the KeyEncryptionKey store.
     * @param keystore A component that has access to the KeyEncryptionKey.
     * @param isEnclaveSupported Specifies whether the KeyEncryptionKey is enclave-enabled.
     * @throws Exception
     */
    public KeyEncryptionKey(String name, String path, EncryptionKeyStoreProvider keystore,
            boolean isEnclaveSupported) throws Exception {
        Utils.validateNotNullOrWhitespace(name, "name");
        Utils.validateNotNullOrWhitespace(path, "path");
        Utils.validateNotNull(keystore, "keystore");

        this.name = name;
        this.path = path;
        this.keyStoreProvider = keystore;
        this.isEnclaveSupported = isEnclaveSupported;
        this.signature = keyStoreProvider.sign(path, isEnclaveSupported);
    }

    /**
     * Encrypts an DataEncryptionKey.
     * @param plaintextEncryptionKey the DataEncryptionKey to encrypt.
     * @return the encrypted DataEncryptionKey.
     * @throws AAPSDKException
     */
    public byte[] encryptEncryptionKey(byte[] plaintextEncryptionKey) throws AAPSDKException {
        return keyStoreProvider.wrapKey(path, encryptionAlgorithm, plaintextEncryptionKey);
    }

    /**
     * Decrypts an DataEncryptionKey.
     * @param encryptedEncryptionKey the DataEncryptionKey to decrypt.
     * @return the decrypted DataEncryptionKey.
     * @throws AAPSDKException
     */
    public byte[] decryptEncryptionKey(byte[] encryptedEncryptionKey) throws AAPSDKException {
        return keyStoreProvider.unwrapKey(path, encryptionAlgorithm, encryptedEncryptionKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof KeyEncryptionKey)) {
            return false;
        }

        KeyEncryptionKey other = (KeyEncryptionKey) obj;

        return name.equals(other.name) && keyStoreProvider.equals(other.keyStoreProvider) && path.equals(other.path)
                && isEnclaveSupported == (other.isEnclaveSupported);
    }

    @Override
    public int hashCode() {
        return new Quadruple<String, EncryptionKeyStoreProvider, String, Boolean>(name, keyStoreProvider, path,
                isEnclaveSupported).hashCode();
    }
}
