package com.ifsp.api.queroservoluntario.rest.controllers;

import com.ifsp.api.queroservoluntario.rest.model.RecoveryPasswordModel;
import com.ifsp.api.queroservoluntario.rest.model.UserModel;
import com.ifsp.api.queroservoluntario.rest.model.UserPasswordModel;
import com.ifsp.api.queroservoluntario.service.EmailService;
import com.ifsp.api.queroservoluntario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/recovery", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> recoveryPassword(@RequestBody @Valid RecoveryPasswordModel recovery) {
        emailService.sendEmailRecovery(recovery.getEmail());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody @Valid UserModel userModel) {
        UserModel user = userService.save(userModel);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> getById(@PathVariable("userId") Long id) {
        UserModel user = userService.findById(id);
        if(userService == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/fill", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> getFillUserById(@PathVariable("userId") Long id) {
        UserModel user = userService.findFillById(id);
        if(userService == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> updateUser(@PathVariable("userId") Long id, @RequestBody @Valid UserModel userModel) {
        userService.update(id, userModel);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/change-password/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> pactPassword(@PathVariable("userId") Long id, @RequestBody @Valid UserPasswordModel userPasswordModel) {
        userService.updatePassword(id, userPasswordModel);
        return new ResponseEntity(HttpStatus.OK);
    }

}
