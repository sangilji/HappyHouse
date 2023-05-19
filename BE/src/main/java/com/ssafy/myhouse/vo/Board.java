package com.ssafy.myhouse.vo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Board {
    private int announcementid;
    private String memberid;
    private String title;
    private String content;
    private int hit;
    private String regtime;
}



