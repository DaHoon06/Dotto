package com.dotto.app.factory.entity;

import com.dotto.app.entity.post.Image;

public class ImageFactory {
    public static Image createImage(){
        return new Image("Origin_fileName.jpg");
    }

    public static Image createImageWithOriginName(String originName){
        return new Image(originName);
    }
}
