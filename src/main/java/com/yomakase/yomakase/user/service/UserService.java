package com.yomakase.yomakase.user.service;

import com.yomakase.yomakase.user.dto.request.SignUpRequest;
import com.yomakase.yomakase.user.dto.request.UserRequest;
import com.yomakase.yomakase.user.dto.response.LoginResponse;
import com.yomakase.yomakase.user.dto.response.SignUpResponse;
import com.yomakase.yomakase.user.dto.response.UserResponse;

public interface UserService {

    SignUpResponse signUp(SignUpRequest request) throws Exception;

    LoginResponse login(UserRequest request) throws Exception;

    UserResponse getMe() throws Exception;
}
