package com.ifsp.api.queroservoluntario.repository;

import com.ifsp.api.queroservoluntario.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CompanyRepository extends PagingAndSortingRepository<CompanyEntity, Long> {

    List<CompanyEntity> findAll();

    @Query("select company from CompanyEntity company order by company.creationDate asc")
    Page<CompanyEntity> findAllWithPagination(PageRequest of);

}
