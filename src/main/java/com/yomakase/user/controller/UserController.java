package com.yomakase.user.controller;

import com.yomakase.etc.annotation.Auth;
import com.yomakase.user.dto.Token;
import com.yomakase.user.dto.request.OwnerSignUpRequest;
import com.yomakase.user.dto.request.UserRequest;
import com.yomakase.user.dto.response.SignUpResponse;
import com.yomakase.user.dto.response.UserResponse;
import com.yomakase.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Controller", description = "유저 관련 컨트롤러")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/user/register")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody UserRequest request) throws Exception {
        return new ResponseEntity<>(userService.signUp(request), HttpStatus.CREATED);
    }

    @PostMapping(value = "/owner/register")
    public ResponseEntity<SignUpResponse> ownerSignUp(@RequestBody OwnerSignUpRequest request) throws Exception {
        return new ResponseEntity<>(userService.ownerSignUp(request), HttpStatus.CREATED);
    }

    @PostMapping(value = "/user/login")
    public ResponseEntity<Token> login(@RequestBody UserRequest request) throws Exception {
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }

    @Auth
    @GetMapping(value = "/user/me")
    public ResponseEntity<UserResponse> getMe() throws Exception {
        return new ResponseEntity<>(userService.getMe(), HttpStatus.OK);
    }

}
