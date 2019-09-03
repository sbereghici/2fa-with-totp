package main;

import main.utils.HMACSHA1;
import main.utils.Misc;
import org.apache.commons.codec.binary.Base32;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Authenticator {

    static String getOneTimePassword(String secretKey, int period, int digits) throws InvalidKeyException, NoSuchAlgorithmException {
        byte[] key = new Base32().decode(secretKey);
        long unixTimestamp = System.currentTimeMillis() / 1000L;
        byte[] timeFactor = Misc.longToByteArray(Misc.trunc(unixTimestamp / period));
        byte[] hash = HMACSHA1.hash(key, timeFactor);
        int truncatedHash = getTruncatedHash(hash);
        int code = truncatedHash % ((int) Math.pow(10, digits));
        StringBuilder result = new StringBuilder(String.valueOf(code));
        while (result.length() < digits) {
            result.insert(0, "0");
        }
        return result.toString();
    }

    private static int getTruncatedHash(byte[] hash) {
        int offset = hash[hash.length - 1] & 0xf;
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        for (int i = 0; i < 4; ++i) {
            byteBuffer.put(i, hash[offset + i]);
        }
        int trunc = byteBuffer.getInt() & 0x7fffffff;
        return trunc;
    }

}
