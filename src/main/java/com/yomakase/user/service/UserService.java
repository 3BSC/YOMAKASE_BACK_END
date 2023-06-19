package com.yomakase.user.service;

import com.yomakase.user.dto.Token;
import com.yomakase.user.dto.request.OwnerSignUpRequest;
import com.yomakase.user.dto.request.UserRequest;
import com.yomakase.user.dto.response.SignUpResponse;
import com.yomakase.user.dto.response.UserResponse;

public interface UserService {

    SignUpResponse ownerSignUp(OwnerSignUpRequest request) throws Exception;

    SignUpResponse signUp(UserRequest request) throws Exception;

    Token login(UserRequest request) throws Exception;

    UserResponse getMe() throws Exception;
}
