package com.dotto.app.entity.member;

import com.dotto.app.entity.common.EntityDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedEntityGraph(  //연관된 엔티티들을 함께 조회 (roles)
    name = "Member.roles",
        attributeNodes = @NamedAttributeNode(value = "roles", subgraph = "Member.roles.role"),

        subgraphs = @NamedSubgraph(name = "Member.roles.role", attributeNodes = @NamedAttributeNode("role"))
)
public class Member extends EntityDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberNo")
    private Long memberNo;

    @Column(nullable = false, unique = true)
    private String id;

    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    private String gender;

    @Column(nullable = false)
    private String phone;

    private String intro;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberRole> roles;


    public Member(String id, String password, String nickname, String gender, String phone, List<Role> roles){
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.phone = phone;
        this.roles = roles.stream().map(r -> new MemberRole(this,r )).collect(Collectors.toSet());
    }

    public void updateNickName(String nickname){
        this.nickname = nickname;
    }

    public void updateIntro(String intro){this.intro = intro;}
}
