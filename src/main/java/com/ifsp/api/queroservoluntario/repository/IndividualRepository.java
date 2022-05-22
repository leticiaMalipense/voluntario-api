package com.ifsp.api.queroservoluntario.repository;

import com.ifsp.api.queroservoluntario.entity.IndividualEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IndividualRepository extends JpaRepository<IndividualEntity, Long> {
    Optional<IndividualEntity> findById(Long individualId);

    @Query("select individual from IndividualEntity individual order by individual.creationDate asc")
    Page<IndividualEntity> findAllWithPagination(PageRequest of);

}
