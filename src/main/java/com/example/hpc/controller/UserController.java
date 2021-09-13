package com.example.hpc.controller;

import com.example.hpc.controller.bases.ControllerBase;
import com.example.hpc.model.domain.UserDomain;
import com.example.hpc.model.dto.UserDto;
import com.example.hpc.model.entity.User;
import com.example.hpc.model.repository.UserRepository;
import com.example.hpc.service.ReCaptchaVerifierService;
import com.example.hpc.service.UserService;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.example.hpc.utils.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController /*extends ControllerBase<User, UserDto, UserDomain, UserRepository, UserMapper, UserService>*/ {

    private ReCaptchaVerifierService reCaptchaVerifierService;

    public UserController(UserService serviceBase, ReCaptchaVerifierService reCaptchaVerifierService) {
//        super(serviceBase);
        this.reCaptchaVerifierService = reCaptchaVerifierService;
    }

    @PostMapping(value = "/recaptcha")
    public ResponseEntity<?> signup(@RequestBody UserDto userDTO, @RequestParam(name = "g-recaptcha-response") String recaptchaResponse) {
        boolean verified = reCaptchaVerifierService.verify(recaptchaResponse);
        if (!verified) {
            throw new ExceptionHandler("RECAPTCHA_VERIFICATION_ERROR", HttpStatus.NOT_ACCEPTABLE.value());
        }
        return ResponseEntity.ok().build();
    }
}
