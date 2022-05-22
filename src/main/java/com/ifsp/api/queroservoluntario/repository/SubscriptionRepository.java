package com.ifsp.api.queroservoluntario.repository;

import com.ifsp.api.queroservoluntario.entity.JobEntity;
import com.ifsp.api.queroservoluntario.entity.SubscriptionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

    @Query("SELECT sub FROM SubscriptionEntity sub WHERE sub.individual.id= ?1 AND sub.active=TRUE AND sub.job.active=TRUE ")
    Page<SubscriptionEntity> findAllByIndividualId(Long id, Pageable pageable);

    @Query("SELECT DISTINCT sub.job FROM SubscriptionEntity sub WHERE sub.companyId = ?1 AND sub.active=TRUE AND sub.job.active=TRUE AND sub.active=TRUE ")
    Page<JobEntity> findJobWithCompanyId(Long id, Pageable pageable);

    @Query("SELECT sub FROM SubscriptionEntity sub join fetch sub.job j " +
            " join fetch sub.individual ind WHERE ind.id = ?1 AND j.id = ?2 AND sub.active=TRUE AND j.active=TRUE ")
    Optional<SubscriptionEntity> findByIndividualIdAndJobId(Long individualId, Long jobId);

    @Query("SELECT sub FROM SubscriptionEntity sub join fetch sub.job j WHERE j.id = ?1 AND sub.active=TRUE ")
    List<SubscriptionEntity> findByJobId(Long jobId);
}
