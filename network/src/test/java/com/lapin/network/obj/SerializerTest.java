package com.lapin.network.obj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;

class SerializerTest {
    TestObj testObj;
    @BeforeEach
    void setUp() {
        testObj = new TestObj();
    }

    @Test
    void serialize() {
    }

    @Test
    void deserialize() {
        Serializable desObj = null;
        try {
            var serObj = Serializer.serialize(testObj);
            desObj = Serializer.deserialize(serObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(TestObj.class,desObj.getClass() );
    }
    class TestObj implements Serializable{
        String name = "test";
        public String getName() {
            return name;
        }
    }
}