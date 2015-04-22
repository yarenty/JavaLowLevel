package com.yarenty.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * We have an array of integers, where:
 * <p/>
 * The integers are in the range 1..n
 * The array has a length of n+1
 * It follows that our array has at least one integer which appears at least twice. But it may have several duplicates,
 * and each duplicate may appear more than twice.
 * <p/>
 * Write a function which finds any integer that appears more than once in our array.
 * <p/>
 * We're going to run this function on our new, super-hip Macbook Pro With Retina Display™. Thing is, the damn thing
 * came with the RAM soldered right to the motherboard, so we can't upgrade our RAM. So we need to optimize for space!
 * <p/>
 * <p/>
 * Created by yarenty on 22/04/15.
 */
public class Duplicates {


    final int[] array1 = {1, 2, 3, 4, 2, 3, 4, 1, 2, 5, 7, 8, 9};


    public static void main(final String[] args) {

        new Duplicates().test();


    }

    void test() {
        System.out.println("Start\n Input array");
        System.out.println(Arrays.toString(array1));
        System.out.println(" MAP solution: " + mapSolution(array1));
        System.out.println(" SORT solution: " + sortSolution(array1));
    }

    int mapSolution(final int[] a) {
        final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(a[i])) {
                // map.put(array1[i], map.get(array1[i]) + 1);
                return a[i]; //first that has double
            } else {
                map.put(a[i], 1);
            }
        }
        return 0;
    }

    int sortSolution(final int[] a) {
        Arrays.sort(a);
        int prev = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] == prev) {
                return prev; //found
            } else {
                prev = a[i];
            }
        }
        return 0;
    }


    int breakAndSeek() {


        return 0;
    }

