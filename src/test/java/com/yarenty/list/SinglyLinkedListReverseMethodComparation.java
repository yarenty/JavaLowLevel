package com.yarenty.list;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * NOT "REAL" TEST!!<br/>
 * Class written to check performance of recursive methods.
 *
 * @author yarenty
 */
public class SinglyLinkedListReverseMethodComparation {

    final int BIG_LIST_SIZE = 2000;

    private SinglyLinkedList<Integer> listWithBigNumberOfIntElements() {
        final SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        for (int i = 0; i < BIG_LIST_SIZE; i++) {
            list.add(i);
        }
        return list;
    }

    private void reverseIterative() {
        final SinglyLinkedList<Integer> list = listWithBigNumberOfIntElements();
        list.reverse();

        assertEquals(BIG_LIST_SIZE - 1, (int) list.get(0));
        assertEquals(0, (int) list.get(BIG_LIST_SIZE - 1));
    }

    private void reverseRecursive() {
        final SinglyLinkedList<Integer> list = listWithBigNumberOfIntElements();
        list.reverseRecursive();

        assertEquals(BIG_LIST_SIZE - 1, (int) list.get(0));
        assertEquals(0, (int) list.get(BIG_LIST_SIZE - 1));
    }

    private void reverseCollections() {
        final SinglyLinkedList<Integer> list = listWithBigNumberOfIntElements();
        Collections.reverse(list);

        assertEquals(BIG_LIST_SIZE - 1, (int) list.get(0));
        assertEquals(0, (int) list.get(BIG_LIST_SIZE - 1));
    }

    /**
     * This is to just check performance of recursive methods.
     */
    @Test
    public void compareTimesFor3ReverseMethods() {
        long start, iterativeTime, recursiveTime, collectionsTime;

        // warmup
        reverseIterative();
        reverseRecursive();
        reverseCollections();

        start = System.nanoTime();
        reverseIterative();
        iterativeTime = System.nanoTime() - start;

        start = System.nanoTime();
        reverseRecursive();
        recursiveTime = System.nanoTime() - start;

        start = System.nanoTime();
        reverseCollections();
        collectionsTime = System.nanoTime() - start;

        System.out.println("Iterative::" + iterativeTime);
        System.out.println("Recursive::" + recursiveTime);
        System.out.println("Colletions::" + collectionsTime);
    }

}
