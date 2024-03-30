package com.labformjava.labformjava.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labformjava.labformjava.dto.RecaptchaDto;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/recaptcha")
public class RecaptchaController {
    private static final String RECAPTCHA_SECRET_KEY = "6LfLkJUpAAAAACH38olngW2b1rTUpA15QYMFjcn7";

    public RecaptchaController() {

    }

    @GetMapping
    public ResponseEntity<?> verifyRecaptcha(@RequestHeader("token") String recaptchaResponse) {
        String url = "https://www.google.com/recaptcha/api/siteverify";
        String params = "?secret=" + RECAPTCHA_SECRET_KEY + "&response=" + recaptchaResponse;
        System.out.println(recaptchaResponse);
        //Send POST to Google reCAPTCHA API
        ResponseEntity<String> response = new RestTemplate().postForEntity(url + params, null, String.class);
        System.out.println(response);

        Gson gson = new Gson();
        RecaptchaDto recaptchaDto = gson.fromJson(response.getBody(), RecaptchaDto.class);

        //Check status response
        if(recaptchaDto.isSuccess()) {
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.badRequest().body("reCAPTCHA verification failed");
        }
    }
}
