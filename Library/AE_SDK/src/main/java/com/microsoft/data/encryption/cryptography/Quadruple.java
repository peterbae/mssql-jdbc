/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

/**
 * Class used to represent an object with four elements.
 *
 */
public class Quadruple<W, X, Y, Z> {
    final W w;
    final X x;
    final Y y;
    final Z z;

    /**
     * Creates an object with four elements.
     * @param w
     * @param x
     * @param y
     * @param z
     */
    public Quadruple(W w, X x, Y y, Z z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
