package com.yarenty.crc;

/**
 * Using direct calculation
 * Created by yarenty on 22/06/2016.
 * (C)2015 SkyCorp Ltd.
 */
public class CRC32Direct implements CRC32{

    public String calculate(String input) {

        byte[] bytes = input.getBytes();
        int crc = 0xFFFFFFFF;       // initial contents of LFBSR
        int poly = 0xEDB88320;   // reverse polynomial

        for (byte b : bytes) {
            int temp = (crc ^ b) & 0xff;

            // read 8 bits one at a time
            for (int i = 0; i < 8; i++) {
                if ((temp & 1) == 1) temp = (temp >>> 1) ^ poly;
                else temp = (temp >>> 1);
            }
            crc = (crc >>> 8) ^ temp;
        }

        // flip bits
        crc = crc ^ 0xffffffff;

       return Integer.toHexString(crc);

    }
}
