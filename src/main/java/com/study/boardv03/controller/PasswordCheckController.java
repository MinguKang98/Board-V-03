package com.study.boardv03.controller;

import com.study.boardv03.criteria.SearchCriteria;
import com.study.boardv03.domain.Board;
import com.study.boardv03.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * /passwordCheck 로 시작하는 요청을 처리하는 클래스
 */

@Service
@RequestMapping(value = "/passwordCheck")
@RequiredArgsConstructor
public class PasswordCheckController {

    private final BoardService boardService;

    /**
     * @param boardId : 비밀번호를 확인할 Board의 boardId
     * @param curPage : 목록에서의 현재 페이지
     * @param searchCriteria : 목록에서의 검색 조건
     * @param model
     * @return
     */
    @RequestMapping(value = "/{boardId}", method = RequestMethod.GET)
    public String passwordCheckView(@PathVariable("boardId") int boardId,
                                    @RequestParam(value = "type") String type,
                                    @RequestParam(value = "curPage", defaultValue = "1") int curPage,
                                    @ModelAttribute SearchCriteria searchCriteria,
                                    Model model) {

        model.addAttribute("type", type);
        model.addAttribute("curPage", curPage);
        model.addAttribute("searchCriteria", searchCriteria);
        model.addAttribute("boardId", boardId);

        return "passwordCheck";
    }

    /**
     * @param boardId : 비밀번호를 확인할 Board의 boardId
     * @param curPage : 목록에서의 현재 페이지
     * @param searchCriteria : 목록에서의 검색 조건
     * @param password : 확인할 Board의 비밀번호
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/modify/{boardId}", method = RequestMethod.POST)
    public String passwordCheckModify(@PathVariable("boardId") int boardId,
                                      @RequestParam(value = "curPage", defaultValue = "1") int curPage,
                                      @ModelAttribute SearchCriteria searchCriteria,
                                      @RequestParam(value = "password") String password,
                                      RedirectAttributes redirectAttributes) {

        Board board = boardService.getBoardById(boardId);

        redirectAttributes.addAttribute("curPage", curPage);
        redirectAttributes.addAttribute("createdDateFrom", searchCriteria.getCreatedDateFrom());
        redirectAttributes.addAttribute("createdDateTo", searchCriteria.getCreatedDateTo());
        redirectAttributes.addAttribute("categoryId", searchCriteria.getCategoryId());
        redirectAttributes.addAttribute("text", searchCriteria.getText());

        if (password.equals(board.getPassword())) {
            return "redirect:/board/modify/" + boardId;
        } else {
            redirectAttributes.addAttribute("type", "modify");
            return "redirect:/passwordCheck/" + boardId;
        }

    }

    /**
     * @param boardId : 비밀번호를 확인할 Board의 boardId
     * @param curPage : 목록에서의 현재 페이지
     * @param searchCriteria : 목록에서의 검색 조건
     * @param password : 확인할 Board의 비밀번호
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/delete/{boardId}", method = RequestMethod.POST)
    public String passwordCheckDelete(@PathVariable("boardId") int boardId,
                                      @RequestParam(value = "curPage", defaultValue = "1") int curPage,
                                      @ModelAttribute SearchCriteria searchCriteria,
                                      @RequestParam(value = "password") String password,
                                      RedirectAttributes redirectAttributes) {

        Board board = boardService.getBoardById(boardId);

        redirectAttributes.addAttribute("curPage", curPage);
        redirectAttributes.addAttribute("createdDateFrom", searchCriteria.getCreatedDateFrom());
        redirectAttributes.addAttribute("createdDateTo", searchCriteria.getCreatedDateTo());
        redirectAttributes.addAttribute("categoryId", searchCriteria.getCategoryId());
        redirectAttributes.addAttribute("text", searchCriteria.getText());

        if (password.equals(board.getPassword())) {
            boardService.deleteBoard(boardId);
            return "redirect:/board/list/";
        } else {
            redirectAttributes.addAttribute("type", "delete");
            return "redirect:/passwordCheck/" + boardId;
        }
    }

}
