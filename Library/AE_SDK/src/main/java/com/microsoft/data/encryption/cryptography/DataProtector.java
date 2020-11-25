/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

/**
 * The base class used for algorithms that do encryption and decryption of serialized object data.
 *
 */
abstract class DataProtector {
    
    /**
     * Decrypts already encrypted ciphertext
     * @param ciphertext data to be decrypted
     * @return plain text
     * 
     */
    abstract byte[] decrypt(byte[] ciphertext) throws AAPSDKException;
    
    /**
     * Encrypts plaintext byte of arrays
     * @param plaintext text to be encrypted
     * @return cipher text
     * 
     */
    abstract byte[] encrypt(byte[] plaintext) throws AAPSDKException;
}
