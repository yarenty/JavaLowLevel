package com.yarenty.list;

import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

/**
 * <b>TASK: Implement a simple singly-linked list, and two functions to reverse
 * the order of the list.<br/>
 * You must provide:<br/>
 * 1. An iterative reverse operation.<br/>
 * 2. A recursive reverse operation.<br/>
 * 3. Full coverage automated tests.<br/>
 * <br/>
 * </b>
 * 
 * I choose to use <b>{@link AbstractSequentialList}</b>. From "
 * {@link <a href="http://docs.oracle.com/javase/6/docs/api/java/util/AbstractSequentialList.html">oracle web site </a>}
 * ":<br/>
 * <i> AbstractSequentialList provides a skeletal implementation of the List
 * interface to minimize the effort required to implement this interface backed
 * by a "sequential access" data store (such as a linked list). [...]</i><br/>
 * <p>
 * This is singly linked list so only forward iteration operations are
 * supported. Methods {@link ListIterator#hasPrevious()} or
 * {@link ListIterator#previous()} will finish with
 * {@link UnsupportedOperationException}.
 * <p>
 * The singly linked list implements {@link RandomAccess} to prevent various
 * {@link Collections} methods from invoking the above two methods.
 * <p>
 * Therefore are 3 reverse methods available:<br/>
 * {@link #reverse()} - iterative reverse (quickest)<br/>
 * {@link #reverseRecursive()} - recursive reverse, could raise StavkOverflow
 * when list is too big<br/>
 * {@link Collections#reverse(List)} - standard collections reverse<br/>
 * 
 * @author yarenty@gmail.com
 * 
 * @param <E>
 *            the type of the elements held in the list.
 * 
 * @see http
 *      ://docs.oracle.com/javase/6/docs/api/java/util/AbstractSequentialList
 *      .html
 */
