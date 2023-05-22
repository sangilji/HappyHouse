package com.ssafy.myhouse.vo.houseVo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
    private String reviewId;
    private String memberId;
    private String createdDate;
    private String content;
    private String residenceType; //남향 북향?
    private String residenceYear; //살았는 기간
    private String residenceFloor; //층수
    private String stars; //평점
    private String likes;
    private String ulikes;
    private String aptCode;

}
