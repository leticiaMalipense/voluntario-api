package com.ifsp.api.queroservoluntario.rest.controllers;

import com.ifsp.api.queroservoluntario.dto.AddressDTO;
import com.ifsp.api.queroservoluntario.dto.AddressViaCepDTO;
import com.ifsp.api.queroservoluntario.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;


    @GetMapping(value = "/cep/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> getByCep(@PathVariable("cep") String cep){

        AddressDTO address = addressService.getByCep(cep);
        if(address == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(address, HttpStatus.OK);
    }
}
