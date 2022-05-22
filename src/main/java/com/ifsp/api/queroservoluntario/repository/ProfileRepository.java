package com.ifsp.api.queroservoluntario.repository;

import com.ifsp.api.queroservoluntario.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    ProfileEntity findByType(String type);
}
