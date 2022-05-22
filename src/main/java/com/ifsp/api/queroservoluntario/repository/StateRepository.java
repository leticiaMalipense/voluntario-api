package com.ifsp.api.queroservoluntario.repository;

import com.ifsp.api.queroservoluntario.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface StateRepository extends JpaRepository<StateEntity, Long> {

    StateEntity findByAbbreviation(String abbreviation);
}
