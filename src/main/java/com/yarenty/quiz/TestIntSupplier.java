package com.yarenty.quiz;

/**
 * Created by yarenty on 29/09/2016.
 * (C)2015 SkyCorp Ltd.
 */
public class TestIntSupplier {


    public IntSupplier doStuff(int [] vals,   int i) {
        //line 1

        //vals = Arrays.copyOf(vals, vals.length);
        if (vals[0] < 0 ) vals[i] = 0;

        return () -> vals[i];
    }


    public static void main (String[] args) {

        int[] a = {-1,2,3,4};

        TestIntSupplier tst = new TestIntSupplier();

        IntSupplier i =  tst.doStuff(a,2);

        System.out.println(i.doit());

    }

}
