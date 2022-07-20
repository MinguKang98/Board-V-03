package com.study.boardv03.controller;

import com.study.boardv03.controller.dto.BoardModifyDto;
import com.study.boardv03.controller.dto.BoardWriteDto;
import com.study.boardv03.controller.dto.FileDto;
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
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Value("${file.download-location}")
    private String DOWNLOAD_DIR;

    /**
     * @param curPage        : 현재 페이지
     * @param searchCriteria : 검색 조건
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String boardListView(@RequestParam(value = "curPage", defaultValue = "1") int curPage,
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

    /**
     * @param boardId        : Baord의 boardId
     * @param curPage        : 목록에서의 현재 페이지
     * @param searchCriteria : 목록에서의 검색 조건
     * @param model
     * @return
     */
    @RequestMapping(value = "/view/{boardId}", method = RequestMethod.GET)
    public String boardView(@PathVariable("boardId") int boardId,
                            @RequestParam(value = "curPage", defaultValue = "1") int curPage,
                            @ModelAttribute SearchCriteria searchCriteria,
                            Model model) {

        Board board = boardService.getBoardById(boardId);
        Category category = categoryService.getCategoryById(board.getCategoryId());
        List<Comment> commentList = commentService.getCommentListByBoardId(boardId);
        List<File> fileList = fileService.getFileListByBoardId(boardId);

        boardService.updateVisitCount(boardId);

        model.addAttribute("searchCriteria", searchCriteria);
        model.addAttribute("curPage", curPage);
        model.addAttribute("board", board);
        model.addAttribute("category", category);
        model.addAttribute("commentList", commentList);
        model.addAttribute("fileList", fileList);

        return "view";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String boardWriteView(Model model) {

        List<Category> categoryList = categoryService.getCategoryList();

        model.addAttribute("form", new BoardWriteDto());
        model.addAttribute("categoryList", categoryList);

        return "write";
    }

    /**
     * @param fileDto       : 업로드한 파일들이 담긴 객체
     * @param boardWriteDto : Board 등록을 위해 입력한 필드들이 들어간 객체
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String boardSave(FileDto fileDto, BoardWriteDto boardWriteDto, Model model) throws IOException {

        Board board = boardWriteDto.toBoard();
        board.setFileExist(fileDto.isFileExist());

        List<MultipartFile> multipartFileList = fileDto.getFileList();
        List<File> fileList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFileList) {
            if (!multipartFile.isEmpty()) {
                String originFileName = multipartFile.getOriginalFilename();
                String fileExtension = FilenameUtils.getExtension(originFileName).toLowerCase();
                String systemFileName = UUID.randomUUID().toString() + "." + fileExtension;

                // 업로드
                java.io.File uploadFile = new java.io.File(DOWNLOAD_DIR + "/" + systemFileName);
                multipartFile.transferTo(uploadFile);

                File file = new File();
                file.setOriginName(originFileName);
                file.setSystemName(systemFileName);
                fileList.add(file);
            }
        }

        boardService.addBoard(board, fileList);

        return "redirect:/board/list";
    }

    /**
     * @param boardId        : Baord의 boardId
     * @param curPage        : 목록에서의 현재 페이지
     * @param searchCriteria : 목록에서의 검색 조건
     * @param model
     * @return
     */
    @RequestMapping(value = "/modify/{boardId}", method = RequestMethod.GET)
    public String boardModifyView(@PathVariable("boardId") int boardId,
                                  @RequestParam(value = "curPage", defaultValue = "1") int curPage,
                                  @ModelAttribute SearchCriteria searchCriteria,
                                  Model model) {

        Board board = boardService.getBoardById(boardId);
        Category category = categoryService.getCategoryById(board.getCategoryId());
        List<File> fileList = fileService.getFileListByBoardId(boardId);

        model.addAttribute("form", new BoardModifyDto());
        model.addAttribute("searchCriteria", searchCriteria);
        model.addAttribute("curPage", curPage);
        model.addAttribute("board", board);
        model.addAttribute("category", category);
        model.addAttribute("fileList", fileList);

        return "modify";
    }

}
