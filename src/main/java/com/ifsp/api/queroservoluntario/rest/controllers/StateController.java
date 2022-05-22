package com.ifsp.api.queroservoluntario.rest.controllers;

import com.ifsp.api.queroservoluntario.entity.StateEntity;
import com.ifsp.api.queroservoluntario.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StateEntity>> getState(){
        List<StateEntity> states = stateService.findAll();
        return new ResponseEntity(states, HttpStatus.OK);
    }
}
