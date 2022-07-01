package com.dotto.app.service.sign;

import com.dotto.app.config.token.TokenHelper;
import com.dotto.app.dto.sign.RefreshTokenResponse;
import com.dotto.app.dto.sign.SignInRequest;
import com.dotto.app.dto.sign.SignInResponse;
import com.dotto.app.dto.sign.SignUpRequest;
import com.dotto.app.entity.member.Member;
import com.dotto.app.entity.member.MemberRole;
import com.dotto.app.entity.member.Role;
import com.dotto.app.entity.member.RoleType;
import com.dotto.app.exception.*;
import com.dotto.app.repository.member.MemberRepository;
import com.dotto.app.repository.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SignService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final TokenHelper accessTokenHelper;
    private final TokenHelper refreshTokenHelper;

    @Transactional
    public void SignUp(SignUpRequest req){
        validSignUpInfo(req);
        String encodedPwd = passwordEncoder.encode(req.getPassword());
        List<Role> roles = List.of(roleRepository.findByRoleType(RoleType.ROlE_NORMAL).orElseThrow(RoleNotFoundException::new));
        memberRepository.save(
                new Member(
                        req.getId(),
                        encodedPwd,
                        req.getNickname(),
                        req.getGender(),
                        req.getPhone(),
                        roles
                )
        );
    }

    @Transactional(readOnly = true)
    public SignInResponse SignIn(SignInRequest req){
        Member member = memberRepository.findWithRolesById(req.getId()).orElseThrow(MemberNotFoundException::new);
        validPwd(req, member);
        TokenHelper.PrivateClaims privateClaims = createPrivateClaims(member);
        String accessToken = accessTokenHelper.createToken(privateClaims);
        String refreshToken = refreshTokenHelper.createToken(privateClaims);
        String roles = String.valueOf(member.getRoles().stream()
                .map(memberRole -> memberRole.getRole())
                .map(role -> role.getRoleType())
                .map(roleType -> roleType.toString())
                .collect(Collectors.toList()));
        return new SignInResponse(accessToken, refreshToken, member.getNickname(),roles);
    }


    private TokenHelper.PrivateClaims createPrivateClaims(Member member){
        return new TokenHelper.PrivateClaims(
                String.valueOf(member.getMemberNo()),
                member.getRoles().stream()
                .map(memberRole -> memberRole.getRole())
                .map(role -> role.getRoleType())
                .map(roleType -> roleType.toString())
                .collect(Collectors.toList())
        );
    }

    public RefreshTokenResponse refreshToken(String rToken){
        TokenHelper.PrivateClaims privateClaims = refreshTokenHelper.parse(rToken).orElseThrow(RefreshTokenFailureException::new);
        String accessToken = accessTokenHelper.createToken(privateClaims);
        return new RefreshTokenResponse(accessToken);
    }

    private void validPwd(SignInRequest req, Member member){
        if(!passwordEncoder.matches(req.getPassword(),member.getPassword())){
            throw new LoginFailureException();
        }

    }

    private void validSignUpInfo(SignUpRequest req){
        if(memberRepository.existsById(req.getId())){
            throw new MemberEmailAlreadyExistsException(req.getId());
        }
        if(memberRepository.existsByNickname(req.getNickname())){
            throw new MemberNicknameAlreadyExistsException(req.getNickname());
        }
    }
}
