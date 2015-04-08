package com.yarenty.list;

import java.util.LinkedList;

/**
 *
 * You have a singly-linked list and want to check if it contains a cycle.
 A singly-linked list is built with Nodes, where each node has:

 node.next—the next node in the list.
 node.data—the data held in the node. For example, if our linked list stores people in line at the movies,
 node.data might be the person's name.
 A cycle occurs when a node’s next points back to a previous node in the list. The linked list is no longer
 linear with a beginning and end—instead, it cycles through a loop of nodes.

 Write a function contains_cycle() that takes the first node in a singly-linked list and returns a boolean indicating
 whether the list contains a cycle.

 For this problem, you cannot make any changes to the Node class.

 * Created by yarenty on 04/02/15.
 */
public class SinglyLinkedListCycle {


    private static LinkedList<String> list = new LinkedList<String>();


    private static void fillList() {
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("fourth");
        list.add("fifth");
        list.addLast(list.getFirst()); //is this loop?

    }

    public static void main(String[] args){

        System.out.println("List has circle::" + checkWithMap());

    }

    private static boolean checkWithMap() {

        list.getFirst();
        list.poll();

        return false;
    }

    /*
    Because a cycle could result from the last node linking to the first node, we might need to look at every node
    before we even see the start of our cycle again. So it seems like we can’t do better than O(n) runtime.

How can we track the nodes we’ve already seen?
    *
    *
    *
    *
    * Using a hash map, we could store all the nodes we’ve seen so far. The algorithm is simple:

If the current node is already in our hash map, we have a cycle. Return True.
If the current node is None we've hit the end of the list. Return False.
Else throw the current node in our hash map and keep going.
What are the time and space costs of this approach? Can we do better?




Our runtime is O(n), the best we can do. But our space cost is also O(n).
Can we get our space cost down to O(1) by storing a constant number of nodes?


Think about a looping list and a linear list. What happens when you traverse one versus the other?




A linear list has an end—a node that doesn’t have a next node. But a looped list will run forever.
We know we don’t have a loop if we ever reach an end node, but how can we tell if we’ve run into a loop?

We can’t just run our function for a really long time, because we’d never really know with certainty
if we were in a loop or just a really long list.

Imagine that you're running on a long, mountainous running trail that happens to be a loop.
What are some ways you can tell you're running in a loop?

One way is to look for landmarks. You could remember one specific point, and if you pass that point again,
 you know you’re running in a loop. Can we use that principle here?

Well, our cycle can occur after a non-cyclical "head" section in the beginning of our linked list.
So we'd need to make sure we chose a "landmark" node that is in the cyclical "tail" and not in the
non-cyclical "head." That seems impossible unless we already know whether or not there's a cycle...

Think back to the running trail. Besides landmarks, what are some other ways you could tell
you’re running in a loop? What if you had another runner? (Remember, it’s a singly-linked list, so no running backwards!)

A tempting approach could be to have the other runner stop and act as a "landmark," and see if you
pass her again. But we still have the problem of making sure our "landmark" is in the loop and not in the
non-looping beginning of the trail.

What if our "landmark" runner moves continuously but slowly?

If we sprint quickly down the trail and the landmark runner jogs slowly, we will eventually "lap" (catch up to)
the landmark runner!

But what if there isn't a loop?

Then we (the faster runner) will simply hit the end of the trail (or linked list).

So let's make two variables, slow_runner and fast_runner. We’ll start both on the first node, and every time slow_runner
 advances one node, we’ll have fast_runner advance two nodes.

If fast_runner catches up with slow_runner, we know we have a loop. If not, eventually fast_runner will hit the end
of the linked list and we'll know we don't have a loop.

This is a complete solution! Can you code it up?

Make sure the function eventually terminates in all cases!
*/
}
