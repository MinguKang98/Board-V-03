package com.study.boardv03.criteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

/**
 * Board의 페이징 조건을 가지는 클래스
 */

@Getter
@Setter
@NoArgsConstructor
public class PagingCriteria {

    // 입력 받음
    private int curPage = 1; // 현재 페이지 번호
    private int rowSizePerPage = 10; // 노출되는 게시글 갯수
    private int pageSize = 10; // 노출되는 페이지 수
    private int totalRowCount; // 총 게시글 갯수

    // 계산 해야함
    private int firstRow; // 노출된 첫 게시글
    private int lastRow; // 노출된 마지막 게시글
    private int totalPageCount; // 최종 페이지
    private int firstPage; // 현재 보이는 처음 페이지
    private int lastPage; // 현재 보이는 마지막 페이지

    public PagingCriteria(int curPage, int totalRowCount) {
        this.curPage = curPage;
        this.totalRowCount = totalRowCount;
        this.pageSetting();
    }

    /**
     * 초기화 되지 않은 field 계산
     */
    public void pageSetting() {
        totalPageCount = (totalRowCount - 1) / rowSizePerPage + 1;

        firstRow = (curPage - 1) * rowSizePerPage;
        lastRow = firstRow + rowSizePerPage - 1;
        if (lastRow > totalRowCount) lastRow = totalRowCount - 1;

        firstPage = (curPage - 1) / pageSize * pageSize + 1;
        lastPage = firstPage + pageSize - 1;
        if (lastPage > totalPageCount) lastPage = totalPageCount;
    }

    /**
     * 
     * @return 페이징 조건을 가지는 hashmap
     */
    public HashMap<String, Object> getPagingCriteriaMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("firstRow", this.getFirstRow());
        map.put("rowSizePerPage", this.getRowSizePerPage());
        return map;
    }

}
