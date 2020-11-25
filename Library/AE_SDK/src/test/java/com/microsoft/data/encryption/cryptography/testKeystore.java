/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
public class testKeystore extends AESetup {

    @Test
    public static void testJavaKeyStoreProvider(String[] args) throws AAPSDKException {

        byte[] encryptedCEKValueJava = javaKeyProvider.wrapKey(javaKeyAliases, KeyEncryptionKeyAlgorithm.RSA_OAEP,
                rootKey);

        byte[] decryptedEKValueJava = javaKeyProvider.unwrapKey(javaKeyAliases, KeyEncryptionKeyAlgorithm.RSA_OAEP,
                encryptedCEKValueJava);

        assertTrue(Arrays.equals(decryptedEKValueJava, rootKey));

        byte[] signedData = javaKeyProvider.sign(javaKeyAliases, true);
        assertTrue(javaKeyProvider.verify(javaKeyAliases, true, signedData));
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
