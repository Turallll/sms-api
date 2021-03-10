package com.mit.api.controller;

import com.mit.api.model.dto.Token;
import com.mit.api.model.dto.UserDto;
import com.mit.api.service.RegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public Token register(@RequestBody UserDto userDto) {
        return registerService.register(userDto);
    }
}
