package util.security;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ejb.Stateless;

@Stateless
public class HashUtilMD5 implements HashUtil {
    @Override
    public String generateSalt() {
        throw new NotImplementedException();
    }

    @Override
    public String hashString(String salt, String toHash) {
        throw new NotImplementedException();
    }
}
