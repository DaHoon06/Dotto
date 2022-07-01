package com.dotto.app.dto.sign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Api(value = "회원가입 요청")
@Data@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @ApiModelProperty(value = "아이디", notes = "아이디를 입력해 주세요", required = true, example = "member1")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,20}+$", message = "4 ~ 20자 영문 숫자만 가능합니다")
    @Size(min = 4, max = 20)
    @NotBlank
    private String id;

    @ApiModelProperty(value = "비밀번호", notes = "비밀번호를 입력해 주세요", required = true, example = "123456a!")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z0-9\\d@$!%*#?&]{8,20}$", message = "8 ~ 20자 이내 영문 , 숫자, 특수문자 1개씩 포함")
    @Size(min = 8, max = 20)
    @NotBlank
    private String password;

    @ApiModelProperty(value = "닉네임", notes = "닉네임을 입력해 주세요", required = true, example = "nickname")
    @Pattern(regexp = "^[A-Za-z0-9가-힣]{1,12}+$", message = "1~12글자 이내에 (특수문자 제외) 입니다")
    @Size(min = 1, max = 12)
    @NotBlank
    private String nickname;

    @ApiModelProperty(value = "성별", notes = "성별을 입력해 주세요", required = true, example = "남")
    @NotBlank
    private String gender;

    @ApiModelProperty(value = "연락처", notes = "연락처를 입력해 주세요", required = true, example = "01012345678")
    @Pattern(regexp = "^01([016789])-?([0-9]{3,4})-?([0-9]{4})$", message = "핸드폰 형식으로 입력해 주세요")
    @NotBlank
    private String phone;


}
