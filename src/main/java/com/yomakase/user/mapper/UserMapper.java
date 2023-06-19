package com.yomakase.user.mapper;

import com.yomakase.user.dto.Token;
import com.yomakase.user.dto.request.UserRequest;
import com.yomakase.user.dto.response.SignUpResponse;
import com.yomakase.user.dto.response.UserResponse;
import com.yomakase.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toUserEntity(UserRequest userRequest);

    UserResponse toUserResponse(UserEntity userEntity);

    @Mapping(target = "user", source = "userEntity")
    SignUpResponse toSignUpResponse(UserEntity userEntity, Token token);
}