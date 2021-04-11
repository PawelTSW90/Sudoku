package pawelDyjak.sudoku;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Base64;

public class EncryptionClass {
    private static SecretKeySpec secretKey;


    //method is encrypting given string by using password
    public String encrypt(String password, String input) {
        byte[] key;
        Cipher cipher;
        try {
            key = password.getBytes(StandardCharsets.UTF_8);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(input.getBytes(StandardCharsets.UTF_8)));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    //method is decrypting given string if correct password is passed
    public String decrypt(String password, String input) {
        byte[] key;
        try {
            key = password.getBytes(StandardCharsets.UTF_8);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(input)));

        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }

        return null;
    }
}
