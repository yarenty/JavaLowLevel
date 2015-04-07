package com.yarenty.strings;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PalindromeTester {


    String s = "Abba ab ba abbA"; //palindrome
    String s2 = "AbA";//palindrome
    String s3 = "Abba    ab    ba abbA";//palindrome only on _no_space_ solution
    String s4 = "A";//palindrome


    public boolean palindromeReverseTest(String s) {

        String s1 = reverse(s);
        System.out.println("s:["+s+"]");
        System.out.println("s1:["+s1+"]");
        return s.equals(s1);
    }


    /**
     * @param s
     * @return reverse string
     */
    public String reverse(String s) {
        String s1="";

        for(int i=s.length()-1; i>=0; i--){
            s1+=s.charAt(i);
        }
        return s1;
    }


    public boolean palindromeTest(String s) {

        for(int i=0; i<s.length()/2; i++){
            if (s.charAt(i) != s.charAt(s.length()-i-1)) return false;
        }
        return true;
    }


    public boolean palindromeTestNoSpace(String s) {

        int index = s.length()-1;
        char space = ' ';
        int i=-1;
        while ( index > i ) {
            i++;
            if (s.charAt(i) == space) continue;
            while (s.charAt(index)==space) {
                index--;
            }
            if (s.charAt(i) != s.charAt(index)) return false;
            index--;
        }
        return true;
    }



    @Test
    public void testPalindromeReverse(){
        assertEquals(true, palindromeReverseTest(s));
        assertEquals(true, palindromeReverseTest(s2));
        assertEquals(false, palindromeReverseTest(s3));
        assertEquals(true, palindromeReverseTest(s4));
    }


    @Test
    public void testPalindrome(){
        assertEquals(true, palindromeTest(s));
        assertEquals(true, palindromeTest(s2));
        assertEquals(false, palindromeTest(s3));
        assertEquals(true, palindromeTest(s4));
    }


    @Test
    public void testPalindromeNoSpace(){
        assertEquals(true, palindromeTestNoSpace(s));
        assertEquals(true, palindromeTestNoSpace(s2));
        assertEquals(true, palindromeTestNoSpace(s3));
        assertEquals(true, palindromeTestNoSpace(s4));
    }
}
