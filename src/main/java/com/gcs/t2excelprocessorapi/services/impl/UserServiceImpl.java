package com.gcs.t2excelprocessorapi.services.impl;

import com.gcs.t2excelprocessorapi.entities.UserEntity;
import com.gcs.t2excelprocessorapi.exceptions.UserConflictException;
import com.gcs.t2excelprocessorapi.mappers.IUserMapper;
import com.gcs.t2excelprocessorapi.repositories.IUserRepository;
import com.gcs.t2excelprocessorapi.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.openapitools.model.UsersCreatePostRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void create(UsersCreatePostRequest usersCreatePostRequest) {
        log.info("create| username={}, email={}, role={}", usersCreatePostRequest.getUsername(),usersCreatePostRequest.getEmail(), usersCreatePostRequest.getRole());
        if(this.userRepository.existsByUsername(usersCreatePostRequest.getUsername())){
            throw new UserConflictException("A user with name already exists");
        }

        if(this.userRepository.existsByEmail(usersCreatePostRequest.getEmail())){
            throw new UserConflictException("A user with email already exists");
        }
        UserEntity userEntity = this.userMapper.toEntity(usersCreatePostRequest);
        userEntity.setPwd(this.passwordEncoder.encode(usersCreatePostRequest.getPassword()));
        this.userRepository.save(userEntity);

        log.info("create| save user sucess");
    }
}
