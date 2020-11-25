/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

/**
 * Class used to represent an object with two elements.
 *
 */
public class Tuple<Y, Z> {
    final Y y;
    final Z z;

    /**
     * Creates an object with two elements.
     * @param y
     * @param z
     */
    public Tuple(Y y, Z z) {
        this.y = y;
        this.z = z;
    }
}