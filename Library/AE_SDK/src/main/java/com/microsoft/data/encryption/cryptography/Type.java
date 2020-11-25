/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

class Type {
    final String ID;
    final int SIZE;
    final int PRECISION;
    final int SCALE;
        
    public Type(String id, int size, int precision, int scale) {
        this.ID = id;
        this.SIZE = size;
        this.PRECISION = precision;
        this.SCALE = scale;
    }
}
