package com.example.wantedchallenge.infrastructure.user;

import com.example.wantedchallenge.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findById(Long id); // JPA 기본 제공 메서드 사용


}
