package com.shahzheeb.authen;

import de.taimos.totp.TOTP;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

public class GenerateTOTP {

    public static void main(String[] args) {
        String secretKey = "U5UP5YA7JN2IUHAKXHM2IZNXV3G7F54K";
        String TOTP = getTOTPCode(secretKey);
        System.out.println("TOPT ="+TOTP);

    }

    public static String getTOTPCode(String secretKey) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }
}
