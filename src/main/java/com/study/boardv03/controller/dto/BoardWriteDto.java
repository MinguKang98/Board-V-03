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

    public Board toBoard() {
        Board board = new Board();
        board.setUser(this.getUser());
        board.setPassword(this.getPassword());
        board.setTitle(this.getTitle());
        board.setContent(this.getContent());
        board.setCategoryId(this.getCategoryId());

        return board;
    }

}
