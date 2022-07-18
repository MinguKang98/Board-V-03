package com.study.boardv03.criteria;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * Board의 검색 조건을 가지는 클래스
 */

@Getter
@Setter
public class SearchCriteria {

    private String createdDateFrom; // 등록일
    private String createdDateTo; // 등록일
    private Integer categoryId; // 카테고리
    private String text; // 제목 or 작성자 or 텍스트

    /**
     *
     * @return 검색 조건을 가지는 hashmap
     */
    public HashMap<String, Object> getSearchCriteriaMap() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("createdDateFrom", this.getCreatedDateFrom());
        map.put("createdDateTo", this.getCreatedDateTo());
        map.put("categoryId", this.getCategoryId());
        map.put("text", this.getText());

        return map;
    }

    /**
     *
     * @return 검색 조건의 query string
     */
    public String getSearchCriteriaQueryString() {

        StringBuffer sb = new StringBuffer();
        sb.append("createdDateFrom=");
        if (this.getCreatedDateFrom() != null) {
            sb.append(this.getCreatedDateFrom());
        }
        sb.append("&createdDateTo=");
        if (this.getCreatedDateTo() != null) {
            sb.append(this.getCreatedDateTo());
        }
        sb.append("&categoryId=");
        if (this.getCategoryId() != null) {
            sb.append(this.getCategoryId());
        }
        sb.append("&text=");
        if (this.getText() != null) {
            sb.append(this.getText());
        }

        return sb.toString();
    }

}
