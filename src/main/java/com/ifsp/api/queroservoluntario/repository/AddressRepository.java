package com.ifsp.api.queroservoluntario.repository;

import com.ifsp.api.queroservoluntario.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
