package com.yomakase.yomakase.user.serviceImpl;

import com.yomakase.yomakase.etc.enums.TokenType;
import com.yomakase.yomakase.user.dto.Token;
import com.yomakase.yomakase.user.dto.request.SignUpRequest;
import com.yomakase.yomakase.user.dto.request.UserRequest;
import com.yomakase.yomakase.user.dto.response.LoginResponse;
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
        SignUpResponse response = UserMapper.INSTANCE.toSignUpResponse(user);

        Token token = getLoginToken(user.getId(), user.getType());
        response.setToken(token);

        return response;
    }

    @Override
    public LoginResponse login(UserRequest request) throws Exception {
        return null;
    }

    private Token getLoginToken(Long userId, UserType type) throws Exception {

        Token token = new Token();

        token.setAccessToken(jwtUtil.generateToken(userId, TokenType.ACCESS, type));
        token.setRefreshToken(jwtUtil.generateToken(userId, TokenType.REFRESH, type));

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
