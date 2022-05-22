package com.ifsp.api.queroservoluntario.rest.controllers;

import com.ifsp.api.queroservoluntario.dto.LoginDTO;
import com.ifsp.api.queroservoluntario.dto.TokenDTO;
import com.ifsp.api.queroservoluntario.entity.UserEntity;
import com.ifsp.api.queroservoluntario.service.UserService;
import com.ifsp.api.queroservoluntario.service.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Base64;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid LoginDTO loginDTO) throws AuthenticationException {
        String password = "";
        try {
            byte[] decodedBytes = Base64.getUrlDecoder().decode(loginDTO.getPass());
            password = new String(decodedBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token;
        try {
            token = tokenService.authentication(loginDTO.getUser(), password);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserEntity user = userService.findByEmailActive(loginDTO.getUser()).get();
        return ResponseEntity.ok(new TokenDTO.Builder()
                                .id(user.getId())
                                .name(user.getName())
                                .document(user.getDocument())
                                .type(user.getProfile().getType())
                                .token("Bearer " + token).build()
        );
    }
}
