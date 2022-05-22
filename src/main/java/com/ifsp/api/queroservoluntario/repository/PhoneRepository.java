package com.ifsp.api.queroservoluntario.repository;

import com.ifsp.api.queroservoluntario.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {
}
