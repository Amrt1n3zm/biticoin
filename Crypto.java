package info.osom.sandbox;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Crypto {
    private static final String KEY = "12345678901234567890123456789012";
    private static final String IV = "1234567890123456";
    private static final String ENGINE = "AES";
    private static final String CRYPTO = "AES/CBC/PKCS5Padding";
    private static final int BUFFER_SIZE = 4 * 1024;

    public static void encrypt(InputStream fis, OutputStream fos)
            throws NoSuchPaddingException, BadPaddingException,
            InvalidKeyException, NoSuchAlgorithmException,
            IOException, ShortBufferException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException {
        execute(Cipher.ENCRYPT_MODE, fis, fos);
    }

    public static void decrypt(InputStream fis, OutputStream fos)
            throws NoSuchPaddingException, BadPaddingException,
            InvalidKeyException, NoSuchAlgorithmException,
            IOException, ShortBufferException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException {
        execute(Cipher.DECRYPT_MODE, fis, fos);
    }

    private static void execute(int mode, InputStream fis, OutputStream fos)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException,
            InvalidKeyException, IOException, ShortBufferException,
            BadPaddingException, IllegalBlockSizeException
    {
        SecretKeySpec sks = new SecretKeySpec(KEY.getBytes(), ENGINE);
        IvParameterSpec ivps = new IvParameterSpec(IV.getBytes());

        Cipher cipher = Cipher.getInstance(CRYPTO);
        cipher.init(mode, sks, ivps);

        byte[] inBuffer = new byte[BUFFER_SIZE];
        byte[] outBuffer = new byte[BUFFER_SIZE];
        int inBytes;
        int outBytes;

        while ((inBytes = fis.read(inBuffer)) != -1) {
            outBytes = cipher.update(inBuffer, 0, inBytes, outBuffer);
            fos.write(outBuffer, 0, outBytes);
        }

        outBytes = cipher.doFinal(outBuffer, 0);
        fos.write(outBuffer, 0, outBytes);
        fos.flush();
    }
}