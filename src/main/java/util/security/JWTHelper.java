package util.security;

import com.google.gson.Gson;
import io.jsonwebtoken.*;
import util.Constants;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Stateless
public class JWTHelper {
    private static JWTHelper jwTokenHelper = null;
    private Key signingKey;
    Gson gson;

    private JWTHelper() {
        new Gson();
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Constants.SECRET_SIGNING_KEY_STRING);
        signingKey = new SecretKeySpec(apiKeySecretBytes, Constants.SIGNATURE_ALGORITHM.getJcaName());
    }
    public static JWTHelper getInstance() {
        if(jwTokenHelper == null)
            jwTokenHelper = new JWTHelper();
        return jwTokenHelper;
    }

    /***
     * generates a jason web token with the parameters as payload.
     * @param payload the account to put in the payload
     * @return
     */
    public String generatePrivateKey(Payload payload) {

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("payload", payload);
        //should not put json in payload, maybe change later
        return Jwts
                .builder()
                .addClaims(claimsMap)
                .setExpiration(calcExpirationDate())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(Constants.SIGNATURE_ALGORITHM, signingKey)
                .compact();
    }

    /***
     * gets the string from the JWT body, if valid.
     * @param jwsString the JWS
     * @return a payload associated with the account
     * @throws ExpiredJwtException
     * @throws MalformedJwtException
     */
    public HashMap claimKey(String jwsString) throws ExpiredJwtException, MalformedJwtException {
        Jwt jwt = Jwts.parser()
                .setSigningKey(signingKey)
                .parse(jwsString);
        Claims claims = (Claims) jwt.getBody();
        return  (HashMap) claims.get("payload");
    }

    private Date calcExpirationDate() {
        long currentTimeInMillis = System.currentTimeMillis();
        long expMilliSeconds = TimeUnit.MINUTES.toMillis(Constants.EXPIRATION_LIMIT);
        return new Date(currentTimeInMillis + expMilliSeconds);
    }
}
