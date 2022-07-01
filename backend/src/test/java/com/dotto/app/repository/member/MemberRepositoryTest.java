package com.dotto.app.repository.member;

import com.dotto.app.entity.member.Member;
import com.dotto.app.entity.member.MemberRole;
import com.dotto.app.entity.member.Role;
import com.dotto.app.entity.member.RoleType;
import com.dotto.app.exception.MemberNotFoundException;
import com.dotto.app.repository.role.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.dotto.app.factory.entity.MemberFactory.createMember;
import static com.dotto.app.factory.entity.MemberFactory.createMemberWithRoles;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @Autowired
    RoleRepository roleRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    void createAndReadTest(){
        //given
        Member member = createMember();

        //when
        memberRepository.save(member);
        clear();

        //then
        Member foundMember = memberRepository.findById(member.getMemberNo()).orElseThrow(MemberNotFoundException::new);
        assertThat(foundMember.getMemberNo()).isEqualTo(member.getMemberNo());
    }

    @Test
    void memberDateTest(){
        //given
        Member member = createMember();

        //when
        memberRepository.save(member);
        clear();

        //then
        Member foundMember = memberRepository.findById(member.getMemberNo()).orElseThrow(MemberNotFoundException::new);
        assertThat(foundMember.getCreatedAt()).isNotNull();
        assertThat(foundMember.getModifiedAt()).isNotNull();
        assertThat(foundMember.getCreatedAt()).isEqualTo(foundMember.getModifiedAt());
    }

    @Test
    void updateNicknameAndIntroTest(){
        //given
        String updatedNickname = "uptNick";
        String updatedIntro = "uptIntro";
        Member member = memberRepository.save(createMember());
        clear();

        //when
        Member foundMember = memberRepository.findById(member.getMemberNo()).orElseThrow(MemberNotFoundException::new);
        foundMember.updateNickName(updatedNickname);
        foundMember.updateIntro(updatedIntro);
        clear();

        //then
        Member updatedMember = memberRepository.findById(member.getMemberNo()).orElseThrow(MemberNotFoundException::new);
        assertThat(updatedMember.getNickname()).isEqualTo(updatedNickname);
        assertThat(updatedMember.getIntro()).isEqualTo(updatedIntro);

    }

    @Test
    void deleteTest(){
        //given
        Member member = memberRepository.save(createMember());
        clear();

        //when
        memberRepository.delete(member);
        clear();

        //then
        assertThatThrownBy(() -> memberRepository.findById(member.getMemberNo()).orElseThrow(MemberNotFoundException::new))
                .isInstanceOf(MemberNotFoundException.class);
    }

    @Test
    void findByMemberIdTest(){
        //given
        Member member = memberRepository.save(createMember());
        clear();

        //when
        Member foundMember = memberRepository.findById(member.getMemberNo()).orElseThrow(MemberNotFoundException::new);

        //then
        assertThat(foundMember.getMemberNo()).isEqualTo(member.getMemberNo());
    }

    @Test
    void findByNicknameTest(){
        //given
        Member member = memberRepository.save(createMember());
        clear();
        //when
        Member foundMember = memberRepository.findById(member.getMemberNo()).orElseThrow(MemberNotFoundException::new);

        //then
        assertThat(foundMember.getNickname()).isEqualTo(member.getNickname());
    }

    @Test
    void uniqueMemberIdTest(){
        //given
        Member member = memberRepository.save(createMember("member1@email.com","qwe123!","nickname","gender","01012345678", null));
        clear();

        //when
        assertThatThrownBy(() -> memberRepository.save(createMember(member.getId(),"qwe123!2","nickname2","gender2","01112345678", null)))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void uniqueNicknameTest(){
        //given
        Member member = memberRepository.save(createMember("member1","qwe123!","nickname","gender","01012345678",null));
        clear();

        //when
        assertThatThrownBy(() -> memberRepository.save(createMember("member1","qwe123!", member.getNickname(),"gender","01012345678", null)))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void existsMemberEmailTest(){
        //given
        Member member = memberRepository.save(createMember());

        //when, then
        assertThat(memberRepository.existsById(member.getId())).isTrue();
        assertThat(memberRepository.existsById(member.getId()+"test")).isFalse();

    }

    @Test
    void existsNicknameTest(){
        //given
        Member member = memberRepository.save(createMember());

        //when, then
        assertThat(memberRepository.existsByNickname(member.getNickname())).isTrue();
        assertThat(memberRepository.existsByNickname(member.getNickname()+"test")).isFalse();
    }

    @Test
    void memberRoleCascadePersistTest(){
        //given
        List<RoleType> roleTypes = List.of(RoleType.ROlE_NORMAL, RoleType.ROLE_ARTIST, RoleType.ROLE_ADMIN);
        List<Role> roles = roleTypes.stream().map(roleType -> new Role(roleType)).collect(Collectors.toList());
        roleRepository.saveAll(roles);
        clear();

        Member member = memberRepository.save(createMemberWithRoles(roleRepository.findAll()));
        clear();

        //when
        Member foundMember = memberRepository.findById(member.getMemberNo()).orElseThrow(MemberNotFoundException::new);
        Set<MemberRole> memberRoles = foundMember.getRoles();

        //then
        assertThat(memberRoles.size()).isEqualTo(roles.size());
    }

    @Test
    void memberRoleCascadeDeleteTest(){
        //given
        List<RoleType> roleTypes = List.of(RoleType.ROlE_NORMAL, RoleType.ROLE_ARTIST, RoleType.ROLE_ADMIN);
        List<Role> roles = roleTypes.stream().map(roleType -> new Role(roleType)).collect(Collectors.toList());
        roleRepository.saveAll(roles);
        clear();

        Member member = memberRepository.save(createMemberWithRoles(roleRepository.findAll()));
        clear();

        //when
        memberRepository.deleteById(member.getMemberNo());
        clear();

        //then
        List<MemberRole> rs = em.createQuery("select mr from MemberRole  mr",MemberRole.class).getResultList();
        assertThat(rs.size()).isZero();
    }

    @Test
    void findWithRolesByEmailTest(){
        //given
        List<RoleType> roleTypes = List.of(RoleType.ROlE_NORMAL, RoleType.ROLE_ARTIST, RoleType.ROLE_ADMIN);
        List<Role> roles = roleTypes.stream().map(roleType -> new Role(roleType)).collect(Collectors.toList());
        roleRepository.saveAll(roles);
        clear();

        Member member = memberRepository.save(createMemberWithRoles(roleRepository.findAll()));
        clear();

        //when
        Member foundMember = memberRepository.findWithRolesById(member.getId()).orElseThrow(MemberNotFoundException::new);

        //then
        List<RoleType> rs = foundMember.getRoles().stream().map(memberRole -> memberRole.getRole().getRoleType()).collect(Collectors.toList());
        assertThat(rs.size()).isEqualTo(roleTypes.size());
        assertThat(rs).contains(roleTypes.get(0),roleTypes.get(1),roleTypes.get(2));
    }


    private void clear(){
        em.flush();
        em.clear();
    }

}