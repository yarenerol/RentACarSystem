package Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

    private PasswordUtil() {
    }

    public static String hash(String password) {       //Burada şifremizi SHA-256 algoritmasıyla şifreleyeceğiz.

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return password;

    }

    private static String bytesToHex(byte[] encodedHash) {
        StringBuilder builder = new StringBuilder(2 * encodedHash.length);

        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                builder.append('0');

            }
            builder.append(hex);
        }
        return builder.toString();

    }
}
