package com.yarenty.sort;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


/**
 * OUTPUT:
<pre>

</pre>
 * @author yarenty
 *
 */
public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// int[] a = { 1, 23, 45, 2, 8, 134, 9, 4, 2000 };
		int a[] = { 23, 44, 1, 2009, 2, 88, 123, 7, 999, 1040, 88 };
		quickSort(a, 0, a.length - 1);
		for (int i : a) {
			System.out.print(i+" ");
		}
		Deque<Integer> stack = new ArrayDeque<Integer>();
		Queue<Integer> q = new ArrayBlockingQueue<Integer>(0);
		
	}

	public static void quickSort(int[] a, int p, int r) {
		if (p < r) {
			int q = partition(a, p, r);
			quickSort(a, p, q);
			quickSort(a, q + 1, r);
		}
	}

	private static int partition(int[] a, int p, int r) {

		int x = a[p];
		int i = p - 1;
		int j = r + 1;

		while (true) {
			i++;
			while (i < r && a[i] < x)
				i++;
			j--;
			while (j > p && a[j] > x)
				j--;

			if (i < j)
				swap(a, i, j);
			else
				return j;
		}
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}
