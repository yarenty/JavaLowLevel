package com.yarenty.math;


/**
 * OUTPUT:
 * <pre>
 *
 * </pre>
 *
 * @author yarenty
 */
public class PrimeNumber {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        final long t1 = System.currentTimeMillis();
        int primes = 0;
        boolean prime = true;
        for (int i = 4; i < 100000; i++) {
            prime = true;


            for (int j = 2; j * 2 < i; j++) {

                if (i % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                primes++; //System.out.println(i+" is prime");
                i++;
            }

        }
        System.out.println("TIME: " + (System.currentTimeMillis() - t1) + " primes:" + primes);

    }

}
