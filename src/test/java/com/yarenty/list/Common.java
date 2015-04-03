package com.yarenty.list;


import junit.framework.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Common {


    static <E> void doTestIOBE(final List<? extends List<E>> underTest
            , final int upperOffset
            , final Method op
            , final Object... argument) {
        for (final List<E> lst : underTest) {
            final List<Object> args = new LinkedList<Object>(Arrays.asList(argument));
            args.add(0, new Object());
            final int[] invalidIndices = {-2, -1, 100, lst.size() + upperOffset};
            for (final int index : invalidIndices) {
                final int size = lst.size();
                args.set(0, index);
                expectException(lst, op, IndexOutOfBoundsException.class, args.toArray());
                // verify invariants. Lists should remain operational after IOBE
                if (lst instanceof SinglyLinkedList<?>) {
                    ((SinglyLinkedList<?>) lst).checkInvariants(size);
                } else {
                    t(size == lst.size());
                }
            }
        }
    }

    static <E> void doTestIsEmpty(final SinglyLinkedList<E> lst) {
        t(lst.isEmpty());
        t(lst.size() == 0);
        isExhausted(lst.listIterator(), lst.size());
        t(lst);
    }

    static <T> void doTestIterator(final SinglyLinkedList<T> lst, final ListIterator<T> i, int offset, final List<T> compareWith) {
        t(i.hasNext());
        t(i.nextIndex() == offset);
        t(i.previousIndex() == offset - 1);
        t(lst, compareWith);
        while (i.hasNext()) {
            final T e = i.next();
            t(lst.areEqual(e, compareWith.get(offset++)));
            t(lst, compareWith);
        }
        t(!i.hasNext());
        t(i.nextIndex() == compareWith.size());
        t(i.previousIndex() == compareWith.size() - 1);
        t(lst, compareWith);
    }


    static <E> void isExhausted(final ListIterator<E> i, final int size) {
        try {
            i.next();
            t(false);
        } catch (NoSuchElementException nsee) {
        }
        t(i.nextIndex() == size);
        t(i.previousIndex() == size - 1);
        t(!i.hasNext());
    }

    static void expectException(final Object o, final Method m, final Class<?> expected, final Object... arg) {
        try {
            m.invoke(o, arg);
        } catch (InvocationTargetException ite) {
            Assert.assertSame(ite.getCause().getClass(), expected);
        } catch (Exception e) {
            throw new RuntimeException("reflection invocation failed", e);
        }
    }

    static <E> void throwsCME(final Object o, final Method m, final Object... arguments) {
        expectException(o, m, ConcurrentModificationException.class, arguments);
    }

    static <E> void doTestIteratorConcurrentModificationException(final ListIterator<E> it, final E arg) {
        try {
            throwsCME(it, ListIterator.class.getMethod("next"));
            throwsCME(it, ListIterator.class.getMethod("set", Object.class), arg);
            throwsCME(it, ListIterator.class.getMethod("remove"));
            throwsCME(it, ListIterator.class.getMethod("add", Object.class), arg);

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    static void t(final boolean condition) {
        Assert.assertTrue(condition);
    }

    static <E> void t(final SinglyLinkedList<E> list, final E... array) {
        t(list, Arrays.asList(array));
    }

    static <E> void t(final SinglyLinkedList<E> list, final List<E> expected) {
        list.checkInvariants(expected.size());
        if (expected instanceof SinglyLinkedList<?>)
            ((SinglyLinkedList<?>) expected).checkInvariants(list.size());
        Assert.assertTrue(expected.equals(list));
    }

    @SuppressWarnings("unused")
    static void out(final Object o) {
        System.out.println(o);
    }

    static void dump(final List<?> list) {
        for (final Object e : list) {
            System.out.print(e + ", ");
        }
        out("");
    }
}
