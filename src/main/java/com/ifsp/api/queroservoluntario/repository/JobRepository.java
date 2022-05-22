package com.ifsp.api.queroservoluntario.repository;

import com.ifsp.api.queroservoluntario.entity.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public interface JobRepository extends JpaRepository<JobEntity, Long> {

    @Query("select job from JobEntity job join fetch job.company comp where job.id = ?1 and job.active = TRUE")
    Optional<JobEntity> findById(Long id);

    @Query("select job from JobEntity job where job.company.id = ?1 and job.active = TRUE")
    Page<JobEntity> findAllByCompanyId(Long companyId, Pageable pageable);

    @Query("select job from JobEntity job join fetch job.company comp where job.id = ?1 and job.active = TRUE")
    Optional<JobEntity> findByIdWithCompany(Long jobId);

    @Query("select job from JobEntity job where job.stateId = ?1 and job.active = TRUE and lower(job.city) like lower(concat(?2,'%')) ")
    Page<JobEntity> findByFilter(Long stateId, String city, PageRequest of);

    @Query("select job from JobEntity job where job.stateId = ?1 and job.active = TRUE ")
    Page<JobEntity> findByFilter(Long stateId, PageRequest of);

}
