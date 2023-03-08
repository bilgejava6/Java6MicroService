package com.muhammet.controller;

import com.muhammet.dto.request.DoLoginRequestDto;
import com.muhammet.dto.request.RegisterRequestDto;
import com.muhammet.exception.AuthServiceException;
import com.muhammet.exception.EErrorType;
import com.muhammet.repository.entity.Auth;
import com.muhammet.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.muhammet.constants.EndPoints.*;
@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<Auth> register(@RequestBody RegisterRequestDto dto){
        if(!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthServiceException(EErrorType.REGISTER_ERROR_PASSWORD_UNMATCH);
        return ResponseEntity.ok(authService.register(dto));
    }
    @PostMapping(DOLOGIN)
    public ResponseEntity<String> doLogin(@RequestBody DoLoginRequestDto dto){
        return ResponseEntity.ok(authService.doLogin(dto));
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<Auth>> findAll(String token){
       return ResponseEntity.ok(authService.findAll(token));
    }
}
