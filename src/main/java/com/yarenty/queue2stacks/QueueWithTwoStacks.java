package com.yarenty.queue2stacks;

/**
 *
 Implement a queue with 2 stacks ↴ .
 Your queue should have an enqueue and a dequeue function and it should be "first in first out" (FIFO).
 Optimize for the time cost of m function calls on your queue. These can be any mix of enqueue and dequeue calls.

 Assume you already have a stack implementation and it gives O(1) time push and pop.




 Breakdown
 Let's call our stacks stack1 and stack2.

 To start, we could just push items onto stack1 as they are enqueued.
 So if our first 3 function calls are enqueues of a, b, and c (in that order) we push them onto stack1 as they come in.

 But recall that stacks are last in, first out. If our next function call was a dequeue()
 we would need to return a, but it would be on the bottom of the stack.


 Look at what happens when we pop c, b, and a one-by-one from stack1 to stack2.



 Notice how their order is reversed.

 We can pop each item 1-by-1 from stack1 to stack2 until we get to a.

 We could return a immediately, but what if our next operation was to enqueue a new item d?
 Where would we put d? d should get dequeued after c, so it makes sense to put them next to each-other . . .
 but c is at the bottom of stack2.


 Let's try moving the other items back onto stack1 before returning. This will restore the ordering from
 before the dequeue, with a now gone. So if we enqueue d next, it ends up on top of c, which seems right.


 So we're basically storing everything in stack1, using stack2 only for temporarily "flipping"
 all of our items during a dequeue to get the bottom (oldest) element.

 This is a complete solution. But we can do better.

 What's our time complexity for m operations? At any given point we have O(m) items inside our data structure,
 and if we dequeue we have to move all of them from stack1 to stack2 and back again. One dequeue operation
 thus costs O(m). The number of dequeues is O(m), so our worst-case runtime for these m operations is O(m2).

 Not convinced we can have O(m) dequeues and also have each one deal with O(m) items in the data structure?
 What if our first .5m operations are enqueues, and the second .5m are alternating enqueues and dequeues.
 For each of our .25m dequeues, we have .5m items in the data structure.

 We can do better than this O(m2) runtime.

 What if we didn't move things back to stack1 after putting them on stack2?

 * Created by yarenty on 11/02/2015.
 */
public class QueueWithTwoStacks {
}
