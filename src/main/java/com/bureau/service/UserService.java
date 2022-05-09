package com.bureau.service;

import com.bureau.exception.ItemNotFoundException;
import com.bureau.mapper.UserMapper;
import com.bureau.model.dto.request.user.PatchUserRequest;
import com.bureau.model.dto.response.UserInfoResponse;
import com.bureau.model.entity.User;
import com.bureau.repository.UserRepository;
import com.bureau.util.ErrorStatusCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * Returns current User info from jwt
     *
     * @return UserInfoResponse object
     */
    public UserInfoResponse getUserInfo() {
        return userMapper.userToUserInfoResponse(getUserFromJwt());
    }

    /**
     * Change password of current user
     *
     * @param req
     */
    public void changePassword(PatchUserRequest req) {
        User user = getUserFromJwt();
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepository.save(user);
    }

    /**
     * Returns User from jwt authentication
     *
     * @return User object
     */
    public User getUserFromJwt() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow(ItemNotFoundException::new);
    }

    public List<User> findByIds(Set<Long> ids) {
        return userRepository.findAllById(ids);
    }

    public User findByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item not found with id " + id, ErrorStatusCodes.USER_NOT_FOUND));
    }
}
