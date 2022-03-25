package com.shahzheeb.authen;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GenerateBarCode {

    public static void main(String[] args) throws IOException, WriterException {
        String secretKey = GenerateSecretKey.generateSecretKey();
        System.out.println("secretKey = "+secretKey);
        String email = "test1234@gmail.com";
        String companyName = "ABC-Bank";
        String barCodeUrl = getGoogleAuthenticatorBarCode(secretKey, email, companyName);
        System.out.println("barCodeUrl ="+barCodeUrl);

        //GENERATE QR CODE
        createQRCode(barCodeUrl, "QRCode.png", 400, 400);

        //1. Scan the barcode in google authenticator app
        //2. Use the same secret to generate TOTP and It will start matching with google authenticator.
    }


    public static String getGoogleAuthenticatorBarCode(String secretKey, String account, String issuer) {
        try {
            return "otpauth://totp/"
                    + URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20")
                    + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20")
                    + "&issuer=" + URLEncoder.encode(issuer, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void createQRCode(String barCodeData, String filePath, int height, int width)
            throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE,
                width, height);
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            MatrixToImageWriter.writeToStream(matrix, "png", out);
        }
    }
}
