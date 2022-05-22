package com.ifsp.api.queroservoluntario.rest.controllers;

import com.ifsp.api.queroservoluntario.rest.model.PaginationModel;
import com.ifsp.api.queroservoluntario.rest.model.UserApproveModel;
import com.ifsp.api.queroservoluntario.rest.model.UserModel;
import com.ifsp.api.queroservoluntario.service.CompanyService;
import com.ifsp.api.queroservoluntario.service.IndividualService;
import com.ifsp.api.queroservoluntario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private IndividualService individualService;

    @Autowired
    private UserService userService;

    public static final String SELF_COMPANY_URL = "/company";
    public static final String SELF_INDIVIDUAL_URL = "/individual";

    @RequestMapping(value = SELF_COMPANY_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaginationModel> findAllCompanies(@RequestParam("page") int page, @RequestParam("size") int size) {
        return new ResponseEntity(companyService.findAll(page, size), HttpStatus.OK);
    }

    @RequestMapping(value = SELF_INDIVIDUAL_URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaginationModel> findAllIndividuals(@RequestParam("page") int page, @RequestParam("size") int size) {
        return new ResponseEntity(individualService.findAll(page, size), HttpStatus.OK);
    }

    @PatchMapping(value = "/active/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserModel> active(@PathVariable("userId") Long userId, @RequestBody @Valid UserApproveModel model) {
        userService.approve(userId, model);
        return new ResponseEntity(HttpStatus.OK);
    }

}
