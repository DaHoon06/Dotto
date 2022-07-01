package com.dotto.app.factory.entity;

import com.dotto.app.entity.member.Member;
import com.dotto.app.entity.post.DottoPost;
import com.dotto.app.entity.post.Image;

import java.util.List;

import static com.dotto.app.factory.entity.MemberFactory.createMember;

public class DottoPostFactory {

    public static DottoPost createDottoPost(){
        return createDottoPost(createMember());
    }

    public static DottoPost createDottoPost(Member member){

        return new DottoPost(member, "title","content",1000,900,'N',"블랙앤그레이",30, 'N', List.of());
    }

    public static DottoPost createDottoPostWithImages(Member member, List<Image> images){
        return new DottoPost(member, "title","content",10000,9000,'N',"레터링",3,'N',images);
    }

    public static DottoPost createDottoPostWithImages(List<Image> images){
        return new DottoPost(createMember(),"title","content",1000,900,'N',"genre",3,'N',images);
    }
}
