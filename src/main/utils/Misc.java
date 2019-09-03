package main.utils;

import java.nio.ByteBuffer;

public class Misc {

    public static long trunc(double d) {
        return (long) Math.floor(d * 100) / 100;
    }

    public static byte[] longToByteArray(long i) {
        final ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(i);
        return buffer.array();
    }
}
