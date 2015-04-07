package com.yarenty.map;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertNotNull;

public class SortingTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    /**
     * Given a web access file, count number of unique URLs and sort by number.
     */

    @Test
    public void testLinkedSortByValue() {
        final Random random = new Random(System.currentTimeMillis());
        final CountingMap<String, Integer> testMap = new CountingMap<String, Integer>();
        for (int i = 0; i < 1000; ++i) {
            testMap.put("Linked" + random.nextInt(100));
        }

        final Map<String, Integer> sortedMap = MapSortingUtil.sortByValue(testMap);

        for (final Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            assertNotNull(entry.getValue());
            System.out.println(entry.getKey() + "::" + entry.getValue());
        }
    }


    @Test
    public void testTreeMapSortByValue() {
        final Random random = new Random(System.currentTimeMillis());
        final CountingTreeMap<String, Integer> testMap = new CountingTreeMap<String, Integer>();
        for (int i = 0; i < 1000; ++i) {
            testMap.put("Tree" + random.nextInt(100));
        }

        //Collections.sort(testMap, testMap.comparator());
        for (final Map.Entry<String, Integer> entry : testMap.entrySet()) {
            assertNotNull(entry.getValue());

            System.out.println(entry.getKey() + "::" + entry.getValue());
        }
    }

}
