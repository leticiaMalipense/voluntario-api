package com.ifsp.api.queroservoluntario.service;

import com.ifsp.api.queroservoluntario.entity.PhoneEntity;
import com.ifsp.api.queroservoluntario.repository.PhoneRepository;
import com.ifsp.api.queroservoluntario.rest.model.PhoneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Long userId, List<PhoneModel> phones) {

        phones.forEach( phone -> {
            PhoneEntity phoneEntity = new PhoneEntity();
            phoneEntity.setUserId(userId);
            phoneEntity.setDdd(phone.getDdd());
            phoneEntity.setNumber(phone.getNumber());

            phoneRepository.save(phoneEntity);
        });
    }
}
