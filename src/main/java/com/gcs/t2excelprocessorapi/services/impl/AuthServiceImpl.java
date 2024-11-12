package com.gcs.t2excelprocessorapi.services.impl;

import com.gcs.t2excelprocessorapi.entities.UserEntity;
import com.gcs.t2excelprocessorapi.exceptions.AuthenticationFailedException;
import com.gcs.t2excelprocessorapi.models.UserDetailsImpl;
import com.gcs.t2excelprocessorapi.repositories.IUserRepository;
import com.gcs.t2excelprocessorapi.services.IAuthService;
import com.gcs.t2excelprocessorapi.services.IJwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService, UserDetailsService {
    private final IUserRepository userRepository;
    private final IJwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String authenticate(String username, String password) {
        log.info("authenticate| username={}", username);
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AuthenticationFailedException("The username and password invalid"));

        if (!passwordEncoder.matches(password, user.getPwd())) {
            throw new AuthenticationFailedException("The username and password invalid");
        }
        String token = jwtService.generateToken(user.getUsername(), user.getRole().toString());
        log.info("authenticate| length token={}",token);
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity opUserEntity = this.userRepository.findByUsername(username).orElseThrow(
            ()-> new UsernameNotFoundException("There is no user with the name"));
        
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(opUserEntity);

        return userDetailsImpl;
        
        
    }
}
