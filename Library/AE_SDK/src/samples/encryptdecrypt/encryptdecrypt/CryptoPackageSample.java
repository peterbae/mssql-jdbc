/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package encryptdecrypt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.microsoft.data.encryption.cryptography.AAPSDKException;
import com.microsoft.data.encryption.cryptography.AeadAes256CbcHmac256EncryptionAlgorithm;
import com.microsoft.data.encryption.cryptography.EncryptionType;
import com.microsoft.data.encryption.cryptography.ISerializer;
import com.microsoft.data.encryption.cryptography.ProtectedDataEncryptionKey;
import com.microsoft.data.encryption.cryptography.SqlSerializerFactory;
import com.microsoft.data.encryption.cryptography.StandardSerializerFactory;


/**
 * Sample application to demonstrate how to use Always Protected SDK's Crypto package.
 */
public class CryptoPackageSample {
    private final static char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F'};
    public static AeadAes256CbcHmac256EncryptionAlgorithm deterministicAlgorithm = null;
    public static ProtectedDataEncryptionKey dataEncryptionKey = null;

    public static void main(
            String[] args) throws IOException, AAPSDKException, InvalidKeyException, NoSuchAlgorithmException {
        String rootKey = null;

        try (InputStreamReader in = new InputStreamReader(System.in); BufferedReader br = new BufferedReader(in)) {
            System.out.print("Enter root key String, the length must be 32 characters: ");
            rootKey = br.readLine();

            dataEncryptionKey = new ProtectedDataEncryptionKey("CEK", rootKey.getBytes());
            deterministicAlgorithm = AeadAes256CbcHmac256EncryptionAlgorithm.getOrCreate(dataEncryptionKey,
                    EncryptionType.Deterministic);

            ISerializer sqlSerializer = SqlSerializerFactory.getOrCreate(SqlSerializerFactory.VARCHAR_ID, 40, 0, 0);
            String data = new String("Varchar sample data");
            ISerializer standardSerializer = StandardSerializerFactory.getOrCreate(String.class.getName());

            // Serialization scenario
            byte[] serializedData1 = sqlSerializer.serialize(data);
            byte[] serializedData2 = standardSerializer.serialize(data);

            // Deserialization scenario
            String deserializedData1 = (String) sqlSerializer.deserialize(serializedData1);
            String deserializedData2 = (String) standardSerializer.deserialize(serializedData2);

            // Deterministic algorithm encryption scenario
            byte[] encryptedData1 = deterministicAlgorithm.encrypt(serializedData1);
            byte[] encryptedData2 = deterministicAlgorithm.encrypt(serializedData2);

            // Deterministic algorithm decryption scenario
            String decryptedData1 = (String) sqlSerializer.deserialize(deterministicAlgorithm.decrypt(encryptedData1));
            String decryptedData2 = (String) standardSerializer.deserialize(deterministicAlgorithm.decrypt(encryptedData2));

            System.out.println("Original String data: " + data);
            System.out.println("Serialized hex String data: " + toHexString(serializedData1));
            System.out.println("Serialized Standard hex String data: " + toHexString(serializedData2));
            System.out.println("Deserialized String data: " + deserializedData1);
            System.out.println("Deserialized Standard String data: " + deserializedData2);
            System.out.println("Encrypted hex String data: " + toHexString(encryptedData1));
            System.out.println("Encrypted Standard hex String data: " + toHexString(encryptedData2));
            System.out.println("Decrypted String data: " + decryptedData1);
            System.out.println("Decrypted Standard String data: " + decryptedData2);
        }
    }

    private static String toHexString(byte[] source) {
        if (null == source) {
            return null;
        }

        StringBuilder sb = new StringBuilder(source.length * 2);
        for (int i = 0; i < source.length; i++) {
            int hexVal = source[i] & 0xFF;
            sb.append(hexChars[(hexVal & 0xF0) >> 4]);
            sb.append(hexChars[(hexVal & 0x0F)]);
        }
        return sb.toString();
    }
}
