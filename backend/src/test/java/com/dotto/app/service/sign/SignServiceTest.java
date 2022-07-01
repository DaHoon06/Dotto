package com.dotto.app.service.sign;

import com.dotto.app.config.token.TokenHelper;
import com.dotto.app.dto.sign.RefreshTokenResponse;
import com.dotto.app.dto.sign.SignInResponse;
import com.dotto.app.dto.sign.SignUpRequest;
import com.dotto.app.entity.member.Role;
import com.dotto.app.entity.member.RoleType;
import com.dotto.app.repository.member.MemberRepository;
import com.dotto.app.repository.role.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static com.dotto.app.factory.dto.SignInRequestFactory.createSignInRequest;
import static com.dotto.app.factory.dto.SignUpRequestFactory.createSignUpRequest;
import static com.dotto.app.factory.entity.MemberFactory.createMember;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SignServiceTest {

    SignService signService;
    @Mock
    MemberRepository memberRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    TokenHelper accessTokenHelper;
    @Mock
    TokenHelper refreshTokenHelper;

    @BeforeEach
    void beforeEach(){
        signService = new SignService(memberRepository, roleRepository, passwordEncoder, accessTokenHelper, refreshTokenHelper);
    }

    @Test
    void signUpTest() {
        //given
        SignUpRequest req = createSignUpRequest();
        given(roleRepository.findByRoleType(RoleType.ROlE_NORMAL)).willReturn(Optional.of(new Role(RoleType.ROlE_NORMAL)));

        //when
        signService.SignUp(req);

        //then
        verify(passwordEncoder).encode(req.getPassword());
        verify(memberRepository).save(any());
    }

    @Test
    void signInTest() {
        //given
        given(memberRepository.findWithRolesById(any())).willReturn(Optional.of(createMember()));
        given(passwordEncoder.matches(anyString(),anyString())).willReturn(true);
        given(accessTokenHelper.createToken(any())).willReturn("access");
        given(refreshTokenHelper.createToken(any())).willReturn("refresh");

        //when
        SignInResponse res = signService.SignIn(createSignInRequest());

        //then
        assertThat(res.getAccessToken()).isEqualTo("access");
        assertThat(res.getRefreshToken()).isEqualTo("refresh");
    }

    @Test
    void refreshTokenTest() {
        //given
        String refreshToken = "refreshToken";
        String subject = "subject";
        String accessToken = "accessToken";
        given(refreshTokenHelper.parse(refreshToken)).willReturn(Optional.of(new TokenHelper.PrivateClaims("memberId", List.of("ROLE_NORMAL"))));
        given(accessTokenHelper.createToken(any())).willReturn(accessToken);

        //when
        RefreshTokenResponse res = signService.refreshToken(refreshToken);

        //then
        assertThat(res.getAccessToken()).isEqualTo(accessToken);
    }
}