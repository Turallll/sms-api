package com.mit.api.service;

import com.mit.api.error.AccessDeniedException;
import com.mit.api.error.InvalidTokenException;
import com.mit.api.mapper.UserMapper;
import com.mit.api.model.dto.UserInfoDto;
import com.mit.api.model.entity.Image;
import com.mit.api.model.entity.User;
import com.mit.api.model.type.RoleType;
import com.mit.api.repository.ImageRepository;
import com.mit.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(ImageRepository imageRepository,
                       UserRepository userRepository,
                       UserMapper userMapper) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void uploadImage(String token, MultipartFile multipartFile) throws IOException {
        getUserByToken(token);

        String imageBytes = new String(multipartFile.getBytes());

        log.info("Create image object");
        Image image = new Image();
        image.setImageBytes(imageBytes);

        log.info("Save image to db");
        imageRepository.save(image);
    }

    public void deleteUserById(String token, Long userId) {
        User user = getUserByToken(token);

        if (!user.getRole().getRole().equals(RoleType.TEACHER.name())) {
            throw new AccessDeniedException("You do not have permission to delete user");
        }

        log.info("Delete user by user id");
        userRepository.deleteById(userId);
    }

    public List<UserInfoDto> getStudents(String token) {
        User user = getUserByToken(token);

        if (!user.getRole().getRole().equals(RoleType.TEACHER.name())) {
            throw new AccessDeniedException("You do not have permission to get all students");
        }

        log.info("Find all students by STUDENT role");
        List<User> students = userRepository.findAllByRole(RoleType.STUDENT.name());

        List<UserInfoDto> studentInfos = new ArrayList<>();

        for (User student : students) {
            studentInfos.add(userMapper.toUserInfo(student));
        }

        return studentInfos;
    }

    private User getUserByToken(String token) {
        Optional<User> user = userRepository.findByAccessToken(token);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new InvalidTokenException("Token is not valid");
        }
    }
}
