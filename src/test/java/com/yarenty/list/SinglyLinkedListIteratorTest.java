/**
 * 
 */
package com.yarenty.list;

import static org.junit.Assert.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yarenty
 *
 */
public class SinglyLinkedListIteratorTest {

	
	SinglyLinkedList<String> list;
	
	int SIZE = 4;
	
	/**
	 * Setup test singly linked list
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		list= new SinglyLinkedList<String>("a","b","c","d");
	}



	/**
	 * Test method for {@link com.yarenty.list.SinglyLinkedList#listIterator()}.
	 */
	@Test
	public final void withGivenSinglyLinkedListCheckProperListIteratorBehaviour() {
		Iterator<String> itr = list.listIterator();
		int i=0;
		String value;
		while (itr.hasNext()) {
			value = itr.next();
			if (i==0) assertEquals("a", value);
			if (i==3) assertEquals("d", value);
			i++;
		}
	}

	
	@Test(expected=ConcurrentModificationException.class)
	public final void withGivenIteratorWhenChangingListThrowConcurrentModificationException() 
	{

		Iterator<String> itr = list.iterator();
		list.add("X");
		assertTrue(itr.hasNext());
	}

	
	@Test(expected=UnsupportedOperationException.class)
	public final void withGivenIteratorWhenCallHasPreviousUnsupportedOperationException() 
	{
		ListIterator<String> itr = list.listIterator();
		itr.hasPrevious();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public final void withGivenIteratorWhenCallPreviousUnsupportedOperationException() 
	{
		ListIterator<String> itr = list.listIterator();
		itr.previous();
	}
	
	
	/**
	 * Test method for {@link com.yarenty.list.SinglyLinkedList#iterator()}.
	 */
	@Test
	public final void testIterator() {
		Iterator<String> itr = list.iterator();
		int i=0;
		String value;
		while (itr.hasNext()) {
			value = itr.next();
			if (i==0) assertEquals("a", value);
			if (i==3) assertEquals("d", value);
			i++;
		}
	}

	/**
	 * Test method for {@link com.yarenty.list.SinglyLinkedList#listIterator(int)}.
	 */
	@Test
	public final void withGivenSinglyLinkedListCheckProperIteratorBehaviour() {
		Iterator<String> itr = list.listIterator(2);
		int i=0;
		String value;
		while (itr.hasNext()) {
			value = itr.next();
			if (i==0) assertEquals("c", value);
			if (i==1) assertEquals("d", value);
			i++;
		}
	}

	/**
	 * Test method for {@link com.yarenty.list.SinglyLinkedList#subList(int, int)}.
	 */
	@Test
	public final void withGivenSinglyLinkedListCheckSubList() {
		List<String> subList = list.subList(1,3);
		subList.get(0);
		assertEquals("b", subList.get(0));
		assertEquals("c", subList.get(1));
	}

}
