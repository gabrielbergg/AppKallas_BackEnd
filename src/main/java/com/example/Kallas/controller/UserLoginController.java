package com.example.Kallas.controller;

import com.example.Kallas.dto.AuthenticationDto;
import com.example.Kallas.dto.LoginResponseDto;
import com.example.Kallas.dto.RegisterDto;
import com.example.Kallas.model.UserLogin;
import com.example.Kallas.repository.UserLoginRepository;
import com.example.Kallas.service.TokenService;
import com.example.Kallas.service.UserLoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class UserLoginController {

    @Autowired
    UserLoginRepository userLoginRepository;

    @Autowired
    UserLoginService userLoginService;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDto data) {
        var loginPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(loginPassword);

        String token = tokenService.generateToken((UserLogin) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        if(userLoginRepository.findByLogin(registerDto.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encriptPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        UserLogin newUser = new UserLogin(registerDto.login(), registerDto.role(), encriptPassword);

        userLoginRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
