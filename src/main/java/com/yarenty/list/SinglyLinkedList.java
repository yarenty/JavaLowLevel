package com.yarenty.list;


import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * <b>TASK: Implement a simple singly-linked list, and two functions to reverse
 * the order of the list.<br/>
 * You must provide:<br/>
 * 1. An iterative reverse operation.<br/>
 * 2. A recursive reverse operation.<br/>
 * 3. Full coverage automated tests.<br/>
 * <br/>
 * </b>
 * <p/>
 * I choose to use <b>{@link AbstractSequentialList}</b>. From "
 * {@link <a href="http://docs.oracle.com/javase/6/docs/api/java/util/AbstractSequentialList.html">oracle web site </a>}
 * ":<br/>
 * <i> AbstractSequentialList provides a skeletal implementation of the List
 * interface to minimize the effort required to implement this interface backed
 * by a "sequential access" data store (such as a linked list). [...]</i><br/>
 * <p/>
 * This is singly linked list so only forward iteration operations are
 * supported. Methods {@link ListIterator#hasPrevious()} or
 * {@link ListIterator#previous()} will finish with
 * {@link UnsupportedOperationException}.
 * <p/>
 * The singly linked list implements {@link RandomAccess} to prevent various
 * {@link Collections} methods from invoking the above two methods.
 * <p/>
 * Therefore are 3 reverse methods available:<br/>
 * {@link #reverse()} - iterative reverse (quickest)<br/>
 * {@link #reverseRecursive()} - recursive reverse, could raise StavkOverflow
 * when list is too big<br/>
 * {@link Collections#reverse(List)} - standard collections reverse<br/>
 *
 * @param <E> the type of the elements held in the list.
 * @author yarenty@gmail.com
 * @see {http://docs.oracle.com/javase/6/docs/api/java/util/AbstractSequentialList.html}
 */
public final class SinglyLinkedList<E> extends AbstractSequentialList<E>
        implements RandomAccess, Cloneable, Serializable {

    /**
     * Implementation of node.
     *
     * @param <T>
     * @author yarenty
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
     */
    private Node<E> tail;

    /**
     * Empty list - initialize to speed up add operations.
     */
    public SinglyLinkedList() {
    }

    /**
     * Creates a list populated with elements.
     *
     * @param elements
     */
    public SinglyLinkedList(final E... elements) {
        this();
        for (final E e : elements)
            add(e);
    }

    /**
     * Constructs a list containing the elements of the specified collection, in
     * the order they are returned by the collection's iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public SinglyLinkedList(final Collection<? extends E> c) {
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
        for (final Iterator<E> i = iterator(); i.hasNext(); i.remove()) {
            i.next();
        }
    }

    /**
     * The only method that directly changes the structure adding an element
     *
     * @param after the node to insert after, assumption is that this is a valid
     *              node from the list in the range [head, last]
     * @param value the value to store
     * @return reference to the added element
     */
    private Node<E> addNode(Node<E> after, final E value) {
        Node<E> n;

        if (after == null) { //first node
            n = new Node<E>(value, first);

            if (first == null) {
                tail = n;
            }
            first = n;

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
     * @param after the node whose next is to be detached from the list and
     *              nullified, assumption is that {@code after} is a valid node in
     *              the range [ head, last )
     */
    private E removeNode(Node<E> after) {

        E toBeReturned = null;

        if (after.next != null) {
            final Node<E> removed = after.next;
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
     * @param e the element to append, can be {@code null}
     * @return {@code true}
     */
    @Override
    public boolean add(final E e) {
        return addNode(tail, e) != null;
    }

    /**
     * Inserts the specified element at the specified position in the list.
     *
     * @param index the position to insert at.
     * @param value the element to insert, can be {@code null}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void add(final int index, final E value) {
        if (checkRange(index, size()) == size()) {
            addNode(tail, value);
        } else {
            if (index == 0) {
                addNode(null, value);
            } else {
                addNode(findNodeBefore(first, index), value);
            }
        }
    }

    private boolean insertAllAfter(final Node<E> insertAfter,
                                   final Collection<? extends E> c) {
        Node<E> n = insertAfter;
        for (final E e : c) {
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
     * @param c collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean addAll(final Collection<? extends E> c) {
        return insertAllAfter(tail, c);
    }

    /**
     * Inserts all of the elements in the specified collection into this list,
     * starting at the specified position. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right (increases
     * their indices). The new elements will appear in the list in the order
     * that they are returned by the specified collection's iterator.
     *
     * @param index index at which to insert the first element from the specified
     *              collection
     * @param c     collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public boolean addAll(final int index, final Collection<? extends E> c) {
        checkRange(index, size());
        final Node n = (index == 0) ? null : findNodeBefore(first, index);
        return insertAllAfter(n, c);
    }

    /**
     * Removes the element at the specified position in this list and returns
     * the element that was removed from the list.
     *
     * @param index {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public E remove(final int index) {
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
     * @return {@code this} for chaining
     */
    public SinglyLinkedList<E> reverse() {
        if (size() > 1) {
            for (Node<E> current = first, previous = null; current != null; ) {
                final Node<E> next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            final Node<E> begin = first;
            first = tail.next;
            tail.next = begin;
            modCount++;
        }
        return this;
    }

    private void recursion(Node<E> current, final Node<E> start) {
        if (current.next == null) {
            return;
        }

        final Node<E> temp = current.next;
        current = current.next;
        temp.next = start;

        recursion(current, temp);
    }

    /**
     * Recursive approach <br/>
     * <p/>
     * Algorithm:<br/>
     * - in first iteration first node will be replaced with his next one <br/>
     * - to next iteration I will send first node - which now is pointing to
     * next.next element and start node in list - which now will be second one<br/>
     * - next iteration will start with updated position to first node<br/>
     * Best visibility will give that picture: <br/>
     * <p/>
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
            final Node<E> oldBegin = first;
            recursion(first, first);
            first = tail;
            tail = oldBegin;

            modCount++;
        }
        return this;
    }


    /**
     * Sorts the list according to their natural order, using
     * <a href="http://www.sorting-algorithms.com/merge-sort">merge sort algorithm</a>
     * <p/>
     * Properties:
     * <li>stable sort
     * <li>lg(n) recursion stack depth
     * <li>n * lg(n) running time
     * <p/>
     * Assumption: {@code E implements Comparable}
     *
     * @return the sorted list for chaining
     * @throws ClassCastException when elements of the list
     *                            are not {@link Comparable}
     */
    public void sort() {
        sort(naturalOrder());
    }

    /**
     * Sorts the list as ordered by the {@code compir}, using
     * <a href="http://www.sorting-algorithms.com/merge-sort">merge sort algorithm</a>
     * <p/>
     * Properties
     * <li>stable sort
     * <li>lg(n) recursion stack depth
     * <li>n * lg(n) running time
     *
     * @param compir the comparator to order the elements
     * @return the sorted list for chaining
     */
    //@Override
    public void sort(final Comparator<? super E> compir) {
        mergeSort(this.first, this.tail, size(), compir);
    }

    private <T>
    void mergeSort(final Node<T> h, final Node<T> t, final int len, final Comparator<? super T> comp) {
        if (len > 1) {
            final int m = len >>> 1;
            final Node<T> t1 = new Node<T>(null, findNodeBefore(h, m)); // need a full blown Node in order to change where it refers to
            mergeSort(h, t1, m, comp);
            mergeSort(t1.next, t, len - m, comp);
            if (comp.compare(t1.next.e, t1.next.next.e) > 0) // skip merging if already ordered
                merge(h, t1, t1.next, t, comp);
        }
    }

    // Merges the list [h2, t2] into [h1, t1].
    // The last element of the first list is head element of the second,
    // and the first element of the second list is the end() element of the first list.
    // The end() sentinel node cannot be used for end-of-list checks,
    // as during merging, the first element of the second list (the end() of the first list)
    // can be merged somewhere into the first list, thus destroying the sentinel invariant
    // To avoid this the lists must be designated as [head, tail] rather than [head, end)
    private <T>
    void merge( Node<T> h1, final Node<T> t1, final Node<T> h2, final Node<T> t2, final Comparator<? super T> comp) {
        for (; t1.next != h1 && t2.next != h2; h1 = h1.next) {
            if (comp.compare(h1.next.e, h2.next.e) > 0) { // insert at found position
                final Node<T> unlinked = h2.next; // unlink from second list
                h2.next = unlinked.next;
                unlinked.next = h1.next;    // link/insert into first list
                h1.next = unlinked;
                if (t2.next == unlinked) // as tail is not a proper element from the list, it must be manually updated
                    t2.next = h2; // the new last element. Basically the second list was emptied
            }
        }
    }

    /**
     * Sorts the list using parallel merge algorithm
     *
     * @param pooly  fork join pool to use for task parallelisation
     * @param compir to order the elements
     * @return current list for method chaining
     */
    @SuppressWarnings("serial")
    public SinglyLinkedList<E> parallelSort(final ForkJoinPool pooly, final Comparator<E> compir) {
        final int threshold = 1 + size() / (8 * Runtime.getRuntime().availableProcessors());
        pooly.invoke(new RecursiveAction() {
            @Override
            protected void compute() {
                doParallelSort(first, tail, size(), compir, Math.max(threshold, 256), pooly);
            }
        });
        return this;
    }

    private Comparator<E> naturalOrder() {
        return new Comparator<E>() {
            @SuppressWarnings({"rawtypes", "unchecked"})
            @Override
            public int compare(final E o1, final E o2) {
                return ((Comparable) o1).compareTo(o2);
            }
        };
    }

    /**
     * Sorts the list using parallel merge algorithm according
     * to the natural order of the elements
     *
     * @param pooly fork join pool to use for task parallelisation
     * @return current list for method chaining
     */
    public SinglyLinkedList<E> parallelSort(final ForkJoinPool pooly) {
        return parallelSort(pooly, naturalOrder());
    }

    /**
     * Sorts the list using parallel merge algorithm and a
     * default {@link ForkJoinPool}
     * according the natural order of the elements, using
     *
     * @return current list for method chaining
     */
    public SinglyLinkedList<E> parallelSort() {
        return parallelSort(naturalOrder());
    }

    /**
     * Sorts the list using parallel merge algorithm and
     * a default @link {@link ForkJoinPool}
     *
     * @param compir to order the elements
     * @return current list for method chaining
     */
    public SinglyLinkedList<E> parallelSort(final Comparator<E> compir) {
        final ForkJoinPool pooly = new ForkJoinPool();
        parallelSort(pooly, compir);
        pooly.shutdown();
        return this;
    }

    @SuppressWarnings("serial")
    private <T> void doParallelSort(final Node<T> h, final Node<T> t, final int len
            , final Comparator<T> comp, final int THRESHOLD
            , final ForkJoinPool pooly) {
        if (len < THRESHOLD) {
            mergeSort(h, t, len, comp);
        } else {
            final Node<T> originalEnd = t.next.next; // in case this is a sublist, end() != head
            final int m = len >>> 1;
            // Detach list1 from list2 to divide and concur in parallel
            // list1 requires a new tail and end , list2 a new head
            final Node<T> t1 = new Node<T>(null, findNodeBefore(h, m));
            final Node<T> h2 = new Node<T>(null, t1.next.next);
            t1.next.next = h; // the new end1, end() is always the head unless it's a sublist
            t.next.next = h2; // and new end2 -	but the originalEnd accounts for the sublist case
            final RecursiveAction right = new RecursiveAction() {
                @Override
                protected void compute() {
                    doParallelSort(h, t1, m, comp, THRESHOLD, pooly);
                }
            };
            pooly.execute(right);
            doParallelSort(h2, t, len - m, comp, THRESHOLD, pooly);
            if (right.tryUnfork()) {
                //System.err.println("stolen");
                doParallelSort(h, t1, m, comp, THRESHOLD, pooly);
            } else {
                right.join();
            }
            //
            if (comp.compare(t1.next.e, h2.next.e) > 0) {
                merge(h, t1, h2, t, comp);
            }
            if (t.next == h2) { //list2 is empty, just fix the tail
                t.next = t1.next;
            } else { // re-attach list1 and list2
                t1.next.next = h2.next;
            }
            t.next.next = originalEnd; // fix the end of the list
        }
    }


    /**
     * Returns a forward only implementation of {@link ListIterator} over the
     * elements in this list.
     * <p/>
     * This implementation does not support methods
     * {@link ListIterator#previous previous} and
     * {@link ListIterator#hasPrevious()}
     *
     * @return {@inheritDoc}
     */
    @Override
    public final ListIterator<E> listIterator() {
        return new SinglyLinkedListIterator(0);
    }

    @Override
    public final Iterator<E> iterator() {
        return new SinglyLinkedListIterator(0);
    }

    /**
     * Returns a forward only implementation of {@link ListIterator} over the
     * elements in this list, starting at the specified position in this list.
     * The specified index indicates the first element that would be returned by
     * an call to {@link ListIterator#next next}.
     * <p/>
     * This implementation does not support methods
     * {@link ListIterator#previous previous} and
     * {@link ListIterator#hasPrevious()}
     *
     * @param fromIndex index of first element to be returned from the list iterator
     *                  (by a call to the {@link ListIterator#next()} method)
     * @return a forward only list iterator of the elements in this list (in
     * proper sequence), starting at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   {@code (index < 0 || index > size())}
     */
    @Override
    public final ListIterator<E> listIterator(final int fromIndex) {
        return new SinglyLinkedListIterator(fromIndex);
    }

    /**
     * implements just the minimum set of forward iteration methods
     */
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
         * <p/>
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


    /**
     * Returns a view of the portion of this list between the specified
     * <tt>fromIndex</tt>, inclusive, and <tt>toIndex</tt>, exclusive.  (If
     * <tt>fromIndex</tt> and <tt>toIndex</tt> are equal, the returned list is
     * empty.)  The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported
     * by this list.<p>
     * <p/>
     * This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).   Any operation that expects
     * a list can be used as a range operation by passing a subList view
     * instead of a whole list.  For example, the following idiom
     * removes a range of elements from a list:
     * <pre>
     *      list.subList(from, to).clear();
     * </pre>
     * Similar idioms may be constructed for <tt>indexOf</tt> and
     * <tt>lastIndexOf</tt>
     * <p/>
     * The sublist is <i>fail-fast</i>: if the list is structurally
     * modified by the same thread at any time after the sublist is created, in any way except
     * through the sublist methods or via the methods of an iterator spawned by the sublist,
     * the sublist will throw a <tt>ConcurrentModificationException</tt>.  Thus, in the face of
     * concurrent modification, the sublist fails quickly and cleanly, rather
     * than risking arbitrary, non-deterministic behavior at an undetermined
     * time in the future. Mind that fail-fast behavior does not mean thread safe behavior.
     *
     * @param {@inheritDoc}
     * @param {@inheritDoc}
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException for an illegal end-point index value
     *                                   {@code fromIndex < 0 || toIndex > size }
     * @throws IllegalArgumentException  for {@code fromIndex > toIndex}. This should be a {@link IndexOutOfBoundsException}
     *                                   however all implementations of {@link List} in JDK violate the contract of {@link List#subList(int, int),
     *                                   so conform to violation practice.
     */
    @Override
    public final SinglyLinkedList<E> subList(final int fromIndex, final int toIndex) {
        checkRange(fromIndex, size());
        checkRange(toIndex, size());
        final int len = toIndex - fromIndex;
        if (len < 0) {
            throw new IndexOutOfBoundsException("toIndex " + toIndex + " must be >= fromIndex " + fromIndex);
        }
        final Node<E> h = findNodeBefore(first, fromIndex); // the head of the sublist
        final Node<E> last = findNodeBefore(h, len);       // the last element in the sublist
        final Node<E> sTail = (tail.next != last ? new Node<E>(null, last) : tail);
        return new SinglyLinkedList<E>(h, last, len);
    }

    private SinglyLinkedList(final Node<E> head, final Node<E> tail, final int len) {
        this.first = head;
        this.tail = tail;
        this.size = len;
    }

    private final <T> Node<T> findNodeBefore(Node<T> start, int index) {
        while (index > 1) {
            start = start.next;
            index--;
        }
        return start;
    }

    // AbstractList.lastIndexOf requires proper previous() method.
    @Override
    public final int lastIndexOf(final Object o) {
        int i = 0, lastIndex = -1;
        for (final E e : this) {
            if (areEqual(o, e)) {
                lastIndex = i;
            }
            i++;
        }
        return lastIndex;
    }


    boolean areEqual(final Object one, final Object two) {
        return (one == two) || (one == null ? two == null : one.equals(two));
    }

    void t(final boolean condition) {
        if (!condition)
            throw new IllegalStateException();
    }

    int checkRange(final int index, final int end) {
        if (index < 0 || index > end) {
            throw new IndexOutOfBoundsException(String.format(
                    "index %d is outside of range [ 0, %d] ", index, end));
        }
        return index;
    }

    /**
     * Unit tests - verifies the class' invariants:
     * <p/>
     * <li>calculated size == number of elements
     * <li>head -> first
     * <li>tail -> last <==> tail.next.next == end
     * <li>head->end - starting from head one
     * can reach end and the number of nodes between is equal to the
     * <tt>expectedSize</tt>
     *
     * @param expectedSize expected size of the list to be compared with the real size of
     *                     the list
     * @throws IllegalStateException    if any invariant is violated
     * @throws IllegalArgumentException if <tt>expectedSize</tt> is less than zero.
     *
     * TODO: fixme
     */
    void checkInvariants(final int expectedSize) {
        if (expectedSize < 0)
            throw new IllegalArgumentException();

        t(size == expectedSize);

        boolean empty = (first == null & isEmpty());
        if (!empty) empty = (first.next == null & isEmpty());

        //t(size == 0 ? empty : !empty);

//        if (!empty) {
//            t(tail.next != null);
//
//            // count the nodes between first and last
//            int nNodes = 0;
//            Node<E> c = first;
//            while (tail.next != c) {
//                ++nNodes;
//                c = c.next;
//                t(nNodes <= size);
//            }
//            t(c == null);
//            t(nNodes == size);
//        }
    }
}