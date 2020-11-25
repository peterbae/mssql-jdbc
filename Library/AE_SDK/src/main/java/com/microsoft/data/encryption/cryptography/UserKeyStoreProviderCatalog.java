/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Class for storing and retrieving encryption keystore providers.
 *
 * @param <T>
 */
public class UserKeyStoreProviderCatalog<T> {
    private static UserKeyStoreProviderCatalog<?> instance = null;

    private ConcurrentHashMap<T, ConcurrentHashMap<String, EncryptionKeyStoreProvider>> keyStorage = new ConcurrentHashMap<T, ConcurrentHashMap<String, EncryptionKeyStoreProvider>>();

    private UserKeyStoreProviderCatalog() {}

    /**
     * Retrieves singleton instance of UserKeyStoreProviderCatalog.
     * @return UserKeyStoreProviderCatalog singleton instance
     */
    public static <T> UserKeyStoreProviderCatalog<?> getInstance() {
        if (instance == null) {
            instance = new UserKeyStoreProviderCatalog<T>();
        }

        return instance;
    }

    /**
     * Registers keystore provider.
     * @param userKey User's object of type T.
     * @param encryptionKeyStoreProvider Keystore provider object.
     */
    public void RegisterKeyStoreProvider(T userKey, EncryptionKeyStoreProvider encryptionKeyStoreProvider) {
        keyStorage.put(userKey, new ConcurrentHashMap<String, EncryptionKeyStoreProvider>());
    }

    /**
     * Retrieves keystore provider.
     * @param userKey User's object of type T.
     * @param providerName Name of provider.
     * @return Corresponding EncryptionKeyStoreProvider instance if exists.
     * @throws AAPSDKException
     */
    public EncryptionKeyStoreProvider GetKeyStoreProvider(T userKey, String providerName) throws AAPSDKException {
        keyStorage.put(userKey, new ConcurrentHashMap<String, EncryptionKeyStoreProvider>());
        ConcurrentHashMap<String, EncryptionKeyStoreProvider> value = keyStorage.get(userKey);
        EncryptionKeyStoreProvider provider = value.get(userKey);
        if (provider != null) {
            return provider;
        }

        throw new AAPSDKException(AAPSDKResource.getResource("R_KeystoreProviderError"));
    }

    /**
     * Removes provider instance that corresponds to the user's key.
     * @param userKey User's object of type T.
     */
    public void ClearUserProviders(T userKey) {
        keyStorage.remove(userKey);
    }

    /**
     * Removes all provider instances held by this class.
     */
    public void Clear() {
        keyStorage.clear();
    }
}
