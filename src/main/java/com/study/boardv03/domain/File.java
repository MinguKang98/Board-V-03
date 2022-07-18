package com.study.boardv03.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * 게시글의 첨부 파일을 나타내는 클래스
 */

@Getter
@Setter
@Alias(value = "File")
public class File {

    int fileId;
    String originName;
    String systemName;
    int boardId;

}
