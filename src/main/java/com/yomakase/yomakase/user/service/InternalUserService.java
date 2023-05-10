package com.yomakase.yomakase.user.service;

import com.yomakase.yomakase.user.entity.UserEntity;

public interface InternalUserService {

    UserEntity getLoginUser() throws Exception;
}
