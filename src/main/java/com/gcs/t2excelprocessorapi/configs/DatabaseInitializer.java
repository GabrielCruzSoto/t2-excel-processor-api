package com.gcs.t2excelprocessorapi.configs;

import com.gcs.t2excelprocessorapi.entities.UserEntity;
import com.gcs.t2excelprocessorapi.mappers.IUserMapper;
import com.gcs.t2excelprocessorapi.repositories.IUserRepository;

import org.openapitools.model.UsersCreatePostRequest.RoleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class DatabaseInitializer {

    @Bean
    public CommandLineRunner initDatabase(IUserRepository userRepository, IUserMapper userMapper, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByUsername("admin")) {
                UserEntity adminUser = UserEntity.builder()
                        .username("admin")
                        .email("admin@example.com")
                        .pwd(passwordEncoder.encode("12345"))
                        .role(RoleEnum.ADMIN) // Asegúrate de que RoleEnum.ADMIN esté definido
                        .build();
                userRepository.save(adminUser);
            }
        };
    }
} 