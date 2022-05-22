package com.ifsp.api.queroservoluntario.rest.controllers;

import com.ifsp.api.queroservoluntario.rest.model.PaginationModel;
import com.ifsp.api.queroservoluntario.rest.model.SubscriptionJobModel;
import com.ifsp.api.queroservoluntario.rest.model.SubscriptionModel;
import com.ifsp.api.queroservoluntario.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createSubscription(@RequestBody @Valid SubscriptionModel subscriptionModel) {
        subscriptionService.save(subscriptionModel);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaginationModel> getByUserId(@PathVariable("userId") Long id, @RequestParam("page") int page, @RequestParam("size") int size) {
        PaginationModel subscriptions = subscriptionService.findAllByUserId(id, page, size);
        return new ResponseEntity(subscriptions, HttpStatus.OK);
    }

    @GetMapping(value = "/company/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaginationModel> getByCompanyId(@PathVariable("companyId") Long id, @RequestParam("page") int page, @RequestParam("size") int size) {
        PaginationModel subscriptions = subscriptionService.findAllByCompanyId(id, page, size);
        return new ResponseEntity(subscriptions, HttpStatus.OK);
    }

    @PatchMapping(value = "/cancel/{subscriptionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cancelSubscription(@PathVariable("subscriptionId") Long subscriptionId) {
        subscriptionService.cancelSubscription(subscriptionId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
