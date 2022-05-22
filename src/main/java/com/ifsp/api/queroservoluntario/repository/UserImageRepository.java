package com.ifsp.api.queroservoluntario.repository;

import com.ifsp.api.queroservoluntario.entity.UserImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserImageRepository  extends JpaRepository<UserImageEntity, Long> {
    Optional<UserImageEntity> findByUserId(Long id);
}
