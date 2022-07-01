package com.dotto.app.dto.sign;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ApiModel(value = "로그인 요청")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {

    @ApiModelProperty(value = "아이디", notes = "아이디를 입력해 주세요", required = true, example = "member1")
    @NotBlank
    private String id;

    @ApiModelProperty(value = "비밀번호", notes = "비밀번호를 입력해 주세요", required = true, example = "123456a!")
    @NotBlank
    private String password;
}
