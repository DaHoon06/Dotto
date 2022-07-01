package com.dotto.app.repository.post;

import com.dotto.app.entity.member.Member;
import com.dotto.app.entity.post.DottoPost;
import com.dotto.app.entity.post.Image;
import com.dotto.app.exception.PostNotFoundException;
import com.dotto.app.repository.member.MemberRepository;
import com.dotto.app.repository.role.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.dotto.app.factory.entity.DottoPostFactory.createDottoPost;
import static com.dotto.app.factory.entity.DottoPostFactory.createDottoPostWithImages;
import static com.dotto.app.factory.entity.ImageFactory.createImage;
import static com.dotto.app.factory.entity.MemberFactory.createMember;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class DottoPostRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DottoPostRepository dottoPostRepository;

    @Autowired
    ImageRepository imageRepository;

    @PersistenceContext
    EntityManager em;

    Member member;

    @BeforeEach
    void beforeEach(){
        member = memberRepository.save(createMember());
    }


    @Test
    void createAndReadTest() {
        //given
        DottoPost post = dottoPostRepository.save(createDottoPost(member));
        clear();

        //when
        DottoPost foundPost = dottoPostRepository.findByIdWithMemberAndDeletedN(post.getPostNo()).orElseThrow(PostNotFoundException::new);

        //then
        assertThat(post.getPostNo()).isEqualTo(foundPost.getPostNo());
        assertThat(post.getTitle()).isEqualTo(foundPost.getTitle());

    }

    @Test
    void deleteTest() {
        //given
        DottoPost post = dottoPostRepository.save(createDottoPost(member));
        clear();
        //when
        dottoPostRepository.deleted(post.getPostNo());
        clear();

        //then
        DottoPost deletePost = dottoPostRepository.findByIdWithMemberAndDeletedY(post.getPostNo()).orElseThrow(PostNotFoundException::new);
        assertThat(post.getPostNo()).isEqualTo(deletePost.getPostNo());
        assertThat(post.getDeletedYn()).isNotEqualTo(deletePost.getDeletedYn());
        assertThat(post.getDeletedYn()).isEqualTo('N');
        assertThat(deletePost.getDeletedYn()).isEqualTo('Y');
    }

    @Test
    void deletedCascadeByMemberTest(){
        //given
        dottoPostRepository.save(createDottoPost(member));
        clear();

        //when
        memberRepository.deleteById(member.getMemberNo());
        clear();

        //then
        List<DottoPost> result = dottoPostRepository.findAll();
        assertThat(result.size()).isZero();
    }

    @Test
    void findByIdWithMember(){
        //given
        DottoPost post = dottoPostRepository.save(createDottoPost(member));
        clear();

        //when
        DottoPost foundPost = dottoPostRepository.findByIdWithMember(post.getPostNo()).orElseThrow(PostNotFoundException::new);

        //then
        Member foundMember = foundPost.getMember();
        assertThat(foundMember.getId()).isEqualTo(member.getId());
    }

    @Test
    void createCascadeImageTest(){
        //given
        DottoPost post = dottoPostRepository.save(createDottoPostWithImages(member,List.of(createImage(),createImage())));
        clear();

        //when
        DottoPost foundPost = dottoPostRepository.findById(post.getPostNo()).orElseThrow(PostNotFoundException::new);

        //then
        List<Image> images = foundPost.getImages();
        assertThat(images.size()).isEqualTo(2);
    }

    //cascade 실제 삭제 확인
    @Test
    void deletedCascadeImageTest(){
        //given
        DottoPost post = dottoPostRepository.save(createDottoPostWithImages(member,List.of(createImage(),createImage())));
        clear();

        //when
        dottoPostRepository.deleteById(post.getPostNo());
        clear();

        //then
        List<Image> images = imageRepository.findAll();
        assertThat(images.size()).isZero();
    }


    void clear(){
        em.flush();
        em.clear();
    }

}
