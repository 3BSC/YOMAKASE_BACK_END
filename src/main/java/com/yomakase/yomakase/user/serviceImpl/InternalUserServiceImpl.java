package com.yomakase.yomakase.user.serviceImpl;

import com.yomakase.yomakase.user.entity.UserEntity;
import com.yomakase.yomakase.user.repository.UserRepository;
import com.yomakase.yomakase.user.service.InternalUserService;
import com.yomakase.yomakase.util.JwtUtil;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InternalUserServiceImpl implements InternalUserService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public UserEntity getLoginUser() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) Objects
                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = request.getHeader("Authorization");

        Map<String, Object> payloads = jwtUtil.getPayloadsFromJwt(token);
        Long id = (Long) payloads.get("id");

        return userRepository.getById(id);
    }
}
