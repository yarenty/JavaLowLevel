package com.yarenty.strings;

/**
 * I like parentheticals (a lot).
 * "Sometimes (when I nest them (my parentheticals) too much (like this (and this))) they get confusing."
 * <p/>
 * Write a function that, given a sentence like the one above, along with the position of an opening parenthesis,
 * finds the corresponding closing parenthesis.
 * <p/>
 * Example: if the example string above is input with the number 10 (position of the first parenthesis),
 * the output should be 79 (position of the last parenthesis).
 * <p/>
 * Gotchas
 * We can do this in O(n)O(n) time.
 * <p/>
 * We can do this in O(1)O(1) additional space.
 * <p/>
 * Breakdown
 * How would you solve this problem by hand with an example input?
 * <p/>
 * Try looping through the string, keeping a count of how many open parentheses we have.
 * <p/>
 * Solution
 * We simply walk through the the string, starting at our input opening parenthesis position.
 * As we iterate, we keep a count of how many additional "(" we find as open_nested_parens.
 * When we find a ")" we decrement open_nested_parens. If we find a ")" and open_nested_parens is 0,
 * we know that ")" closes our initial "(", so we return its position.
 * <p/>
 * def get_closing_paren(sentence, opening_paren_index):
 * open_nested_parens = 0
 * position = opening_paren_index + 1
 * for char in sentence[position:]:
 * if char == '(':
 * open_nested_parens += 1
 * elif char == ')':
 * if open_nested_parens == 0:
 * return position
 * else:
 * open_nested_parens -= 1
 * position += 1
 * raise Exception("No closing parenthesis :(")
 * Complexity
 * O(n)O(n) time, where nn is the number of chars in the string. O(1)O(1) space.
 * <p/>
 * <p/>
 * <p/>
 * Created by yarenty on 08/04/15.
 */
public class Parentheticals {
}
