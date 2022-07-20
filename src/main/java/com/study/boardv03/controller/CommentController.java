package com.study.boardv03.controller;

import com.study.boardv03.criteria.SearchCriteria;
import com.study.boardv03.domain.Comment;
import com.study.boardv03.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     *
     * @return
     */
    @RequestMapping(value = "/save/{boardId}", method = RequestMethod.POST)
    public String commentSave(@PathVariable("boardId") int boardId,
                              @RequestParam(value = "commentContent") String commentContent,
                              @RequestParam(value = "curPage", defaultValue = "1") int curPage,
                              @ModelAttribute SearchCriteria searchCriteria,
                              RedirectAttributes redirectAttributes) {

        Comment comment = new Comment();
        comment.setContent(commentContent);
        comment.setBoardId(boardId);
        commentService.addComment(comment);

        redirectAttributes.addAttribute("curPage", curPage);
        redirectAttributes.addAttribute("createdDateFrom", searchCriteria.getCreatedDateFrom());
        redirectAttributes.addAttribute("createdDateTo", searchCriteria.getCreatedDateTo());
        redirectAttributes.addAttribute("categoryId", searchCriteria.getCategoryId());
        redirectAttributes.addAttribute("text", searchCriteria.getText());

        return "redirect:/board/view/" + boardId;
    }
}
