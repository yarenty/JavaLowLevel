/**
 * 
 */
package com.yarenty.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for recursive reverse method.
 * 
 * @author yarenty
 * 
 */
public class SinglyLinkedListReverseRecursiveTest {


	int BIG_LIST_SIZE = 1000;


	private SinglyLinkedList<Integer> listWithBigNumberOfIntElements() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		for (int i = 0; i < BIG_LIST_SIZE; i++) {
			list.add(i);
		}
		return list;
	}



	/**
	 * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
	 */
	@Test
	public final void withGivenEmptyListPerformRecursiveReverse() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		try {
			list.reverseRecursive();
		} catch (Exception e) {
			fail("Exception inside recursive reverse method");
		}
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
	 */
	@Test
	public final void withGivenOneElementListPerformRecursiveReverse() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>(1);
		try {
			list.reverseRecursive();
		} catch (Exception e) {
			fail("Exception inside recursive reverse method");
		}
		assertEquals(1, list.size());
		assertEquals(1, (int) list.get(0));

	}

	/**
	 * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
	 */
	@Test
	public final void withGivenTwoElementsListPerformRecursiveReverse() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>(1, 2);
		try {
			list.reverseRecursive();
		} catch (Exception e) {
			fail("Exception inside recursive reverse method");
		}

		assertEquals(2, (int) list.get(0));
		assertEquals(1, (int) list.get(1));
	}

	/**
	 * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
	 */
	@Test
	public final void withGivenThreeElementsListPerformRecursiveReverse() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>(1, 2, 3);
		try {
			list.reverseRecursive();
		} catch (Exception e) {
			fail("Exception inside recursive reverse method");
		}

		assertEquals(3, (int) list.get(0));
		assertEquals(2, (int) list.get(1));
		assertEquals(1, (int) list.get(2));
	}

	/**
	 * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
	 */
	@Test
	public final void withGivenBigListPerformRecursiveReverse() {
		SinglyLinkedList<Integer> list = listWithBigNumberOfIntElements();
		list.reverseRecursive();

		assertEquals(BIG_LIST_SIZE - 1, (int) list.get(0));
		assertEquals(0, (int) list.get(BIG_LIST_SIZE - 1));
	}

	
	/**
	 * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
	 */
	@Test
	public final void withGivenStringListPerformRecursiveReverse() {
		SinglyLinkedList<String> list = new SinglyLinkedList<String>("a",
				"b", "c", "d");
		list.reverseRecursive();
		
		assertEquals("d", list.get(0));
		assertEquals("c", list.get(1));
		assertEquals("b", list.get(2));
		assertEquals("a", list.get(3));
	}
	
}
