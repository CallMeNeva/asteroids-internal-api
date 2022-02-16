package io.github.callmeneva.asteroids;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RequestKeyVerifier {

    @Value("${request-key-salt}")
    private String salt;

    public boolean verify(String key, String token) {
        String saltedToken = (token + salt);
        String saltedTokenDigest = DigestUtils.md5Hex(saltedToken);
        return StringUtils.equals(key, saltedTokenDigest);
    }

    public boolean verify(String key, String... tokenParts) {
        return verify(key, String.join("", tokenParts));
    }
}
