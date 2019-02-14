package util.security;

public interface HashUtil {
    String generateSalt();
    String hashString(String salt, String toHash);
}
