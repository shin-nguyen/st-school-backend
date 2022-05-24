package com.stschools.api;

import com.stschools.exception.ApiRequestException;
import com.stschools.exception.EmailException;
import com.stschools.exception.InputFieldException;
import com.stschools.exception.PasswordException;
import com.stschools.payload.common.RegistrationMobileRequest;
import com.stschools.payload.common.RegistrationRequest;
import com.stschools.service.AuthenticationService;
import com.stschools.util.ControllerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private final AuthenticationService authenticationService;
    private final ControllerUtils controllerUtils;

    @PostMapping
    public ResponseEntity<String> registration(@Valid @RequestBody  RegistrationRequest user,
                                                                    BindingResult bindingResult) {
        controllerUtils.captchaValidation(user.getCaptcha());

        if (controllerUtils.isPasswordDifferent(user.getPassword(), user.getPassword2())) {
            throw new PasswordException("Passwords do not match.");
        }
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        }
        if (!authenticationService.registerUser(user)) {
            throw new EmailException("Email is already used.");
        }
        return ResponseEntity.ok("User successfully registered.");
    }

    @PostMapping("/mobile")
    public ResponseEntity<String> registration(@RequestBody RegistrationMobileRequest user) {

        authenticationService.registerUserMobile(user);
        return ResponseEntity.ok("User successfully registered.");
    }


    @GetMapping("/activate/{code}")
    public ResponseEntity<String> activateEmailCode(@PathVariable String code) {
        if (!authenticationService.activateUser(code)) {
            throw new ApiRequestException("Activation code not found.", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok("User successfully activated.");
        }
    }
}