public final class SinglyLinkedList<E> extends AbstractSequentialList<E>
		implements RandomAccess {

	/**
	 * Implementation of node.
	 * 
	 * @author yarenty
	 * 
	 * @param <T>
	 */
	private static class Node<T> {
		T e;
		Node<T> next;

		Node(T e, Node<T> next) {
			this.e = e;
			this.next = next;
		}
	}

	transient private int size = 0;

	/**
	 * Pointer to first node.
	 */
	transient Node<E> first;


	/**
	 * Pointer to the last element in the list to speed up operation "append"
	 * 
	 */
	private Node<E> tail;

	/**
	 * Empty list - initialize to speed up add operations.
	 * 
	 */
	public SinglyLinkedList() {}

	/**
	 * Creates a list populated with elements.
	 * 
	 * @param elements
	 */
	public SinglyLinkedList(E... elements) {
		this();
		for (E e : elements)
			add(e);
	}

	/**
	 * Constructs a list containing the elements of the specified collection, in
	 * the order they are returned by the collection's iterator.
	 * 
	 * @param c
	 *            the collection whose elements are to be placed into this list
	 * @throws NullPointerException
	 *             if the specified collection is null
	 */
	public SinglyLinkedList(Collection<? extends E> c) {
		this();
		addAll(c);
	}

	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Removes all of the elements from this list. The list will be empty after
	 * this call returns.
	 */
	@Override
	public void clear() {
		for (Iterator<E> i = iterator(); i.hasNext(); i.remove()) {
			i.next();
		}
	}

	/**
	 * The only method that directly changes the structure adding an element
	 * 
	 * @param after
	 *            the node to insert after, assumption is that this is a valid
	 *            node from the list in the range [head, last]
	 * @param value
	 *            the value to store
	 * @return reference to the added element
	 * */
	private Node<E> addNode(Node<E> after, E value) {
		Node<E> n;
		
		if (after == null) { //first node
			n = new Node<E>(value,first);

			if (first == null) {
				tail = n;
			}
			first=n;
						
		} else {
			n = new Node<E>(value, after.next);
			after.next = n;
			if (n.next == null) // is "n" the new last node?
				tail = n; // tail is not a proper element of the list and must
								// be manually updated
		}

		size++;
		modCount++;
		return n;
	}

	/**
	 * The method that directly changes the structure to remove an element
	 * 
	 * @param after
	 *            the node whose next is to be detached from the list and
	 *            nullified, assumption is that {@code after} is a valid node in
	 *            the range [ head, last )
	 * */
	private E removeNode(Node<E> after) {
		
		E toBeReturned= null;
		
		if (after.next !=null) {
			Node<E> removed = after.next;
			after.next = removed.next;
			removed.next = null;
			toBeReturned = removed.e;
			removed.e = null;
			// is after node the new last node?
			if (after.next == null)
				tail.next = after;
			//
		} else {
			toBeReturned = after.e;
			after = null;
			tail = null;
		}
		
		size--;
		modCount++;
		return toBeReturned;
	}

	/**
	 * Appends the argument to the end of the list.
	 * 
	 * @param e
	 *            the element to append, can be {@code null}
	 * @return {@code true}
	 */
	@Override
	public boolean add(E e) {
		return addNode(tail, e) != null;
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * 
	 * @param index
	 *            the position to insert at.
	 * @param value
	 *            the element to insert, can be {@code null}
	 * @throws IndexOutOfBoundsException
	 *             {@inheritDoc}
	 */
	@Override
	public void add(int index, E value) {
		if (checkRange(index, size()) == size()) {
			addNode(tail,value); 
		} else {
			if (index==0) {
				addNode(null, value);
			} else {
				addNode(findNodeBefore(first, index), value);
			}
		}
	}

	private boolean insertAllAfter(final Node<E> insertAfter,
			Collection<? extends E> c) {
		Node<E> n = insertAfter;
		for (E e : c) {
			n = addNode(n, e);
		}
		if (n != insertAfter)
			modCount++;
		return (n != insertAfter);
	}

	/**
	 * Appends all of the elements in the specified collection to the end of
	 * this list, in the order that they are returned by the specified
	 * collection's iterator. The behavior of this operation is undefined if the
	 * specified collection is modified while the operation is in progress.
	 * (Note that this will occur if the specified collection is this list, and
	 * it's nonempty.)
	 * 
	 * @param c
	 *            collection containing elements to be added to this list
	 * @return {@code true} if this list changed as a result of the call
	 * @throws NullPointerException
	 *             if the specified collection is null
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		return insertAllAfter(tail, c);
	}

	/**
	 * Inserts all of the elements in the specified collection into this list,
	 * starting at the specified position. Shifts the element currently at that
	 * position (if any) and any subsequent elements to the right (increases
	 * their indices). The new elements will appear in the list in the order
	 * that they are returned by the specified collection's iterator.
	 * 
	 * @param index
	 *            index at which to insert the first element from the specified
	 *            collection
	 * @param c
	 *            collection containing elements to be added to this list
	 * @return {@code true} if this list changed as a result of the call
	 * @throws IndexOutOfBoundsException
	 *             {@inheritDoc}
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		checkRange(index, size());
		Node n = (index == 0) ? null: findNodeBefore(first, index);
		return insertAllAfter(n, c);
	}

	/**
	 * Removes the element at the specified position in this list and returns
	 * the element that was removed from the list.
	 * 
	 * @param index
	 *            {@inheritDoc}
	 * @throws IndexOutOfBoundsException
	 *             {@inheritDoc}
	 */
	@Override
	public E remove(int index) {
		checkRange(index, size() - 1);
		return removeNode(findNodeBefore(first, index));
	}

	/**
	 * Returns a shallow copy of this <tt>SingleLinkedList</tt>. (The elements
	 * themselves are not cloned.)
	 * 
	 * @return a shallow copy of this <tt>SingleLinkedList</tt> instance
	 */
	@Override
	public SinglyLinkedList<E> clone() {
		return new SinglyLinkedList<E>(this);
	}

	/**
	 * Iterative approach. Reverses the order of the elements in the list, runs
	 * in linear time.
	 * 
	 * 
	 * @return {@code this} for chaining
	 */
	public SinglyLinkedList<E> reverse() {
		if (size() > 1) {
			for (Node<E> current = first, previous = null; current != null;) {
				Node<E> next = current.next;
				current.next = previous;
				previous = current;
				current = next;
			}
			Node<E> begin = first;
			first = tail.next;
			tail.next = begin;
			modCount++;
		}
		return this;
	}

	private void recursion(Node<E> current, Node<E> start) {
		if (current.next == null) {
			return;
		}

		Node<E> temp = current.next;
		current = current.next;
		temp.next = start;

		recursion(current, temp);
	}

	/**
	 * Recursive approach <br/>
	 * 
	 * Algorithm:<br/>
	 * - in first iteration first node will be replaced with his next one <br/>
	 * - to next iteration I will send first node - which now is pointing to
	 * next.next element and start node in list - which now will be second one<br/>
	 * - next iteration will start with updated position to first node<br/>
	 * Best visibility will give that picture: <br/>
	 * 
	 * <code><pre>
	 *            a-&gt;b-&gt;c-&gt;d-&gt;
	 *         b-&gt;a-&gt;c-&gt;d-&gt;
	 *      c-&gt;b-&gt;a-&gt;d-&gt;
	 *   d-&gt;c-&gt;b-&gt;a-&gt;
	 * </pre></code> Where:<br/>
	 * current is always pointing to the "a" node <br/>
	 * start is always pointing to first node in list ("a", then "b" then "c"
	 * ...) <br/>
	 * 
	 * @return reversed list
	 */
	public SinglyLinkedList<E> reverseRecursive() {
		if (size() > 1) {
			Node<E> oldBegin = first;
			recursion(first, first);
			first = tail;
			tail = oldBegin;

			modCount++;
		}
		return this;
	}

	/**
	 * Returns a forward only implementation of {@link ListIterator} over the
	 * elements in this list.
	 * <p>
	 * This implementation does not support methods
	 * {@link ListIterator#previous previous} and
	 * {@link ListIterator#hasPrevious()}
	 * 
	 * @return {@inheritDoc}
	 */
	@Override
	public ListIterator<E> listIterator() {
		return new SinglyLinkedListIterator(0);
	}

	@Override
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator(0);
	}

	/**
	 * Returns a forward only implementation of {@link ListIterator} over the
	 * elements in this list, starting at the specified position in this list.
	 * The specified index indicates the first element that would be returned by
	 * an call to {@link ListIterator#next next}.
	 * <p>
	 * This implementation does not support methods
	 * {@link ListIterator#previous previous} and
	 * {@link ListIterator#hasPrevious()}
	 * 
	 * @param index
	 *            index of first element to be returned from the list iterator
	 *            (by a call to the {@link ListIterator#next()} method)
	 * @return a forward only list iterator of the elements in this list (in
	 *         proper sequence), starting at the specified position in this list
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range
	 *             {@code (index < 0 || index > size())}
	 */
	@Override
	public ListIterator<E> listIterator(int fromIndex) {
		return new SinglyLinkedListIterator(fromIndex);
	}

	/** implements just the minimum set of forward iteration methods */
	final class SinglyLinkedListIterator implements ListIterator<E> {

		Node<E> previous = first; // previous is used for to enable removal of
									// node pointed by current
		Node<E> current = first; // current == previous means next() has not
									// been invoked
		int expectedModCount = modCount;
		int nextIndex = 0;

		SinglyLinkedListIterator(int fromIndex) {
			checkRange(fromIndex, size());
			for (int i = 0; i < fromIndex; ++i)
				next();
		}

		void checkForComodification() {
			if (expectedModCount != modCount) {
				throw new ConcurrentModificationException();
			}
		}

		@Override
		public boolean hasNext() {
			checkForComodification();
			return nextIndex != size();
		}

		@Override
		public int nextIndex() {
			return nextIndex;
		}

		@Override
		public void set(E e) {
			checkForComodification();
			if (current == previous)
				throw new IllegalStateException("invoke next() before set(E e)");
			current.e = e;
		}

		@Override
		public void remove() {
			checkForComodification();
			if (current == previous)
				throw new IllegalStateException("invoke next() before remove()");
			removeNode(previous);
			current = previous;
			nextIndex--;
			expectedModCount++;
		}

		@Override
		public void add(E e) {
			checkForComodification();
			addNode(current, e);
			previous = current;
			current = current.next;
			nextIndex++;
			expectedModCount++;
		}

		@Override
		public E next() {
			checkForComodification();
			if (nextIndex == size) {
				throw new NoSuchElementException();
			}

			previous = current;
			current = current.next;
			nextIndex++;
			return previous.e;
		}

		@Override
		public boolean hasPrevious() {
			throw new UnsupportedOperationException();
		}

		@Override
		public E previous() {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 * <p>
		 * Required by the implementation of
		 * {@link AbstractList#indexOf(Object)}
		 * 
		 * @return {@inheritDoc}
		 */
		@Override
		public int previousIndex() {
			return nextIndex - 1;
		}
	}

	private <T> Node<T> findNodeBefore(Node<T> start, int index) {
		while (index > 1) {
			start = start.next;
			index--;
		}
		return start;
	}

	// AbstractList.lastIndexOf requires proper previous() method.
	@Override
	public int lastIndexOf(Object o) {
		int i = 0, lastIndex = -1;
		for (E e : this) {
			if (areEqual(o, e)) {
				lastIndex = i;
			}
			i++;
		}
		return lastIndex;
	}

	boolean areEqual(Object one, Object two) {
		return (one == two) || (one == null ? two == null : one.equals(two));
	}

	void t(boolean condition) {
		if (!condition)
			throw new IllegalStateException();
	}

	int checkRange(int index, int end) {
		if (index < 0 || index > end) {
			throw new IndexOutOfBoundsException(String.format(
					"index %d is outside of range [ 0, %d] ", index, end));
		}
		return index;
	}

	/**
	 * Unit tests - verifies the class' invariants:
	 * 
	 * <li>calculated size == number of elements 
	 * <li>head -> first 
	 * <li>tail -> last <==> tail.next.next == end 
	 * <li>head->end - starting from head one
	 * can reach end and the number of nodes between is equal to the
	 * <tt>expectedSize</tt>
	 * 
	 * @param expectedSize
	 *            expected size of the list to be compared with the real size of
	 *            the list
	 * @throws IllegalStateException
	 *             if any invariant is violated
	 * @throws IllegalArgumentException
	 *             if <tt>expectedSize</tt> is less than zero.
	 */
	void checkInvariants(int expectedSize) {
		if (expectedSize < 0)
			throw new IllegalArgumentException();

		t(size == expectedSize);

		final boolean empty = (first.next == null & isEmpty());
		t(size == 0 ? empty : !empty);
		
		t(tail.next == null);
		
		// count the nodes between first and last
		int nNodes = 0;
		Node<E> c = first;
		while (tail.next != c) {
			++nNodes;
			c = c.next;
			t(nNodes <= size); 
		}
		t(c == null);
		t(nNodes == size);
	}
}