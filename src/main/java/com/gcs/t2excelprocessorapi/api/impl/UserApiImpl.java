package com.gcs.t2excelprocessorapi.api.impl;

import org.openapitools.api.UsersApi;
import org.openapitools.model.UsersCreatePostRequest;
import org.springframework.http.ResponseEntity;

import com.gcs.t2excelprocessorapi.services.IUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiImpl implements UsersApi{

    private final IUserService userService;

    @Override
    public ResponseEntity<Void> usersCreatePost(UsersCreatePostRequest usersCreatePostRequest) {
        log.info("usersCreatePost| username={}, email={}, role={}", usersCreatePostRequest.getUsername(),usersCreatePostRequest.getEmail(), usersCreatePostRequest.getRole());
        this.userService.create(usersCreatePostRequest);
        return ResponseEntity.ok().body(null);
    }

}
