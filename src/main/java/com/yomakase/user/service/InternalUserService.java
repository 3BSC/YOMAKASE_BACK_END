package com.yomakase.user.service;

import com.yomakase.user.entity.UserEntity;

public interface InternalUserService {

    UserEntity getLoginUser() throws Exception;
}
