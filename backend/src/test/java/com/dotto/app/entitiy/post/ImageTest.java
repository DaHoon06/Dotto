package com.dotto.app.entitiy.post;

import com.dotto.app.entity.post.DottoPost;
import com.dotto.app.entity.post.Image;
import com.dotto.app.exception.UnsupportedImageFormatException;
import org.junit.jupiter.api.Test;

import static com.dotto.app.factory.entity.DottoPostFactory.createDottoPost;
import static com.dotto.app.factory.entity.ImageFactory.createImage;
import static com.dotto.app.factory.entity.ImageFactory.createImageWithOriginName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageTest {

    @Test
    void createImageTest(){
        //given
        String vaildExtention = ".JPEG";

        //when, then
        createImageWithOriginName("image"+vaildExtention);
    }

    @Test
    void createImageExceptionByUnsupportedFormatTest(){
        //given
        String invalidExtention = "invalid";

        //when, then
        assertThatThrownBy(() -> createImageWithOriginName("image"+ invalidExtention ))
                .isInstanceOf(UnsupportedImageFormatException.class);
    }

    @Test
    void createImageExceptionByNoneExtensionTest(){
        //given
        String originName = "image";

        //when, then
        assertThatThrownBy(() -> createImageWithOriginName(originName))
                .isInstanceOf(UnsupportedImageFormatException.class);
    }

    @Test
    void initDottoPostTest(){
        //given
        Image image = createImage();

        //when
        DottoPost post = createDottoPost();
        image.initDottoPost(post);

        //then
        assertThat(image.getDottoPost()).isSameAs(post);


    }


}
