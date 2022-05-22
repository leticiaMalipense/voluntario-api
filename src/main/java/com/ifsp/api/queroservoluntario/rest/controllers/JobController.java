package com.ifsp.api.queroservoluntario.rest.controllers;

import com.ifsp.api.queroservoluntario.rest.model.JobModel;
import com.ifsp.api.queroservoluntario.rest.model.PaginationModel;
import com.ifsp.api.queroservoluntario.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody @Valid JobModel jobModel){
        jobService.save(jobModel);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{jobId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobModel> getById(@PathVariable("jobId") Long id){
        JobModel job = jobService.findById(id);
        return new ResponseEntity(job, HttpStatus.OK);
    }

    @GetMapping(value = "/company/{companyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaginationModel> getByCompanyId(@PathVariable("companyId") Long companyId,
                                                         @RequestParam("page") int page, @RequestParam("size") int size) {
        PaginationModel jobList = jobService.findAllByCompanyId(companyId, page, size);
        return new ResponseEntity(jobList, HttpStatus.OK);
    }

    @GetMapping(value = "/filter;{matrix}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaginationModel> getByFilters(@MatrixVariable("state") Long state,
                                                        @MatrixVariable(value = "city", required = false) String city,
                                                        @RequestParam("page") int page, @RequestParam("size") int size) {
        PaginationModel jobList = jobService.findByFilter(state, city, page, size);
        return new ResponseEntity(jobList, HttpStatus.OK);
    }

    @PutMapping(value = "/{jobId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateJob(@PathVariable("jobId") Long id, @RequestBody @Valid JobModel jobModel){
        jobService.update(id, jobModel);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{jobId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteJob(@PathVariable("jobId") Long id){
        jobService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
