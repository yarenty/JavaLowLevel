package com.yarenty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class Writer {

    private static final String MESSAGE = "Test message:\n"
            + "Writing to a file in NIO is similar to reading from one.\n "
            + "We start by getting a channel from a FileOutputStream:\n"
            + "Our next step is to create a buffer and put some data in it -"
            + "- in this case, the data will be taken from an array called "
            + "message which contains the ASCII bytes for the string "
            + "Some bytes. \n"
            + "Our final step is to write to the buffer:\n"
            + "fc.write( buffer );\n"
            + "Notice that once again we did not need to tell the channel "
            + "how much data we wanted to"
            + "write.\n The buffer's internal accounting";


    public static void main(final String[] args) {

        try {
            createFile("test.txt");

            System.out.println("OUTPUT::");

            readFile("test.txt");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    /**
     * @throws IOException
     */
    public static void createFile(final String fileName) throws IOException {

        System.out.println("start");
        final FileOutputStream fout = new FileOutputStream(fileName);
        System.out.println("file created");

        final FileChannel fc = fout.getChannel();
        System.out.println("chanell is here");

        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("and buffer");

        for (int i = 0; i < MESSAGE.length(); ++i) {
            buffer.put(MESSAGE.getBytes()[i]);
        }

        System.out.println("byte by byte:" + buffer.position());


        buffer.flip();
        System.out.println("flip");

        fc.write(buffer);

        fc.close();
        fout.close();

        System.out.println("all closed");
    }


    /**
     * @throws IOException
     */
    public static void readFile(final String fileName) throws IOException {
        final FileInputStream fin = new FileInputStream(fileName);
        final FileChannel fc = fin.getChannel();

        final ByteBuffer buffer = ByteBuffer.allocate(1024);

        fc.read(buffer);

        System.out.println(BufferInfo.getStats(buffer));

//		StringBuffer sb = new StringBuffer(1024);
//		for (int i=0; i <buffer.position(); i++) {
//			sb.append((char)buffer.get(i));
//		}
//		System.out.println(sb);


        final Charset latin1 = Charset.forName("ISO-8859-1");
        final CharsetDecoder decoder = latin1.newDecoder();
        final CharBuffer cb = decoder.decode(buffer);


        System.out.println("xxxxxxxxxxxxxxx:" + cb.limit());
        System.out.println(cb.toString());


        fc.close();
        fin.close();
    }


}
