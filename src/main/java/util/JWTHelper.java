package util;

import com.fasterxml.jackson.databind.util.JSONPObject;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.json.JsonObject;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JWTHelper {
    private static JWTHelper jwTokenHelper = null;
    private static final long EXPIRATION_LIMIT = 30;

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    private Key signingKey;

    private JWTHelper() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Constants.SECRET_SIGNING_KEY_STRING);
        signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }
    public static JWTHelper getInstance() {
        if(jwTokenHelper == null)
            jwTokenHelper = new JWTHelper();
        return jwTokenHelper;
    }

    /***
     * generates a jason web token with the parameters as payload.
     * @param username
     * @param password
     * @return
     */
    public String generatePrivateKey(String username, String password) {
        JsonObject payload = new JSONPObject();

        return Jwts
                .builder().setPayload("userId")
                .setExpiration(calcExpirationDate())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }


    public String claimKey(String privateKey) throws ExpiredJwtException, MalformedJwtException {
        Jwt jwt = Jwts.parser()
                .setSigningKey(signingKey)
                .parse(privateKey);
        return (String) jwt.getBody();
    }

    private Date calcExpirationDate() {
        long currentTimeInMillis = System.currentTimeMillis();
        long expMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT);
        return new Date(currentTimeInMillis + expMilliSeconds);
    }
}
