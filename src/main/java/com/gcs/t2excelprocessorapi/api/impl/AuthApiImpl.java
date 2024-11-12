package com.gcs.t2excelprocessorapi.api.impl;

import com.gcs.t2excelprocessorapi.services.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.api.AuthApi;
import org.openapitools.model.AuthLoginPost200Response;
import org.openapitools.model.AuthLoginPostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthApiImpl implements AuthApi{
    private final IAuthService authService;

    @Override
    public ResponseEntity<AuthLoginPost200Response> authLoginPost(AuthLoginPostRequest request) {
        log.info("authLoginPost| username={}",request.getUsername());
        String token = authService.authenticate(request.getUsername(), request.getPassword());

        AuthLoginPost200Response response = new AuthLoginPost200Response();
        response.setToken(token);
        log.info("authLoginPost| length token={}",token);
        return ResponseEntity.ok(response);
    }

}
