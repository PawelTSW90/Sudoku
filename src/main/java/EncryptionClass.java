import javax.crypto.Cipher;

import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import java.util.Arrays;
import java.util.Base64;

public class EncryptionClass {
    private static SecretKeySpec secretKey;



    public static String encrypt(String password, String input) {
        byte[] key;
        Cipher cipher;
        try {
            key = password.getBytes("UTF-8");
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(input.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String password, String input)
    {
        byte[] key;
        try
        {
            key = password.getBytes("UTF-8");
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(input)));

        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }

        return null;
    }
}
