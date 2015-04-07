package com.yarenty.map;


import java.util.HashMap;

/**
 * Given a web access file, count number of unique URLs and sort by number.
 *
 * @param <K>
 * @param <V>
 * @author yarenty
 */
public class CountingMap<K, V extends Integer> extends HashMap<K, V> {

    public void put(final K key) {
        Integer value = 1;
        if (this.containsKey(key)) {
            value = (Integer) this.get(key) + 1;
        }
        this.put(key, (V) value);
    }
}
