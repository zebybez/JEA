package util;

import io.jsonwebtoken.SignatureAlgorithm;

public class Constants {
    public static final String SECRET_SIGNING_KEY_STRING = "jAsonWEbToKEnIsZeErKUT";
    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    public static final long EXPIRATION_LIMIT = 30;
}
