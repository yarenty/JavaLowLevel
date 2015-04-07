package com.yarenty.map;


import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author yarenty
 */
public class CountingTreeMap<K, V extends Integer> extends TreeMap<K, V> implements Comparator<K> {

    public void put(final K key) {
        Integer value = 1;
        if (this.containsKey(key)) {
            value = (Integer) this.get(key) + 1;
        }
        this.put(key, (V) value);
    }

    @Override
    public Comparator<K> comparator() {
        System.out.println("call");
        return new Comparator<K>() {

            @Override
            public int compare(K o1, K o2) {
                return get(o1).compareTo(get(o2));
            }
        };

    }

    @Override
    public int compare(final K o1, final K o2) {
        System.out.println("xxx");
        return get(o1).compareTo(get(o2));
    }


}
