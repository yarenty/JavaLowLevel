package com.yarenty;

/**
 * You've implemented a Stack class, but you want to be able to access the largest element in a stack.
 Here's the Stack class you have:

 class Stack:

 # intialize an empty list
 def __init__(self):
 self.items = []

 # push a new item to the last index
 def push(self, item):
 self.items.append(item)

 # remove the last item
 def pop(self):
 # if the stack is empty, return None
 # (it would also be reasonable to throw an exception)
 if not self.items: return None

 return self.items.pop()

 # see what the last item is
 def peek(self):
 # if the stack is empty, return None
 if not self.items: return None

 return self.items[len(self.items)-1]
 Use your Stack class to implement a new class MaxStack with a function get_max() that returns the largest element
 in the stack. get_max() should not remove the item.

 Your stacks will contain only integers.


 *
 * Created by yarenty on 04/02/15.
 */
public class LargerElementInStack {




    /*
    * One lazy ↴ approach is to have get_max() simply walk through the stack and find the max element.
    * This takes O(n) time for each call to get_max(). But we can do better.
    *
    *
    * To get O(1) time for get_max(), we could store the max integer as a member variable (call it max). But how would we keep it up to date
    *
    *
    *
To get O(1) time for get_max(), we could store the max integer as a member variable (call it max). But how would we keep it up to date?

For every push(), we can check to see if the item being pushed is larger than the current max, assigning it as our new max if so. But what happens when we pop() the current max? We could re-compute the current max by walking through our stack in O(n) time. So our worst-case runtime for pop() would be O(n). We can do better.

What if when we find a new current max (new_max), instead of overwriting the old one (old_max) we held onto it, so that once new_max was popped off our stack we would know that our max was back to old_max?

What data structure should we store our set of maxs in? We want something where the last item we put in is the first item we get out ("last in, first out").

We can store our maxs in another stack!
    * */
}
