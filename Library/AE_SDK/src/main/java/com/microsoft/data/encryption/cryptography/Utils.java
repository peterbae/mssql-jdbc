/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

import java.text.MessageFormat;


class Utils {

    public static void validateNotNull(Object s, String name) throws AAPSDKException {
        if (null == s) {
            MessageFormat form = new MessageFormat(AAPSDKResource.getResource("R_CannotBeNull"));
            Object[] msgArgs = {name};
            throw new AAPSDKException(form.format(msgArgs));
        }
    }

    public static void validateNotNullOrWhitespace(String s, String name) throws AAPSDKException {
        if (null == s || s.isEmpty() || s.trim().isEmpty()) {
            MessageFormat form = new MessageFormat(AAPSDKResource.getResource("R_CannotBeNullOrWhiteSpace"));
            Object[] msgArgs = {name};
            throw new AAPSDKException(form.format(msgArgs));
        }
    }
}
