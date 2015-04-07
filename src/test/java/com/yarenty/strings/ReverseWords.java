package com.yarenty.strings;

import org.junit.Test;

/**
 * OUTPUT:<pre>
 Ala ma kote i psa Bonifacego
 Bonifacego psa i kote ma Ala
 </pre>
 * @author yarenty
 *
 */
public class ReverseWords {


    final String in = "Ala ma kote i psa Bonifacego";

    String reverse(String in){
        String out[] = in.split(" ");

        for (String s : out) {
            System.out.println(s);
        }

        StringBuilder sb = new StringBuilder(in.length());

        for (int i=out.length-1; i>=0; --i){
            sb.append(out[i]).append(" ");
            System.out.println(out[i]);
        }

        return sb.substring(0,sb.length()-1).toString(); // it's quicker
    }


    @Test
    public void testReverseString() {
        System.out.println(in);
        System.out.println(reverse(in));
    }

}
