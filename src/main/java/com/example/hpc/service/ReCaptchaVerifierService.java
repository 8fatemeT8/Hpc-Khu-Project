package com.example.hpc.service;

import com.example.hpc.model.catpcha.CaptchaResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class ReCaptchaVerifierService {

    private String secretKey;
    private RestTemplate restClient;

    private static final String RECAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify";

    public ReCaptchaVerifierService(String googleRecaptchaSecretKey, RestTemplate springRestClient) {
        this.secretKey = googleRecaptchaSecretKey;
        this.restClient = springRestClient;
    }

    public boolean verify(String recaptchaResponse) {
        URI verifyUri = URI
                .create(String.format(RECAPTCHA_URL + "?secret=%s&response=%s&remoteip=%s", this.secretKey, recaptchaResponse,"83.97.23.188"));

        CaptchaResponseDto captchaResponseDto = this.restClient.getForObject(verifyUri, CaptchaResponseDto.class);

        if (captchaResponseDto != null && !captchaResponseDto.isSuccess()) {
            return recaptchaResponse != null;
        }
        return true;
    }
}