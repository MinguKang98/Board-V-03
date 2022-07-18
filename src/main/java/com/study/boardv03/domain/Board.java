package com.study.boardv03.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * 게시글을 나타내는 클래스
 */

@Getter
@Setter
@Alias(value = "Board")
public class Board {

    private int boardId;
    private String createdDate;
    private String updatedDate;
    private String user;
    private String password;
    private String title;
    private String content;
    private int visitCount;
    private int commentCount;
    private Boolean fileExist;
    private int categoryId;

}
