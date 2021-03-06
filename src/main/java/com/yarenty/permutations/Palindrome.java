package com.yarenty.permutations;

/**
 * Write an efficient function that checks whether any permutation ↴ of an input string is a palindrome ↴ .
 * Examples:
 * <p/>
 * "civic" should return true
 * "ivicc" should return true
 * "civil" should return false
 * "livci" should return false
 * Gotchas
 * We can do this in O(n)O(n) time.
 * <p/>
 * Breakdown
 * The brute force ↴ approach would be to check every permutation of the input string to see if it is a palindrome.
 * <p/>
 * What would be the time cost? For a string of length n, there are n!n! permutations (nn choices for the first character, times n-1n−1 choices for the second character, etc). Checking each length-nn permutation to see if it's a palindrome involves checking each character, taking O(n)O(n) time. That gives us O(n!n)O(n!n) time overall. We can do better!
 * <p/>
 * Let's try rephrasing the problem. How can we tell if any permutation of a string is a palindrome?
 * <p/>
 * Well, how would we check that a string is a palindrome? We could use the somewhat-common "keep two pointers" pattern.
 * We'd start a pointer at the beginning of the string and a pointer at the end of the string, and check that
 * the characters at those pointers are equal as we walk both pointers towards the middle of the string.
 * <p/>
 * civic
 * ^   ^
 * <p/>
 * civic
 * ^ ^
 * <p/>
 * civic
 * ^
 * Can we adapt the idea behind this approach to checking if any permutation of a string is a palindrome?
 * <p/>
 * Notice: we're essentially checking that each character left of the middle has a corresponding copy right of the middle.
 * <p/>
 * We can simply check that each character appears an even number of times (unless there is a middle character,
 * which can appear once or some other odd number of times). This ensures that the characters can be ordered
 * so that each char on the left side of the string has a matching char on the right side of the string.
 * <p/>
 * But we'll need a data structure to keep track of the number of times each character appears. What should we use?
 * <p/>
 * We should use a hash map. Tip: using a hash map is the most common way to get from a brute force approach
 * to something more clever. It should always be your first thought.
 * <p/>
 * Solution
 * Our approach is to check that each character appears an even number of times (unless we have an odd number
 * of characters—then the "middle" character can appear an odd number of times). This is sufficient for proving
 * that some permutation of the characters is a palindrome.
 * <p/>
 * First, we iterate through our input string, keeping track of the parity (whether it occurs an even or odd
 * number of times) of all characters in a hash map called parity_map. We'll use a boolean True to represent
 * odd parity, and False to represent even parity.
 * <p/>
 * As we iterate through the string:
 * <p/>
 * If we see a character that is not in the hash map, add it with the parity odd.
 * If we see a character that is in the hash map, flip its parity.
 * Next, we iterate through our parity_map, confirming that each character, except the potential middle one,
 * has an even parity. If there is exactly one character with an odd parity, we know our total number of characters
 * is odd, so the character with odd parity is the "middle" character in our palindrome permutation.
 * <p/>
 * So we iterate through the parity values of the hash map:
 * <p/>
 * If we see an odd parity for the first time, note this with a flag odd_seen.
 * If we see an odd parity again, return false.
 * If we finish iterating through the parity values, return true.
 * def has_palindrome_permutation(the_string):
 * # key: character
 * # value: boolean whether or not the char has odd parity
 * parity_map = {}
 * <p/>
 * # get parity of each unique character
 * for char in the_string:
 * if char in parity_map:
 * parity_map[char] = not parity_map[char]
 * else:
 * parity_map[char] = True
 * <p/>
 * odd_seen = False
 * # make sure we have one or fewer odd parities
 * for has_odd_parity in parity_map.itervalues():
 * if has_odd_parity:
 * if odd_seen:
 * return False
 * else:
 * odd_seen = True
 * <p/>
 * return True
 * Complexity
 * O(n)O(n) time, since we're making one iteration through the nn characters in the string and one more iteration
 * through the set of unique characters in the string (which is less than or equal to nn).
 * <p/>
 * Our parity_map is the only thing taking up non-constant space. We could say our space cost is O(n)O(n) as well,
 * since the set of unique characters is less than or equal to nn. But we can also look at it this way:
 * there are only so many different characters. How many? The ASCII character set has just 128 different characters
 * (standard english letters and punctuation), while Unicode has 110,000 (supporting several languages and some
 * icons/symbols). We might want to treat our number of possible characters in our character set as another variable
 * kk and say our space complexity is O(k)O(k). Or we might want to just treat it as a constant, and say our space
 * complexity is O(1)O(1).
 * <p/>
 * By storing booleans representing parity instead of just storing counts and checking for parity at the end,
 * we avoid integer overflow in the case where some characters appear an arbitrarily large number of times.
 * <p/>
 * <p/>
 * <p/>
 * Created by yarenty on 01/04/15.
 */
public class Palindrome {
}
