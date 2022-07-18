package com.study.boardv03.repository;

import com.study.boardv03.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DB에 접근하여 Comment와 관련된 작업을 하는 interface
 */

@Mapper
@Repository
public interface CommentRepository {

    /**
     * 입력받은 boardId를 가지는 모든 Comment의 List를 return 한다.
     *
     * @param boardId : select할 Comment의 boardId
     * @return 해당 boardId를 가지는 모든 Comment의 List
     */
    List<Comment> getCommentListByBoardId(int boardId);

    /**
     * 입력받은 Comment를 DB에 추가하고 추가된 Comment의 commentId를 return 한다.
     *
     * @param comment : insert할 Comment 인스턴스
     * @return 추가된 Comment의 commentId
     */
    int addComment(Comment comment);

}
