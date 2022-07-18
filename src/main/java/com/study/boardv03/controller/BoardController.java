package com.study.boardv03.controller;

import com.study.boardv03.criteria.PagingCriteria;
import com.study.boardv03.criteria.SearchCriteria;
import com.study.boardv03.domain.Board;
import com.study.boardv03.domain.Category;
import com.study.boardv03.service.BoardService;
import com.study.boardv03.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     *
     * @param curPage
     * @param searchCriteria
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String boardList( @RequestParam(value = "curPage", defaultValue = "1") int curPage,
                             @ModelAttribute SearchCriteria searchCriteria,
                             Model model) {

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

    //TODO 게시글-보기

    //TODO 게시글-작성

    //TODO 게시글-작성 처리

    //TODO 게시글-수정

    //TODO 게시글-삭제
}
