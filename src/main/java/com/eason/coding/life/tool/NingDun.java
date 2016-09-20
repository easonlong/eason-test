package com.eason.coding.life.tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class NingDun {

    private static final int[] DIGITS_POWER;

    public static void main(String[] args) throws DecoderException, IOException {
        long l = new Date().getTime() / 1000L;
        String token = args[0];
        final byte[] jsonByte = Base64.decodeBase64(token);
        Map m = new ObjectMapper().reader().forType(Map.class).readValue(jsonByte);
        String seed = (String) ((Map) m.get("token")).get("seed");
        final String s = NingDun.generatePassword(Hex.decodeHex(seed.toCharArray()), l, 60, 0L, 6);
        System.out.println(s);
    }

    public static byte[] hmac_sha(String crypto, byte[] keyBytes, byte[] text) {
        try {
            Mac hmac = Mac.getInstance(crypto);
            hmac.init(new SecretKeySpec(keyBytes, "RAW"));
            return hmac.doFinal(text);
        } catch (GeneralSecurityException gse) {
            throw new UndeclaredThrowableException(gse);
        }
    }

    private static byte[] hexStr2Bytes(String hex) {
        byte[] bArray = new BigInteger("10" + hex, 16).toByteArray();
        byte[] ret = new byte[(bArray.length - 1)];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = bArray[i + 1];
        }
        return ret;
    }

    static {
        DIGITS_POWER = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
    }

    public static String generateTOTP(byte[] key, Calendar time, int timeStep, long T0, int returnDigits) {
        return generateTOTP(key, time, timeStep, T0, returnDigits, "HmacSHA1");
    }

    public static String generatePassword(byte[] key, long factor, int timeStep, long T0, int returnDigits) {
        return generatePassword(key, factor, timeStep, T0, returnDigits, "HmacSHA1");
    }

    public static String generateTOTP256(byte[] key, Calendar time, int timeStep, long T0, int returnDigits) {
        return generateTOTP(key, time, timeStep, T0, returnDigits, "HmacSHA256");
    }

    public static String generateTOTP512(byte[] key, Calendar time, int timeStep, long T0, int returnDigits) {
        return generateTOTP(key, time, timeStep, T0, returnDigits, "HmacSHA512");
    }

    public static String generateTOTP(byte[] key, Calendar time, int timeStep, long T0, int returnDigits, String crypto) {
        return generatePassword(key, time.getTimeInMillis() / 1000, timeStep, T0, returnDigits, crypto);
    }

    public static String generatePassword(byte[] key, long factor, int timeStep, long T0, int returnDigits, String crypto) {
        long T = (factor - T0) / ((long) timeStep);
        byte[] msg = new byte[8];
        for (int i = msg.length - 1; i >= 0; i--) {
            msg[i] = (byte) ((int) (255 & T));
            T >>= 8;
        }
        byte[] hash = hmac_sha(crypto, key, msg);
        int offset = hash[hash.length - 1] & 15;
        String result = Integer.toString((((((hash[offset] & 0x7F) << 24) | ((hash[offset + 1] & 0xFF
        ) << 16)) | ((hash[offset + 2] & 0xFF) << 8)) | (hash[offset + 3] & 0xFF)) %
                DIGITS_POWER[returnDigits]);
        while (result.length() < returnDigits) {
            result = "0" + result;
        }
        return result;
    }
}