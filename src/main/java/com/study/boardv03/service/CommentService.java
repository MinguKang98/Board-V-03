package com.study.boardv03.service;

import com.study.boardv03.domain.Comment;
import com.study.boardv03.repository.BoardRepository;
import com.study.boardv03.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Comment와 관련된 로직을 처리하는 클래스
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    /**
     * 입력받은 boardId를 가지는 모든 Comment의 List를 return 한다.
     *
     * @param boardId : 찾을 Comment의 boardId
     * @return 해당 boardId를 가지는 모든 Comment의 List
     */
    public List<Comment> getCommentListByBoardId(int boardId) {

        List<Comment> commentList = commentRepository.getCommentListByBoardId(boardId);
        return commentList;
    }

    /**
     * 입력받은 Comment를 추가하고 추가된 Comment의 commentId를 return 한다.
     *
     * @param comment : 추가할 Comment 인스턴스
     * @return 추가된 Comment의 commentId
     */
    @Transactional
    public int addComment(Comment comment) {

        commentRepository.addComment(comment);

        boardRepository.updateCommentCount(comment.getBoardId());

        return comment.getCommentId();
    }

}
