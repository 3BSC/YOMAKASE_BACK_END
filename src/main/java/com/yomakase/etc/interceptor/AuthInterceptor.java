package com.yomakase.etc.interceptor;

import com.yomakase.etc.annotation.Auth;
import com.yomakase.etc.enums.ExceptionMessage;
import com.yomakase.etc.enums.TokenType;
import com.yomakase.etc.exception.NonCriticalException;
import com.yomakase.user.enums.UserType;
import com.yomakase.util.JwtUtil;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Auth auth = handlerMethod.getMethod().getDeclaredAnnotation(Auth.class);

        if (auth == null) {
            return true;
        } else {

            String accessToken = request.getHeader("Authorization");
            jwtUtil.isValid(accessToken, TokenType.ACCESS);

            Map<String, Object> payload = jwtUtil.getPayloadsFromJwt(accessToken);
            UserType userType = UserType.valueOf((String) payload.get("aud"));

            if (auth.role().equals(UserType.OWNER)) {
                return true;
            }

            if (auth.role().equals(userType)) {
                return true;
            }

        }

        throw new NonCriticalException(ExceptionMessage.UNAUTHORIZED_ACCESS);
    }
}
