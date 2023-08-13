package com.jaguars.core.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jaguars.core.model.entity.UserEntity;
import com.jaguars.core.model.req.SignupRequest;
import com.jaguars.core.service.interfaces.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping( path="/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/sayHellow")
    @ResponseBody
    public ResponseEntity<String> sayHellow() {
       return ResponseEntity.ok("sayHellow");
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> singup(@RequestBody SignupRequest request) {
        UserEntity user = userService.createUser(request);
        return ResponseEntity.ok(null);
    }

}
