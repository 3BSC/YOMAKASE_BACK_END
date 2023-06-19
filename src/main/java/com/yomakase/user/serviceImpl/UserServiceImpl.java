package com.yomakase.user.serviceImpl;

import com.yomakase.etc.enums.ExceptionMessage;
import com.yomakase.etc.exception.NonCriticalException;
import com.yomakase.user.dto.Token;
import com.yomakase.user.dto.request.OwnerSignUpRequest;
import com.yomakase.user.dto.request.UserRequest;
import com.yomakase.user.dto.response.SignUpResponse;
import com.yomakase.user.dto.response.UserResponse;
import com.yomakase.user.entity.UserEntity;
import com.yomakase.user.enums.UserType;
import com.yomakase.user.mapper.UserMapper;
import com.yomakase.user.repository.UserRepository;
import com.yomakase.user.service.InternalUserService;
import com.yomakase.user.service.UserService;
import com.yomakase.util.JwtUtil;
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

    private final InternalUserService internalUserService;

    @Transactional
    @Override
    public SignUpResponse signUp(UserRequest request) throws Exception {

        UserEntity user = userSignUp(request, UserType.NORMAL);

        Token token = getLoginToken(user.getId(), user.getType());

        SignUpResponse response = UserMapper.INSTANCE.toSignUpResponse(user, token);
        return response;
    }

    @Override
    public SignUpResponse ownerSignUp(OwnerSignUpRequest request) throws Exception {

        UserRequest userRequest = request.getUserRequest();
        UserEntity user = userSignUp(userRequest, UserType.OWNER);

        Token token = getLoginToken(user.getId(), user.getType());

        SignUpResponse response = UserMapper.INSTANCE.toSignUpResponse(user, token);
        return response;
    }

    private UserEntity userSignUp(UserRequest request, UserType type) throws Exception {
        validateExistEmail(request.getEmail());

        UserEntity user = UserMapper.INSTANCE.toUserEntity(request).toBuilder()
                .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
                .type(type)
                .build();

        userRepository.save(user);
        return user;
    }

    @Override
    public Token login(UserRequest request) throws Exception {

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NonCriticalException(ExceptionMessage.USER_NOT_EXISTS_EXCEPTION));

        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            throw new NonCriticalException(ExceptionMessage.INVALID_ACCESS);
        }

        return getLoginToken(user.getId(), user.getType());
    }

    private Token getLoginToken(Long userId, UserType type) throws Exception {

        Token token = new Token();
        token.setAccessToken(jwtUtil.generateAccessToken(userId, type));

        Integer deviceId = (int) (Math.random() * 10000);
        token.setRefreshToken(jwtUtil.generateRefreshToken(userId, String.valueOf(deviceId)));

        return token;
    }

    @Override
    public UserResponse getMe() throws Exception {
        UserEntity user = internalUserService.getLoginUser();
        return UserMapper.INSTANCE.toUserResponse(user);
    }

    private void validateExistEmail(String email) throws Exception {
        if (userRepository.existsByEmail(email)) {
            throw new NonCriticalException(ExceptionMessage.USER_ALREADY_EXIST);
        }
    }

}
