package com.yomakase.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yomakase.user.enums.UserType;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    private UserType type;

    private String phoneNumber;

}
