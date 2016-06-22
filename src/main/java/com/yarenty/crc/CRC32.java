package com.yarenty.crc;

/**
 * Cyclic Redundancy Check (CRC32 or Ethernet / AAL5 or ITU-TSS)
 *
 * Created by yarenty on 22/06/2016.
 * (C)2015 SkyCorp Ltd.
 */
public interface CRC32 {

    String calculate(String input);

}
