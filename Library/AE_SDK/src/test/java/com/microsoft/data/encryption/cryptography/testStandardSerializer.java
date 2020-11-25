/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.microsoft.data.encryption.cryptography;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
public class testStandardSerializer {

    @Test
    public void testBoolean() throws AAPSDKException {
        ISerializer serializer = StandardSerializerFactory.getOrCreate(Boolean.class.getName());
        Boolean[] array = {null, true, false};
        for (Boolean data : array) {
            byte[] serializedData = serializer.serialize(data);
            Boolean deserializedData = (Boolean) serializer.deserialize(serializedData);
            assertEquals(data, deserializedData);
        }
        // test caching
        assertTrue(serializer.equals(StandardSerializerFactory.getOrCreate(Boolean.class.getName())));
    }

    @Test
    public void testInteger() throws AAPSDKException {
        ISerializer serializer = StandardSerializerFactory.getOrCreate(Integer.class.getName());
        Integer[] array = {null, Integer.MAX_VALUE, 1, 0, -1, Integer.MIN_VALUE, new Random().nextInt()};
        for (Integer data : array) {
            byte[] serializedData = serializer.serialize(data);
            Integer deserializedData = (Integer) serializer.deserialize(serializedData);
            assertEquals(data, deserializedData);
        }
        assertTrue(serializer.equals(StandardSerializerFactory.getOrCreate(Integer.class.getName())));
    }

    @Test
    public void testByte() throws AAPSDKException {
        ISerializer serializer = StandardSerializerFactory.getOrCreate(Byte.class.getName());
        Byte[] array = {null, Byte.MIN_VALUE, Byte.MAX_VALUE, 0, 1, -1};
        for (Byte data : array) {
            byte[] serializedData = serializer.serialize(data);
            Byte deserializedData = (Byte) serializer.deserialize(serializedData);
            assertEquals(data, deserializedData);
        }
        assertTrue(serializer.equals(StandardSerializerFactory.getOrCreate(Byte.class.getName())));
    }

    @Test
    public void testDouble() throws AAPSDKException {
        ISerializer serializer = StandardSerializerFactory.getOrCreate(Double.class.getName());
        Double[] array = {null, Double.MIN_VALUE, Double.MAX_VALUE, 0.0, 1.0, -1.0, new Random().nextDouble()};
        for (Double data : array) {
            byte[] serializedData = serializer.serialize(data);
            Double deserializedData = (Double) serializer.deserialize(serializedData);
            assertEquals(data, deserializedData);
        }
        assertTrue(serializer.equals(StandardSerializerFactory.getOrCreate(Double.class.getName())));
    }

    @Test
    public void testFloat() throws AAPSDKException {
        ISerializer serializer = StandardSerializerFactory.getOrCreate(Float.class.getName());
        Float[] array = {null, Float.MIN_VALUE, Float.MAX_VALUE, 0.0f, 1.0f, -1.0f, new Random().nextFloat()};
        for (Float data : array) {
            byte[] serializedData = serializer.serialize(data);
            Float deserializedData = (Float) serializer.deserialize(serializedData);
            assertEquals(data, deserializedData);
        }
        assertTrue(serializer.equals(StandardSerializerFactory.getOrCreate(Float.class.getName())));
    }


    @Test
    public void testUuid() throws AAPSDKException {
        ISerializer serializer = StandardSerializerFactory.getOrCreate(UUID.class.getName());
        UUID[] array = {null, UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()};
        for (UUID data : array) {
            byte[] serializedData = serializer.serialize(data);
            UUID deserializedData = (UUID) serializer.deserialize(serializedData);
            assertEquals(data, deserializedData);
        }
        assertTrue(serializer.equals(StandardSerializerFactory.getOrCreate(UUID.class.getName())));
    }

    @Test
    public void testChar() throws AAPSDKException {
        ISerializer serializer = StandardSerializerFactory.getOrCreate(Character.class.getName());
        Character[] dataArray = {null, 'a', 'z', '0', '9', '말', ' '};
        for (Character data : dataArray) {
            byte[] serializedData = serializer.serialize(data);
            Character deserializedData = (Character) serializer.deserialize(serializedData);
            assertEquals(data, deserializedData);
        }
        assertTrue(serializer.equals(StandardSerializerFactory.getOrCreate(Character.class.getName())));
    }
    
    @Test
    public void testString() throws AAPSDKException {
        ISerializer serializer = StandardSerializerFactory.getOrCreate(String.class.getName());
        String[] dataArray = {null, "a", "말", "0000000000123456789", ""};
        for (String data : dataArray) {
            byte[] serializedData = serializer.serialize(data);
            String deserializedData = (String) serializer.deserialize(serializedData);
            assertEquals(data, deserializedData);
        }
        assertTrue(serializer.equals(StandardSerializerFactory.getOrCreate(String.class.getName())));
    }
}
