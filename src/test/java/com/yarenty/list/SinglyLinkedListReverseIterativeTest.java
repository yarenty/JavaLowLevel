/**
 * 
 */
package com.yarenty.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for iterative reverse method.
 * 
 * @author yarenty
 * 
 */
public class SinglyLinkedListReverseIterativeTest {


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
	public final void withGivenEmptyListPerformIterativeReverse() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
		try {
			list.reverse();
		} catch (Exception e) {
			fail("Exception inside iterative reverse method");
		}
		assertEquals(0, list.size());
	}

	/**
	 * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
	 */
	@Test
	public final void withGivenOneElementListPerformIterativeReverse() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>(1);
		try {
			list.reverse();
		} catch (Exception e) {
			fail("Exception inside iterative reverse method");
		}
		assertEquals(1, list.size());
		assertEquals(1, (int) list.get(0));

	}

	/**
	 * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
	 */
	@Test
	public final void withGivenTwoElementsListPerformIterativeReverse() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>(1, 2);
		try {
			list.reverse();
		} catch (Exception e) {
			fail("Exception inside iterative reverse method");
		}

		assertEquals(2, (int) list.get(0));
		assertEquals(1, (int) list.get(1));
	}

	/**
	 * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
	 */
	@Test
	public final void withGivenThreeElementsListPerformIterativeReverse() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>(1, 2, 3);
		try {
			list.reverse();
		} catch (Exception e) {
			fail("Exception inside iterative reverse method");
		}

		assertEquals(3, (int) list.get(0));
		assertEquals(2, (int) list.get(1));
		assertEquals(1, (int) list.get(2));
	}

	/**
	 * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
	 */
	@Test
	public final void withGivenBigListPerformIterativeReverse() {
		SinglyLinkedList<Integer> list = listWithBigNumberOfIntElements();
		list.reverse();

		assertEquals(BIG_LIST_SIZE - 1, (int) list.get(0));
		assertEquals(0, (int) list.get(BIG_LIST_SIZE - 1));
	}

	
	/**
	 * Test method for {@link com.yarenty.list.SinglyLinkedList#reverse()}.
	 */
	@Test
	public final void withGivenStringListPerformIterativeReverse() {
		SinglyLinkedList<String> list = new SinglyLinkedList<String>("a",
				"b", "c", "d");
		list.reverse();
		
		assertEquals("d", list.get(0));
		assertEquals("c", list.get(1));
		assertEquals("b", list.get(2));
		assertEquals("a", list.get(3));
	}
	
}
