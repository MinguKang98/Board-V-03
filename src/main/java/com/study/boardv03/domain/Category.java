package com.study.boardv03.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

/**
 * 게시글의 카테고리를 나타내는 클래스
 */

@Getter
@Setter
@Alias(value = "Category")
public class Category {

    private int categoryId;
    private String name;

}
