package com.mit.api.service;

import com.mit.api.error.InvalidIdentifierException;
import com.mit.api.mapper.UserMapper;
import com.mit.api.model.dto.Token;
import com.mit.api.model.dto.UserDto;
import com.mit.api.model.entity.Role;
import com.mit.api.model.entity.User;
import com.mit.api.model.type.RoleType;
import com.mit.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.UUID;

import static com.mit.api.util.Constants.STUDENT_IDENTIFIERS;
import static com.mit.api.util.Constants.TEACHER_IDENTIFIERS;

@Slf4j
@Service
public class RegisterService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public RegisterService(UserMapper userMapper,
                           UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public Token register(UserDto userDto) {
        User user = null;
        Role roleEntity = new Role();
        String role = null;

        /**
         * Check that user is student or teacher
         * If user is teacher then he/she has TEACHER role. Otherwise has STUDENT role
         */
        if (STUDENT_IDENTIFIERS.contains(userDto.getIdentifier())) {
            user = userMapper.toUser(userDto);
            role = RoleType.STUDENT.name();
            roleEntity.setRole(role);
            user.setRole(roleEntity);
        } else if (TEACHER_IDENTIFIERS.contains(userDto.getIdentifier())) {
            user = userMapper.toUser(userDto);
            role = RoleType.TEACHER.name();
            roleEntity.setRole(role);
            user.setRole(roleEntity);
        } else {
            throw new InvalidIdentifierException("Identifier is not valid");
        }

        log.info("Create token for user");
        String token = UUID.randomUUID().toString();
        user.setAccessToken(token);

        log.info("Save user to db");
        userRepository.save(user);

        senEmailToUser(userDto.getEmail());
        return new Token(token);
    }

    private void senEmailToUser(String email) {

    }
}

