package com.yarenty.map;

import java.util.*;

/**
 * Given a web access file, count number of unique URLs and sort by number.
 *
 * @author yarenty
 */
public class MapSortingUtil {


    //TODO: check this magic with K,V
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(final Map<K, V> map) {
        //lets sort that list
        final List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());


        Collections.sort(list,
                //main issue: Comparator usually works on key, but everything is Object
                // and as Object you can put whole map.entry
                new Comparator<Map.Entry<K, V>>() {
                    public int compare(final Map.Entry<K, V> o1, final Map.Entry<K, V> o2) {
                        // from bigger to smallest - that's why o2 then o1
                        return (o2.getValue()).compareTo(o1.getValue());
                    }
                }
        );


        // not needed really if only display - returning that linked list could be enough
        final Map<K, V> result = new LinkedHashMap<K, V>(list.size());


        for (final Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
