package org.example.simplehashmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class SimpleHashMapTest {

    @Test
    void totalTestWithLargeAmountOfData() {
        // given
        SimpleHashMap<Integer, String> testHashMap = new SimpleHashMapImpl<>();
        for (int i = 1; i <= 10000; i++) {
            testHashMap.put(i, "test" + i);
        }

        // when
        int count1 = testHashMap.size();

        // then
        assertEquals(10000, count1);

        // when
        for (int i = 5001; i <= 10000; i++) {
            testHashMap.remove(i);
        }
        int count2 = testHashMap.size();

        // then
        assertEquals(5000, count2);

        // when
        for (int i = 1; i <= 10000; i++) {
            testHashMap.put(i, "test" + i);
        }
        int count3 = testHashMap.size();

        // then
        assertEquals(10000, count3);
    }

    @Test
    void getTest() {
        // given
        SimpleHashMap<Integer, String> testHashMap = new SimpleHashMapImpl<>();
        String expected1 = "test1";
        String expected2 = "test2";
        String expected3 = "test3";
        String expected4 = "test2+";

        testHashMap.put(1, expected1);
        testHashMap.put(2, expected2);
        testHashMap.put(3, expected3);

        // when
        String actual1 = testHashMap.get(1);
        String actual2 = testHashMap.get(2);
        String actual3 = testHashMap.get(3);

        // then
        assertEquals(actual1, expected1);
        assertEquals(actual2, expected2);
        assertEquals(actual3, expected3);

        // when
        testHashMap.put(2, expected4);
        String actual4 = testHashMap.get(2);
        String actual5 = testHashMap.get(4);

        // then
        assertEquals(actual4, expected4);
        assertNull(actual5);


    }

    @Test
    void removeTest() {
        // given
        SimpleHashMap<Integer, String> testHashMap = new SimpleHashMapImpl<>();

        testHashMap.put(1, "test1");
        testHashMap.put(2, "test2");
        testHashMap.put(3, "test3");

        // when
        testHashMap.remove(1);

        // then
        assertEquals(testHashMap.size(), 2);

        // when
        testHashMap.remove(2);

        // then
        assertEquals(testHashMap.size(), 1);

        // when
        testHashMap.remove(2);

        // then
        assertEquals(testHashMap.size(), 1);

    }

    @Test
    void putTest() {
        // given
        SimpleHashMap<Integer, String> testHashMap = new SimpleHashMapImpl<>(2, 0.75f);
        for (int i = 1; i < 100; i++) {
            testHashMap.put(i, "test" + i);

        }

        // when
        String actual1 = testHashMap.get(1);

        // then
        assertEquals(actual1, "test1");

        // when
        String actual2 = testHashMap.get(99);

        // then
        assertEquals(actual2, "test99");

    }

    @Test
    void sizeTest() {
        // given
        SimpleHashMap<Integer, String> testHashMap = new SimpleHashMapImpl<>(2, 0.75f);

        testHashMap.put(1, "test1");
        testHashMap.put(2, "test2");
        testHashMap.put(3, "test3");

        // when
        // then
        assertEquals(testHashMap.size(), 3);

        // when
        testHashMap.remove(1);

        // then
        assertEquals(testHashMap.size(), 2);

        // when
        testHashMap.put(2, "test2+");

        // then
        assertEquals(testHashMap.size(), 2);
    }

}