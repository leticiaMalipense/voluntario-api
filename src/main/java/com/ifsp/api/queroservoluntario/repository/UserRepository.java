package com.ifsp.api.queroservoluntario.repository;

import com.ifsp.api.queroservoluntario.entity.IndividualEntity;
import com.ifsp.api.queroservoluntario.entity.UserEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String username);

    @Query("SELECT user FROM UserEntity user where user.email = ?1 AND user.active = TRUE ")
    Optional<UserEntity> findByEmailActive(String username);

    Optional<UserEntity> findByEmailOrDocument(String email, String document);

    @Modifying
    @Query("UPDATE UserEntity set password = ?2 where id = ?1")
    void updatePassword(Long id, String password);
}
