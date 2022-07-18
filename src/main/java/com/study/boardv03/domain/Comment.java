package com.study.boardv03.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * 게시글의 댓글을 나타내는 클래스
 */

@Getter
@Setter
@Alias(value = "Comment")
public class Comment {

    private int commentId;
    private String createdDate;
    private String content;
    private int boardId;
}
