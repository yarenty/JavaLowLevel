/**
 *
 */
package com.yarenty.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Test for standard list methods.
 *
 * @author yarenty
 */
public class SinglyLinkedListStandarMethodsTest {

    SinglyLinkedList<String> list;

    final int SIZE = 4;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        list = new SinglyLinkedList<String>("a", "b", "c", "d");
    }

    /**
     * Test method for {@link com.yarenty.list.SinglyLinkedList#size()}.
     */
    @Test
    public final void withGivenSinglyLinkedListCheckProperSize() {
        assertEquals(SIZE, list.size());
    }

    /**
     * Test method for {@link com.yarenty.list.SinglyLinkedList#clear()}.
     */
    @Test
    public final void withGivenSinglyLinkedListCheckClear() {
        list.clear();
        assertEquals(0, list.size());
    }

    /**
     * Test method for
     * {@link com.yarenty.list.SinglyLinkedList#checkInvariants(int)}.
     */
    @Test
    public final void withGivenSinglyLinkedListCheckInvariants() {
        list.checkInvariants(SIZE);
    }

    /**
     * Test method for
     * {@link com.yarenty.list.SinglyLinkedList#SinglyLinkedList(E[])}.
     */
    @Test
    public final void withGivenSinglyLinkedListTestSinglyLinkedListEArray() {
        final String[] obj = {"a", "b"};
        final SinglyLinkedList<String> list = new SinglyLinkedList<String>(obj);

        assertEquals(2, list.size());
    }

    /**
     * Test method for
     * {@link com.yarenty.list.SinglyLinkedList#SinglyLinkedList(java.util.Collection)}
     * .
     */
    @Test
    public final void withGivenSinglyLinkedListTestSinglyLinkedListCollectionOfQextendsE() {
        final Collection<String> obj = Collections.emptyList();

        final SinglyLinkedList<String> list = new SinglyLinkedList<String>(obj);

        assertEquals(0, list.size());

    }

    /**
     * Test method for {@link com.yarenty.list.SinglyLinkedList#clone()}.
     * <p/>
     * Shallow copy test.
     */
    @Test
    public final void withGivenSinglyLinkedListTestClone() {
        final SinglyLinkedList<String> cloned = list.clone();
        assertEquals(SIZE, cloned.size());
        // shallow copy
        assertEquals(list.get(0), cloned.get(0));
    }

    /**
     * Test method for {@link com.yarenty.list.SinglyLinkedList#get(int)}.
     */
    @Test
    public final void withGivenSinglyLinkedListTestGetInt() {
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
        assertEquals("d", list.get(3));
    }

    /**
     * Test method for {@link com.yarenty.list.SinglyLinkedList#get(int)}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void withGivenSinglyLinkedListWhenBigIndexCheckIndexOutOfBounds() {
        list.get(SIZE); // +1 over
    }

    /**
     * Test method for {@link com.yarenty.list.SinglyLinkedList#get(int)}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void withGivenSinglyLinkedListWhenMinusIndexCheckIndexOutOfBounds() {
        list.get(-1);
    }

    /**
     * Test method for
     * {@link com.yarenty.list.SinglyLinkedList#add(java.lang.Object)}.
     */
    @Test
    public final void withGivenSinglyLinkedListWhenAddElementToListTestIsLast() {
        list.add("e");
        assertEquals("e", list.get(SIZE));
        assertEquals(SIZE + 1, list.size());
    }

    /**
     * Test method for
     * {@link com.yarenty.list.SinglyLinkedList#add(int, java.lang.Object)}.
     */
    @Test
    public final void withGivenSinglyLinkedListWhenAddElementAsFirstInList() {
        list.add(0, "e");
        assertEquals("e", list.get(0));
        assertEquals(SIZE + 1, list.size());
    }

    /**
     * Test method for
     * {@link com.yarenty.list.SinglyLinkedList#add(int, java.lang.Object)}.
     */
    @Test
    public final void withGivenSinglyLinkedListWhenAddIntEOnSpecificPosition() {
        list.add(2, "e");
        assertEquals("e", list.get(2));
        assertEquals(SIZE + 1, list.size());
    }

    /**
     * Test method for
     * {@link com.yarenty.list.SinglyLinkedList#addAll(java.util.Collection)}.
     */
    @Test
    public final void withGivenSinglyLinkedListTestAddAllCollectionOfElements() {
        final ArrayList<String> addedList = new ArrayList<String>(Arrays.asList("x",
                "y", "z"));
        list.addAll(addedList);
        assertEquals(SIZE + addedList.size(), list.size());
    }

    /**
     * Test method for
     * {@link com.yarenty.list.SinglyLinkedList#addAll(int, java.util.Collection)}
     * .
     */
    @Test
    public final void withGivenSinglyLinkedListTestAddAllCollectionOfElementsOnSpecificPosition() {
        // SinglyLinkedList<String> list= getStringList();
        final ArrayList<String> addedList = new ArrayList<String>(Arrays.asList("x",
                "y", "z"));
        list.addAll(2, addedList);
        assertEquals(SIZE + addedList.size(), list.size());

        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("x", list.get(2));
        assertEquals("y", list.get(3));
    }

    /**
     * Test method for
     * {@link com.yarenty.list.SinglyLinkedList#addAll(int, java.util.Collection)}
     * .
     */
    @Test
    public final void withGivenSinglyLinkedListTestAddAllCollectionOfElementsOnBeginingOfList() {
        final ArrayList<String> addedList = new ArrayList<String>(Arrays.asList("x",
                "y", "z"));
        list.addAll(0, addedList);
        assertEquals(SIZE + addedList.size(), list.size());

        assertEquals("x", list.get(0));
        assertEquals("y", list.get(1));
        assertEquals("z", list.get(2));
        assertEquals("a", list.get(3));
    }

    /**
     * Test method for
     * {@link com.yarenty.list.SinglyLinkedList#addAll(int, java.util.Collection)}
     * .
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void withGivenSinglyLinkedListWhenPositionIsWrongThenAddAllCollectionOfElementsThrowsOutOfBounds() {
        final ArrayList<String> addedList = new ArrayList<String>(Arrays.asList("x",
                "y", "z"));
        list.addAll(SIZE + 2, addedList);
    }

    /**
     * Test method for {@link com.yarenty.list.SinglyLinkedList#remove(int)}.
     */
    @Test
    public final void withGivenSinglyLinkedListTestRemoveElementAtPosition() {
        list.remove(2);
        assertEquals(SIZE - 1, list.size());
        assertEquals("d", list.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public final void withGivenSinglyLinkedListWhenRemoveWithWrongPositionThrowOutOfBounds() {
        list.remove(7);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public final void withGivenSinglyLinkedListWhenRemoveWithMinusPositionThrowOutOfBounds() {
        list.remove(-7);
    }

}
