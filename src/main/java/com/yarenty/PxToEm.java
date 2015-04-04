package com.yarenty;

/**
 * This is just generating table with px to em conversions.
 *
 * Created by yarenty on 04/02/2015.
 */
public class PxToEm {

    public static void main(String[] args) {

        System.out.println("<table border='1'><tr><th style='width:5em'>px</th><th style='width:8em'>em</th></tr>");
        int i=0;
        for (i = 1; i <= 40; i++) {

            print(i);
        }

        print(50);

        print(80);
        print(100);
        print(120);
        print(640);
        print(920);
        print(960);
        System.out.println("</table>");

    }

    private static void print(int i){
        System.out.println("<tr><td width='5em' align='right'> " + i + " px </td><td align='right'> " + getEm(i) + " </td></tr>");
    }

    private static float getEm(int i) {
        return (float) i / 16;
    }


}
