package com.dotto.app;

import com.dotto.app.entity.member.Member;
import com.dotto.app.entity.member.Role;
import com.dotto.app.entity.member.RoleType;
import com.dotto.app.entity.post.DottoPost;
import com.dotto.app.exception.MemberNotFoundException;
import com.dotto.app.exception.RoleNotFoundException;
import com.dotto.app.repository.member.MemberRepository;
import com.dotto.app.repository.post.DottoPostRepository;
import com.dotto.app.repository.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("local")
public class InitDB {

    private final RoleRepository roleRepository;
    private final MemberRepository memberRepository;
    private final DottoPostRepository dottoPostRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initDB(){
        initRole();
        initMember();
        initDottoPost();
        log.info("initialize database");
    }

    private void initRole() {
        roleRepository.saveAll(
                List.of(RoleType.values()).stream().map(roleType -> new Role(roleType)).collect(Collectors.toList())
        );
    }

    private void initMember(){
        String googleLoginType = "google";
        String kakaoLoginType = "kakao";
        memberRepository.saveAll(
                List.of(new Member("member1", passwordEncoder.encode("1234"), "nickname", "male", "01012345678"
                                , List.of(roleRepository.findByRoleType(RoleType.ROlE_NORMAL).orElseThrow(RoleNotFoundException::new)),googleLoginType
                        ),
                        new Member("member2", passwordEncoder.encode("1234"), "nickname2", "female", "01012345678"
                                , List.of(roleRepository.findByRoleType(RoleType.ROLE_ARTIST).orElseThrow(RoleNotFoundException::new)),kakaoLoginType
                        ),
                        new Member("member3", passwordEncoder.encode("1234"), "nickname3", "none", "01012345678"
                                ,List.of(roleRepository.findByRoleType(RoleType.ROLE_ADMIN).orElseThrow(RoleNotFoundException::new)),kakaoLoginType
                        )

                )
        );
        //dummy member
        IntStream.range(0, 100)
                .forEach( i -> memberRepository.save(
                        new Member("member10"+i, passwordEncoder.encode("1234"),"nickname10"+i,
                "gender","01012345678", List.of(roleRepository.findByRoleType(RoleType.ROLE_ARTIST).orElseThrow(RoleNotFoundException::new)),kakaoLoginType))
        );
    };

    private void initDottoPost(){

        String tags = "??????, ??????, ??????";

        dottoPostRepository.saveAll(
                List.of(new DottoPost(memberRepository.findById("member1").orElseThrow(MemberNotFoundException::new),
                                "title1","content1",10000,9000,'Y',"??????????????????",30,tags,"10%", List.of()),
                        new DottoPost(memberRepository.findById("member2").orElseThrow(MemberNotFoundException::new),
                                "title2","content2",20000, 18000, 'N',"????????????",180,tags,"",List.of()),
                        new DottoPost(memberRepository.findById("member3").orElseThrow(MemberNotFoundException::new),
                                "title3","content3",30000,27000,'N',"????????????",270,"","",List.of())

                        )
                );

        //dummy post
        IntStream.range(0, 100)
                .forEach(i-> dottoPostRepository.save(
                        new DottoPost(memberRepository.findById("member10"+i).orElseThrow(MemberNotFoundException::new),
                                "title10"+i,"content10"+i, 10000,9000,'Y',"?????????", i, "??????"+i+", ??????"+i+", ??????"+i,"10%", List.of())));
    };
}
