package ru.spbstu.edu.asteroids;

import lombok.extern.java.Log;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Stream;

@Log
@Component
public class RequestKeyVerifier {

    @Value("${request-key-salt}")
    private String salt;
    @Value("${request-key-delimiter}")
    private String delimiter;

    public boolean verify(String key, Object... tokenFragments) {
        StringJoiner tokenFragmentsJoiner = new StringJoiner(delimiter);

        Stream.of(tokenFragments)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .forEach(tokenFragmentsJoiner::add);

        if (!salt.isEmpty()) {
            tokenFragmentsJoiner.add(salt);
        }

        String token = tokenFragmentsJoiner.toString();
        String tokenDigest = DigestUtils.md5Hex(token);
        boolean keysMatch = Objects.equals(key, tokenDigest);

       String message = String.format("Client key: [%s], server key: [%s], token: [%s], match: %b", key, tokenDigest, token, keysMatch);

        if (keysMatch) {
            log.info(message);
        } else {
            log.warning(message);
        }

        return keysMatch;
    }
}
