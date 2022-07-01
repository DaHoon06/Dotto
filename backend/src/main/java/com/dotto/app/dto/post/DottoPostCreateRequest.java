package com.dotto.app.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "게시글 작성 요청")
@Data
@NoArgsConstructor
public class DottoPostCreateRequest {

    @ApiModelProperty(value = "제목", notes = "게시글 제목을 입력해 주세요", required = true, example = "title")
    @NotBlank
    private String title;

    @ApiModelProperty(value = "내용", notes = "게시글 내용을 입력해 주세요", required = true, example = "content")
    @NotBlank
    private String content;

    @ApiModelProperty(value = "가격", notes = "가격을 입력해 주세요", required = true, example = "10000")
    @NotBlank
    private int price;

    @ApiModelProperty(value = "할인 가격", notes = "할인 가격을 입력해 주세요", example = "9000")
    private int salesPrice;

    @ApiModelProperty(value = "할인 여부", notes = "할인 여부를 입력해 주세요", required = true, example = "N")
    @NotBlank
    private char salesYn;

    @ApiModelProperty(value = "장르", notes = "장르를 입력해 주세요", required = true, example = "레터링")
    @NotBlank
    private String genre;

    @ApiModelProperty(value = "소요시간", notes = "소요시간을 입력해 주세요", required = true, example = "30")
    @NotBlank
    private int totalTime;

    @ApiModelProperty(value = "이미지", notes = "게시글 이미지를 입력해 주세요")
    private List<MultipartFile> postPhoto = new ArrayList<>();

    @ApiModelProperty(value = "태그", notes = "태그를 입력해 주세요", example = "#멋져, #훈남")
    private String[] tags;

    @ApiModelProperty(hidden = true)
    @Null
    private Long memberNo;


}
