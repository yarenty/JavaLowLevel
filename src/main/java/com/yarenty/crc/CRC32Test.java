package com.yarenty.crc;

/******************************************************************************
 * Test of  Cyclic Redundancy Check (CRC32 or Ethernet / AAL5 or ITU-TSS).
 * <p/>
 * Uses direct table lookup, calculation, and Java library.
 * <p/>
 * % java CRC32 123456789
 * CRC32 (via table lookup)       = cbf43926
 * CRC32 (via direct calculation) = cbf43926
 * CRC32 (via Java's library)     = cbf43926
 * <p/>
 * <p/>
 * <p/>
 * Uses irreducible polynomial:
 * 1    + x    + x^2  + x^4  + x^5  + x^7  + x^8  +
 * x^10 + x^11 + x^12 + x^16 + x^22 + x^23 + x^26
 * <p/>
 * 0000 0100 1100 0001 0001 1101 1011 0111
 * 0    4    C    1    1    D    B    7
 * <p/>
 * The reverse of this polynomial is
 * <p/>
 * 0    2    3    8    8    B    D    E
 * <p/>
 * <p/>
 * Created by yarenty on 22/06/2016.
 * (C)2015 SkyCorp Ltd.
 */


public class CRC32Test {

    public static void main(String[] args) {

        String input = "fqbux ";
        CRC32 loo = new CRC32Lookup();
        CRC32 nat = new CRC32Direct();
        CRC32 zip = new CRC32Zip();

        System.out.println("LOOKUP:" + loo.calculate(input) + "\nNATIVE:" + nat.calculate(input) + "\n   ZIP:" + zip.calculate(input));

        //timings

        CRC32[] crcs = {loo, nat, zip};
        //CRC32[] crcs = {nat,zip,loo};
        //CRC32[] crcs = {zip,nat,loo};
        Long[] out = new Long[crcs.length];

        //warmup - otherwise first one will be always the longest
        int LOOPS = 1000000;
        for (CRC32 crcAlgorithm : crcs) {
            for (int i = 0; i < LOOPS; i++) {
                crcAlgorithm.calculate(input);
            }
        }

        int n = 0;
        for (CRC32 crcAlgorithm : crcs) {

            Long start = System.currentTimeMillis();
            for (int i = 0; i < LOOPS; i++) {
                crcAlgorithm.calculate(input);
            }
            Long stop = System.currentTimeMillis();
            out[n] = stop - start;
            n++;
        }

        System.out.println("Timings:\nLOOKUP:" + out[0] + "ms\nNATIVE:" + out[1] + "ms\n   ZIP:" + out[2] + "ms");

    }

}
