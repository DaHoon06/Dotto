package com.dotto.app.dto.sign;

import com.dotto.app.entity.member.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class SignInResponse {

    private String accessToken;
    private String refreshToken;
    private String nickname;
    private String roles;


}
