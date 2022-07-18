package com.study.boardv03.repository;

import com.study.boardv03.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * DB에 접근하여 Board와 관련된 작업을 하는 interface
 */

@Mapper
@Repository
public interface BoardRepository {

    /**
     * 모든 Board를 List 형태로 select
     * @return : Board의 모든 값을 가지는 List
     */
    List<Board> getBoardList();

    /**
     * 입력받은 boardId를 가지는 Board select
     * @param boardId : select할 Board 의 boardId
     * @return 해당 bordId를 가지는 Board 인스턴스
     */
    Board getBoardById(int boardId);

    /**
     * 검색 조건에 해당하는 Board의 총 갯수를 select
     * @param searchCriteriaMap : 검색 조건을 가지는 hashmap
     * @return 검색 조건에 해당하는 Board의 총 갯수
     */
    int getTotalBoardCountBySearchCriteria(HashMap<String, Object> searchCriteriaMap);

    /**
     * 검색 조건과 페이징 조건에 해당하는 Board를 List 형태로 select
     * @param searchAndPagingCriteriaMap : 검색 조건과 페이징 조건을 가지는 hashmap
     * @return 검색 조건과 페이징 조건에 해당하는 Board의 List
     */
    List<Board> getBoardListBySearchAndPagingCriteria(HashMap<String, Object> searchAndPagingCriteriaMap);

    /**
     * 입력받은 Board insert, board는 boardId 값 가지고 있지 않음
     * @param board : insert 할 Board 인스턴스
     * @return 추가된 Board의 boardId
     */
    int addBoard(Board board);

    /**
     * 입력받은 boardId를 가지는 Board delete
     * @param boardId : 삭제할 Board의 boardId
     */
    void deleteBoard(int boardId);

    /**
     * 입력받은 Board update, board는 boardId 값 가지고 있어야 함
     * @param board : update 할 Board 인스턴스
     */
    void updateBoard(Board board);

    /**
     * 입력받은 boardId를 가지는 Board의 commentCount 1 증가
     * @param boardId : update 할 Board의 boardId
     */
    void updateCommentCount(int boardId);

    /**
     * 입력받은 boardId를 가지는 Board의 visitCount 1 증가
     * @param boardId : update 할 Board의 boardId
     */
    void updateVisitCount(int boardId);


}
