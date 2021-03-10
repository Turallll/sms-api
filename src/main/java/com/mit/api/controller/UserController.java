package com.mit.api.controller;

import com.mit.api.model.dto.UserInfoDto;
import com.mit.api.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/image/upload")
    public void uploadImage(@RequestHeader("token") String token,
                            @RequestBody MultipartFile multipartFile) throws IOException {
        userService.uploadImage(token, multipartFile);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUserById(@RequestHeader("token") String token,
                               @PathVariable("userId") Long userId) {
        userService.deleteUserById(token, userId);
    }

    @GetMapping("/students/all")
    public List<UserInfoDto> getAllStudents(@RequestHeader("token") String token) {
        return userService.getStudents(token);
    }
}
