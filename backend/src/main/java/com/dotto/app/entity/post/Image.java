package com.dotto.app.entity.post;

import com.dotto.app.exception.UnsupportedImageFormatException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgNo;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String originName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postNo",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DottoPost dottoPost;

    private final static String[] supportedExtension = {"jpg", "jpeg", "gif", "bmp", "png"};

    public Image(String originName){
        this.name = generateName(extractExtension(originName));
        this.originName = originName;
    }

    public void initDottoPost(DottoPost dottoPost){
        if(this.dottoPost == null) this.dottoPost = dottoPost;

    }

    private String generateName(String extension){
        return UUID.randomUUID().toString()+"."+extension;
    }

    private String extractExtension(String originName){
        try{
            String ext = originName.substring(originName.lastIndexOf(".")+1);
            if(isSupportedFormat(ext)) return ext;
        }catch (StringIndexOutOfBoundsException e){}
        throw new UnsupportedImageFormatException();
    }

    private boolean isSupportedFormat(String ext){
        return Arrays.stream(supportedExtension).anyMatch(e -> e.equalsIgnoreCase(ext));
    }
}
