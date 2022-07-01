package com.dotto.app.factory.entity;

import com.dotto.app.entity.member.Member;
import com.dotto.app.entity.member.Role;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static java.util.Collections.emptyList;

public class MemberFactory {

    public static Member createMember(){
        return new Member("test","qwe123!","nickname","male","01012345678", emptyList());
    }

    public static Member createMember(String id, String password, String nickname, String gender, String phone, String intro){
        return new Member(id, password, nickname, gender, phone, emptyList());
    }

    public static Member createMemberWithRoles(List<Role> roles){
        return new Member("test","qwe123!","nickname","male","01012345678", roles);
    }

    public static Member createMemberWithNo(Long no){
        Member member = new Member("test","qwe123!","nickname","male","01012345678", emptyList());
        ReflectionTestUtils.setField(member, "no",no);
        return member;
    }
}
