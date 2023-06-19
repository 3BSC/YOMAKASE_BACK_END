package com.yomakase.user.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String email;

    private String password;

    private String nickname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    private String phoneNumber;
}
