package com.study.boardv03.service;

import com.study.boardv03.criteria.PagingCriteria;
import com.study.boardv03.criteria.SearchCriteria;
import com.study.boardv03.domain.Board;
import com.study.boardv03.domain.File;
import com.study.boardv03.repository.BoardRepository;
import com.study.boardv03.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Board와 관련된 로직을 처리하는 클래스
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;

    /**
     * 검색 조건에 해당하는 Board의 총 갯수를 return 한다.
     *
     * @param searchCriteria : 검색 조건
     * @return 검색 조건을 만족하는 Board의 총 갯수
     */
    public int getTotalBoardCountBySearchCriteria(SearchCriteria searchCriteria) {

        HashMap<String, Object> searchCriteriaMap = searchCriteria.getSearchCriteriaMap();
        int totalBoardCountBySearchCriteria =
                boardRepository.getTotalBoardCountBySearchCriteria(searchCriteriaMap);
        return totalBoardCountBySearchCriteria;
    }

    /**
     * 검색 조건과 페이징 조건을 만족하는 Board들을 포함하는 List를 return 한다.
     *
     * @param searchCriteria : 검색 조건
     * @param pagingCriteria : 페이징 조건
     * @return 검색 조건과 페이징 조건을 만족하는 Board들을 포함하는 List
     */
    public List<Board> getBoardListBySearchCriteriaAndPagingCriteria(SearchCriteria searchCriteria,
                                                                     PagingCriteria pagingCriteria) {

        HashMap<String, Object> searchCriteriaMap = searchCriteria.getSearchCriteriaMap();
        HashMap<String, Object> pagingCriteriaMap = pagingCriteria.getPagingCriteriaMap();

        pagingCriteriaMap.forEach((key, value) -> searchCriteriaMap.merge(key, value, (v1, v2) -> v2));

        List<Board> boardList = boardRepository.getBoardListBySearchAndPagingCriteria(searchCriteriaMap);

        return boardList;
    }

    /**
     * 입력받은 boardId를 가지는 Board를 return 한다.
     *
     * @param boardId : return할 Board의 boardId
     * @return 해당 boardId를 가지는 Board의 인스턴스
     */
    public Board getBoardById(int boardId) {

        Board board = boardRepository.getBoardById(boardId);
        return board;
    }

    /**
     * Board와 File들을 추가한다.
     *
     * @param board : 추가할 Board
     * @param fileList : 추가할 File의 List
     */
    @Transactional
    public void addBoard(Board board, List<File> fileList) {
        // insert board
        boardRepository.addBoard(board);
        int boardId = board.getBoardId();

        // insert file
        for (File file : fileList) {
            file.setBoardId(boardId);
            fileRepository.addFile(file);
        }

    }

    /**
     * 입력받은 boardId를 가지는 Baord를 삭제한다.
     *
     * @param boardId : 삭제할 Board의 boardId
     */
    @Transactional
    public void deleteBoard(int boardId) {

        boardRepository.deleteBoard(boardId);
    }

    //TODO updateBoard

    /**
     * 입력받은 boardId를 가지는 Board의 visitCount를 1 중가시킨다.
     *
     * @param boardId : update할 Board의 boardId
     */
    @Transactional
    public void updateVisitCount(int boardId) {

        boardRepository.updateVisitCount(boardId);
    }

}
