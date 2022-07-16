package ru.spbstu.edu.asteroids.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestKeyVerificationService {

    private final RequestKeyConfig config;

    @Autowired
    public RequestKeyVerificationService(RequestKeyConfig config) {
        this.config = config;
    }
}
