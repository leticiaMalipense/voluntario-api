package com.ifsp.api.queroservoluntario.service;

import com.ifsp.api.queroservoluntario.converters.SubscriptionConverter;
import com.ifsp.api.queroservoluntario.entity.JobEntity;
import com.ifsp.api.queroservoluntario.entity.SubscriptionEntity;
import com.ifsp.api.queroservoluntario.entity.UserEntity;
import com.ifsp.api.queroservoluntario.infra.exceptions.BadRequestException;
import com.ifsp.api.queroservoluntario.infra.exceptions.ConflictException;
import com.ifsp.api.queroservoluntario.infra.exceptions.NotFoundException;
import com.ifsp.api.queroservoluntario.repository.JobRepository;
import com.ifsp.api.queroservoluntario.repository.SubscriptionRepository;
import com.ifsp.api.queroservoluntario.repository.UserRepository;
import com.ifsp.api.queroservoluntario.rest.model.PaginationModel;
import com.ifsp.api.queroservoluntario.rest.model.SubscriptionJobModel;
import com.ifsp.api.queroservoluntario.rest.model.SubscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionConverter subscriptionConverter;

    public void save(SubscriptionModel model) {
        Optional<SubscriptionEntity> subs = subscriptionRepository.findByIndividualIdAndJobId(model.getIndividualId(), model.getJobId());

        if(subs.isPresent()) {
            throw new ConflictException();
        } else {
            Optional<JobEntity> job = jobRepository.findById(model.getJobId());
            Optional<UserEntity> user = userRepository.findById(model.getIndividualId());
            if(!job.isPresent()) {
                throw new BadRequestException("Vaga nao encontrada");
            }
            SubscriptionEntity entity = subscriptionConverter.toEntity(model, job.get(), user.get(), Boolean.TRUE);
            subscriptionRepository.save(entity);
        }
    }

    public PaginationModel findAllByUserId(Long id, int page, int size) {
        Page<SubscriptionEntity> subs = subscriptionRepository.findAllByIndividualId(id, PageRequest.of(page, size));

        if(subs.isEmpty()) {
            throw new NotFoundException();
        }

        List<SubscriptionModel> subsList = subs.stream().map(j -> subscriptionConverter.toModel(j)).collect(Collectors.toList());

        PaginationModel model = new PaginationModel();
        model.setTotal(subs.getTotalElements());
        model.setItems(subsList);
        return model;
    }

    public PaginationModel findAllByCompanyId(Long id, int page, int size) {
        List<SubscriptionJobModel> list = new ArrayList<>();
        Page<JobEntity> jobs = subscriptionRepository.findJobWithCompanyId(id, PageRequest.of(page, size));

        if(jobs.isEmpty()) {
            throw new NotFoundException();
        }

        for (JobEntity entry : jobs) {
            List<SubscriptionEntity> subscriptions = subscriptionRepository.findByJobId(entry.getId());
            List<SubscriptionModel> subsList = subscriptions.stream().map(j -> subscriptionConverter.toModel(j)).collect(Collectors.toList());

            SubscriptionJobModel model = new SubscriptionJobModel();

            model.setJobId(entry.getId());
            model.setType(entry.getType()) ;
            model.setTitle(entry.getTitle());
            model.setDateInitial(entry.getDateInitial());
            model.setDateFinal(entry.getDateFinal());
            model.setSubscriptions(subsList);
            model.setQuantity(subscriptions.size());
            list.add(model);
        }

        PaginationModel model = new PaginationModel();
        model.setTotal(jobs.getTotalElements());
        model.setItems(list);

        return model;
    }

    public void cancelSubscription(Long subscriptionId) {
        Optional<SubscriptionEntity> subs = subscriptionRepository.findById(subscriptionId);
        if(!subs.isPresent()) {
            throw new NotFoundException("Vaga nao encontrada");
        }

        subs.get().setActive(Boolean.FALSE);
        subscriptionRepository.save(subs.get());
    }
}
