package com.yomakase.user.dto.response;

import com.yomakase.user.dto.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {

    private UserResponse user;

    private Token token;
}
