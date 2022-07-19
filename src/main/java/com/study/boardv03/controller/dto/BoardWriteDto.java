package com.study.boardv03.controller.dto;

import com.study.boardv03.domain.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardWriteDto {

    private String user;
    private String password;
    private String title;
    private String content;
    private int categoryId;

    public static Board toBoard(BoardWriteDto boardWriteDto) {
        Board board = new Board();
        board.setUser(boardWriteDto.getUser());
        board.setPassword(boardWriteDto.getPassword());
        board.setTitle(boardWriteDto.getTitle());
        board.setContent(boardWriteDto.getContent());
        board.setCategoryId(boardWriteDto.getCategoryId());

        return board;
    }

}