/**
 * Breakdown
 This one's a classic! We just do one walk through the array, using a hash map to keep track of which items we've seen!

 def find_repeat_hash(numbers):
 numbers_seen = {}
 for number in numbers:
 if number in numbers_seen:
 return number
 else:
 numbers_seen[number] = True

 # whoops--no duplicate
 raise Exception('no duplicate!')
 Bam. O(n)O(n) time and ... O(n)O(n) space ...

 Right, we're supposed to optimize for space. O(n)O(n) is actually kinda high space-wise. Hm. We can probably get O(1)O(1)...

 We can "brute force" this by taking each number in the range 1..n1..n and, for each, walking through the array to see if it appears twice.

 def find_repeat_brute_force(numbers):
 for needle in range(1,len(numbers)):
 has_been_seen = False
 for number in numbers:
 if number == needle:
 if has_been_seen:
 return number
 else:
 has_been_seen = True

 # whoops--no duplicate
 raise Exception('no duplicate!')
 This is O(1)O(1) space and O(n^2)O(n
 ​2
 ​​ ) time.

 That space complexity can't be beat, but the time cost seems a bit high. Can we do better?

 One way to beat O(n^2)O(n
 ​2
 ​​ ) time is to get O(n\lg{n})O(nlgn) time. Sorting takes O(n\lg{n})O(nlgn) time. And if we sorted the array, any duplicates would be right next to each-other!

 But if we start off by sorting our array we'll need to take O(n)O(n) space to store the sorted array...

 ...unless we sort the input array in place!

 Okay, so this'll work:

 Do an in-place sort of the array (for example an in-place mergesort).
 Walk through the now-sorted array from left to right.
 Return as soon as we find two adjacent numbers which are the same.
 This'll keep us at O(1)O(1) space and bring us down to O(n\lg{n})O(nlgn) time.

 But destroying the input is kind of a drag—it might cause problems elsewhere in our code. Can we maintain this time and space cost without destroying the input?

 Let's take a step back. How can we break this problem down into subproblems?

 If we're going to do O(n\lg{n})O(nlgn) time, we'll probably be iteratively doubling something or iteratively cutting something in half. That's how we usually get a lgnlgn. So what if we could cut the problem in half somehow?

 Well binary search ↴ works by cutting the problem in half after figuring out which half of our input array holds the answer.

 But in a binary search the reason we can confidently say which half has the answer is because the array is sorted. For this problem, when we cut our unsorted array in half we can't really make any strong statements about which elements are in the left half and which are in the right half.

 What if we could cut the problem in half a different way, other than cutting the array in half?

 With this problem, we're looking for a needle (a repeated number) in a haystack (an array). What if instead of cutting the haystack in half, we cut the set of possibilities for the needle in half?

 The full range of possibilites for our needle is 1..n1..n. How could we test whether the actual needle is in the first half of that range (1..\frac{n}{2}1..
 ​2

 n
 ​​ ) or the second half (\frac{n}{2}+1..n
 ​2

 n
 ​​ +1..n)?

 A quick note about how we're defining our ranges: when we take \frac{n}{2}
 ​2

 n
 ​​  we're doing integer division, so we throw away the remainder. To see what's going on, we should look at what happens when nn is even and when nn is odd:

 If nn is 66 (an even number), we have \frac{n}{2}=3
 ​2

 n
 ​​ =3 and \frac{n}{2}+1=4
 ​2

 n
 ​​ +1=4, so our ranges are 1..31..3 and 4..64..6.
 If nn is 55 (an odd number), \frac{n}{2}=2
 ​2

 n
 ​​ =2 (we throw out the remainder) and \frac{n}{2}+1=3
 ​2

 n
 ​​ +1=3, so our ranges are 1..21..2 and 3..53..5.
 So we can notice a few properties about our ranges:

 They aren't necessarily the same size.
 They don't overlap.
 Taken together, they represent the original input array's range of 1..n1..n. In math terminology, we could say their union is 1..n1..n.
 So, how do we know if the needle is in 1..\frac{n}{2}1..
 ​2

 n
 ​​  or \frac{n}{2}+1..n
 ​2

 n
 ​​ +1..n?

 Think about the original problem statement. We know that we have at least one repeat because there are n+1n+1 items and they are all in the range 1..n1..n, which contains only nn distinct integers.

 This notion of "we have more items than we have possibilities, so we must have at least one repeat" is pretty powerful. It's sometimes called the pigeonhole principle ↴ . Can we exploit the pigeonhole principle to see which half of our range contains a repeat?

 Imagine that we separated the input array into two subarrays—one containing the items in the range 1..\frac{n}{2}1..
 ​2

 n
 ​​  and the other containing the items in the range \frac{n}{2}+1..n
 ​2

 n
 ​​ +1..n.

 Each subarray has a number of elements as well as a number of possible distinct integers (that is, the length of the range of possible integers it holds).

 Given what we know about the number of elements vs the number of possible distinct integers in the original input array, what can we say about the number of elements vs the number of distinct possible integers in these subarrays?

 The sum of the subarrays' numbers of elements is n+1n+1 (the number of elements in the original input array) and the sum of the subarrays' numbers of possible distinct integers is nn (the number of possible distinct integers in the original input array).

 Since the sums of the subarrays' numbers of elements must be 1 greater than the sum of the subarrays' numbers of possible distinct integers, one of the subarrays must have at least one more element than it has possible distinct integers.

 Not convinced? We can prove this by contradiction. Suppose neither array had more elements than it had possible distinct integers. In other words, both arrays have at most the same number of items as they have distinct possibilites. The sum of their numbers of items would then be at most the total number of possibilities across each of them, which is nn. This is a contradiction—we know that our total number of items from the original input array is n+1n+1, which is greater than nn.

 Now that we know one of our subarrays has 1 or more items more than it has distinct possibilities, we know that subarray must have at least one duplicate, by the same pigeonhole argument that we use to know that the original input aray has at least one duplicate.

 So once we know which subarray has the count higher than its number of distinct possibilites, we can use this same approach recursively, cutting that subarray into two halves, etc, until we have just 1 item left in our range.

 Of course, we don't need to actually separate our array into subarrays. All we care about is how long each subarray would be. So we can simply do one walk through the input array, counting the number of items that would be in each subarray.

 Can you formalize this in code?

 Careful—if we do this recursively, we'll incur a space cost in the call stack! Do it iteratively instead.
 */
}
