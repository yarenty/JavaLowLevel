package com.yarenty.crc;

/**
 * Using Java's java.util.zip.CRC32 library
 * <p/>
 * Created by yarenty on 22/06/2016.
 * (C)2015 SkyCorp Ltd.
 */
public class CRC32Zip implements CRC32 {

    public String calculate(String input) {

        byte[] bytes = input.getBytes();
        java.util.zip.CRC32 x = new java.util.zip.CRC32();
        x.update(bytes);
        return Long.toHexString(x.getValue());
    }
}
