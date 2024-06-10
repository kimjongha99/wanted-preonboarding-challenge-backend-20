package com.example.wantedchallenge.infrastructure.user;

import com.example.wantedchallenge.domain.user.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findCustomUsers();
}