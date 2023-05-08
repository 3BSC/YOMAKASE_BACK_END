package com.yomakase.yomakase.user.serviceImpl;

import com.yomakase.yomakase.user.dto.Token;
import com.yomakase.yomakase.user.dto.request.SignUpRequest;
import com.yomakase.yomakase.user.dto.request.UserRequest;
import com.yomakase.yomakase.user.dto.response.SignUpResponse;
import com.yomakase.yomakase.user.dto.response.UserResponse;
import com.yomakase.yomakase.user.entity.UserEntity;
import com.yomakase.yomakase.user.enums.UserType;
import com.yomakase.yomakase.user.mapper.UserMapper;
import com.yomakase.yomakase.user.repository.UserRepository;
import com.yomakase.yomakase.user.service.UserService;
import com.yomakase.yomakase.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    @Override
    public SignUpResponse signUp(SignUpRequest request) throws Exception {

        UserRequest userRequest = request.getUserRequest();
        UserEntity user = UserMapper.INSTANCE.toUserEntity(userRequest).toBuilder()
                .password(BCrypt.hashpw(userRequest.getPassword(), BCrypt.gensalt()))
                .type(UserType.NORMAL)
                .build();

        userRepository.save(user);
        Token token = getLoginToken(user.getId(), user.getType());

        SignUpResponse response = UserMapper.INSTANCE.toSignUpResponse(user, token);
        return response;
    }

    @Override
    public Token login(UserRequest request) throws Exception {

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new Exception());

        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            throw new Exception();
        }

        return getLoginToken(user.getId(), user.getType());
    }

    private Token getLoginToken(Long userId, UserType type) throws Exception {

        Token token = new Token();
        token.setAccessToken(jwtUtil.generateAccessToken(userId, type));

        Integer deviceId = (int)(Math.random() * 10000);
        token.setRefreshToken(jwtUtil.generateRefreshToken(userId, String.valueOf(deviceId)));

        return token;
    }

    @Override
    public UserResponse getMe() throws Exception {
        return null;
    }

    private void validateExistAccount(String account) throws Exception {
        if (userRepository.existsByAccount(account)) {
            throw new Exception();
        }
    }

    private void validateExistEmail(String email) throws Exception {
        if (userRepository.existsByEmail(email)) {
            throw new Exception();
        }
    }

}
