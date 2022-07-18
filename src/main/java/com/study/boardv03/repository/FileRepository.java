package com.study.boardv03.repository;

import com.study.boardv03.domain.File;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DB에 접근하여 File과 관련된 작업을 하는 interface
 */

@Mapper
@Repository
public interface FileRepository {

    /**
     * 입력받은 boardId를 가지는 모든 File의 List를 return 한다.
     *
     * @param boardId : select할 File의 boardId
     * @return 해당 boardId를 가지는 모든 File의list
     */
    List<File> getFileListByBoardId(int boardId);

    /**
     * 입력받은 fileId를 가지는 File을 return 한다.
     *
     * @param fileId : select할 File의 filedId
     * @return 해당 fileId를 가지는 File
     */
    File getFileById(int fileId);

    /**
     * 입력받은 File을 DB에 추가하고 추가된 File의 fileId를 return 한다.
     *
     * @param file : insert할 File 인스턴스
     * @return 추가된 File의 fileId
     */
    int addFile(File file);

    /**
     * 입력받은 fileId를 가지는 File을 DB에서 삭제한다.
     * 
     * @param fileId : delete할 File의 fileId
     */
    void deleteFile(int fileId);
}
