package util.security;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.Stateless;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

@Stateless
public class HashUtilMD5 implements HashUtil {

    private static final Random RANDOM = new SecureRandom();

    @Override
    public String generateSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return new String(salt);
    }

    @Override
    public String hashString(String salt, String toHash) {
        String appended = salt + toHash;
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return new String(messageDigest.digest(appended.getBytes()));
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return "";
        }

    }


}
