package com.study.boardv03.controller;

import com.study.boardv03.controller.dto.BoardWriteDto;
import com.study.boardv03.criteria.PagingCriteria;
import com.study.boardv03.criteria.SearchCriteria;
import com.study.boardv03.domain.Board;
import com.study.boardv03.domain.Category;
import com.study.boardv03.domain.Comment;
import com.study.boardv03.domain.File;
import com.study.boardv03.service.BoardService;
import com.study.boardv03.service.CategoryService;
import com.study.boardv03.service.CommentService;
import com.study.boardv03.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * /board 로 시작하는 요청을 처리하는 클래스
 */

@Controller
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CategoryService categoryService;
    private final CommentService commentService;
    private final FileService fileService;

    /**
     *
     * @param curPage : 현재 페이지
     * @param searchCriteria : 검색 조건
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String boardList( @RequestParam(value = "curPage", defaultValue = "1") int curPage,
                             @ModelAttribute SearchCriteria searchCriteria,
                             Model model ) {

        int totalBoardCount = boardService.getTotalBoardCountBySearchCriteria(searchCriteria);
        PagingCriteria pagingCriteria = new PagingCriteria(curPage, totalBoardCount);

        List<Board> boardList = boardService.getBoardListBySearchCriteriaAndPagingCriteria(
                searchCriteria, pagingCriteria);
        List<Category> categoryList = categoryService.getCategoryList();

        model.addAttribute("searchCriteria", searchCriteria);
        model.addAttribute("pagingCriteria", pagingCriteria);
        model.addAttribute("totalBoardCount", totalBoardCount);
        model.addAttribute("boardList", boardList);
        model.addAttribute("categoryList", categoryList);

        return "list";
    }

    /**
     *
     *
     * @param boardId : Baord의 boardId
     * @param curPage : 목록에서의 현재 페이지
     * @param searchCriteria : 목록에서의 검색 조건
     * @param model
     * @return
     */
    @RequestMapping(value = "/view/{boardId}", method = RequestMethod.GET)
    public String boardView( @PathVariable("boardId") int boardId,
                             @RequestParam(value = "curPage", defaultValue = "1") int curPage,
                             @ModelAttribute SearchCriteria searchCriteria,
                             Model model) {

        Board board = boardService.getBoardById(boardId);
        Category category = categoryService.getCategoryById(board.getCategoryId());
        List<Comment> commentList = commentService.getCommentListByBoardId(boardId);
        List<File> fileList = fileService.getFileListByBoardId(boardId);

        model.addAttribute("searchCriteria", searchCriteria);
        model.addAttribute("curPage", curPage);
        model.addAttribute("board", board);
        model.addAttribute("category", category);
        model.addAttribute("commentList", commentList);
        model.addAttribute("fileList", fileList);

        return "view";
    }

    //TODO 게시글 작성

    //TODO 게시글-작성 처리

    //TODO 게시글-수정

    //TODO 게시글-삭제
}
