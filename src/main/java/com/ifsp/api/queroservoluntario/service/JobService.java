package com.ifsp.api.queroservoluntario.service;

import com.ifsp.api.queroservoluntario.converters.JobConverter;
import com.ifsp.api.queroservoluntario.entity.CompanyEntity;
import com.ifsp.api.queroservoluntario.entity.JobEntity;
import com.ifsp.api.queroservoluntario.infra.exceptions.BadRequestException;
import com.ifsp.api.queroservoluntario.infra.exceptions.NotFoundException;
import com.ifsp.api.queroservoluntario.repository.CompanyRepository;
import com.ifsp.api.queroservoluntario.repository.JobRepository;
import com.ifsp.api.queroservoluntario.rest.model.JobModel;
import com.ifsp.api.queroservoluntario.rest.model.PaginationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobConverter jobConverter;

    @Transactional
    public void save(JobModel jobModel) {
        Optional<CompanyEntity> companyEntity = companyRepository.findById(jobModel.getCompanyId());
        if(!companyEntity.isPresent()) {
            throw new BadRequestException("Instituicao nao encontrada");
        }

        JobEntity jobEntity = jobConverter.toEntity(jobModel, companyEntity.get(), true);
        jobRepository.save(jobEntity);
    }

    public JobModel findById(Long id) {
        Optional<JobEntity> job = jobRepository.findById(id);
        if(!job.isPresent()) {
            throw new NotFoundException();
        }
        return jobConverter.toModel(job.get());
    }

    public PaginationModel findAllByCompanyId(Long companyId, int page, int size) {
        Page<JobEntity> jobs = jobRepository.findAllByCompanyId(companyId, PageRequest.of(page, size));

        if(jobs.isEmpty()) {
            throw new NotFoundException();
        }

        List<JobModel> jobList = jobs.stream().map(j -> jobConverter.toModel(j)).collect(Collectors.toList());

        PaginationModel model = new PaginationModel();
        model.setTotal(jobs.getTotalElements());
        model.setItems(jobList);

        return model;
    }

    public PaginationModel findByFilter(Long stateId, String city, int page, int size) {
        Page<JobEntity> jobs;

        if(city != null && !city.isEmpty()) {
            jobs = jobRepository.findByFilter(stateId, jobConverter.removerAcentos(city).trim(), PageRequest.of(page, size));
        } else {
            jobs = jobRepository.findByFilter(stateId, PageRequest.of(page, size));
        }

        if(jobs.isEmpty()) {
            throw new NotFoundException();
        }

        List<JobModel> jobModelList = jobs.stream().map(j -> jobConverter.toModel(j)).collect(Collectors.toList());

        PaginationModel model = new PaginationModel();
        model.setTotal(jobs.getTotalElements());
        model.setItems(jobModelList);
        return model;
    }

    @Transactional
    public void update(Long jobId, JobModel jobModel) {

        Optional<JobEntity> job = jobRepository.findByIdWithCompany(jobId);
        if(!job.isPresent()) {
            throw new NotFoundException("Vaga nao encontrada");
        }

        JobEntity jobUpdated = jobConverter.toEntityUpdate(job.get(), jobModel);
        jobRepository.save(jobUpdated);
    }

    @Transactional
    public void delete(Long jobId) {
        Optional<JobEntity> job = jobRepository.findById(jobId);
        if(!job.isPresent()) {
            throw new NotFoundException("Vaga nao encontrada");
        }
        job.get().setActive(false);
        jobRepository.save(job.get());
    }
}
