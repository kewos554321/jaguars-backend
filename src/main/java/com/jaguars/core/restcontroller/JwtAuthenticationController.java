package com.jaguars.core.restcontroller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jaguars.core.configuration.impl.JwtUserDetailsService;
import com.jaguars.core.model.bean.UserProfile;
import com.jaguars.core.model.entity.UserEntity;
import com.jaguars.core.model.req.JwtRequest;
import com.jaguars.core.model.req.RefreshJwtRequest;
import com.jaguars.core.model.resp.AuthorizationResponse;
import com.jaguars.core.model.resp.JwtResponse;
import com.jaguars.core.service.interfaces.TokenService;
import com.jaguars.core.service.interfaces.UserService;
import com.jaguars.core.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
public class JwtAuthenticationController {
    
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest request) throws Exception {
        UserDetails userDetails = jwtUserDetailsService.loadUserByEmail(request.getEmail());

        String token = jwtUtil.generateToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);
        log.info("userDetails={}, token={}, refreshToken={}", userDetails.toString(), token, refreshToken);
        tokenService.createTokenRecord(token, refreshToken, request.getEmail());

        return ResponseEntity.ok(new JwtResponse(token, refreshToken));
    }

    @GetMapping(value = "/authorize")
    public ResponseEntity<AuthorizationResponse> authorization(
            @RequestHeader(name = "Authorization") String authorization) throws Exception {
        log.info("authorization getUserProfile = {}", authorization);
        String token = authorization.substring(7);
        String email = jwtUtil.extractEmail(token);
        Optional<UserEntity> user = userService.getUserByEmail(email);
        if (user.isPresent()) {
            UserProfile profile = new UserProfile();
            BeanUtils.copyProperties(user.get(), profile);
            AuthorizationResponse response = new AuthorizationResponse();
            response.setUserProfile(profile);
            return ResponseEntity.ok(response);
        } else {
            throw new Exception("can't find user");
        }
    }

    @PostMapping(value = "/refreshToken")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshJwtRequest request) throws Exception {
        UserDetails userDetails = jwtUserDetailsService.loadUserByEmail(request.getEmail());
        
        String newToken = jwtUtil.generateToken(userDetails);
        String newRefreshToken = jwtUtil.generateRefreshToken(userDetails);
        log.info("userDetails={}, token={}, refreshToken={}", userDetails.toString(), newToken, newRefreshToken);
        tokenService.createTokenRecord(newToken, newRefreshToken, request.getEmail());

        return ResponseEntity.ok(new JwtResponse(newToken, newRefreshToken));
    }



}
