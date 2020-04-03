package encrypt.DataSource;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

/*
 * Author:fcy
 * Date:2020/3/28 3:27
 * tomcat加密数据库密码
 * 将该三个文件打成jar包并放入tomcat的lib目录下
 * 并在context.xml文件中填写自己的DataSource工厂类
 */
public class encryptPassword implements encrypt{
    private static SecretKey secretKey=getSecretEncryptionKey();
    private  static SecretKey getSecretEncryptionKey() {
        KeyGenerator generator = null;
        try {
            generator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        generator.init(128,new SecureRandom(encryptPassword.class.getSimpleName().getBytes()));
        SecretKey secKey = generator.generateKey();

        return secKey;
    }
    public  String encryptPassword(String plainText) throws Exception{
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(byteCipherText);
    }
    public  String decryptPassword(String byteCipherText) throws Exception {
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] bytePlainText = aesCipher.doFinal(Base64.getDecoder().decode(byteCipherText));
        return new String(bytePlainText);
    }

    public static void main(String[] args) throws Exception {
        encryptPassword encryptPassword = new encryptPassword();
        String s = encryptPassword.encryptPassword("838502");
        System.out.println(s);
        String s1 = encryptPassword.decryptPassword(s);
        System.out.println(s1);
        System.out.println(new Date().getYear()+1900);
        String[] array=new String[]{"1","2","3"};
        System.out.println(Arrays.toString(array));
        String str="birthday|#||#|123";
        for (String s2 : str.split("\\|#\\|")) {
            System.out.println("D"+s2);
        }
    }
}
