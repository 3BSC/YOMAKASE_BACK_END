package com.yomakase.yomakase.user.dto.response;

import com.yomakase.yomakase.user.enums.UserType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String email;

    private String nickname;

    private Date birthDay;

    private UserType type;

    private String phoneNumber;

}
