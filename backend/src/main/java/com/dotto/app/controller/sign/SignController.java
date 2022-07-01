package com.dotto.app.controller.sign;

import com.dotto.app.dto.response.Response;
import com.dotto.app.dto.sign.SignInRequest;
import com.dotto.app.dto.sign.SignUpRequest;
import com.dotto.app.service.sign.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.dotto.app.dto.response.Response.success;

@Api(value = "Sign Controller" ,tags ="sign" )
@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;


    @ApiOperation(value = "회원가입", notes = "회원가입 요청을 한다")
    @PostMapping("/api/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response SignUp( @RequestBody SignUpRequest req){
        signService.SignUp(req);
        return success();
    }

    @ApiOperation(value = "로그인", notes = "로그인 요청을 한다")
    @PostMapping("/api/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public Response SignIn(@RequestBody SignInRequest req){
        return success(signService.SignIn(req));
    }

    @ApiOperation(value = "액세스 토큰 제발급", notes = "리프레쉬 토큰을 통해 액세스토큰을 재발급 받는다")
    @PostMapping("/api/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    public Response refreshTokenResponse ( @RequestHeader(value = "Authorization") String refreshToken){
        return success(signService.refreshToken(refreshToken));
    }
}
