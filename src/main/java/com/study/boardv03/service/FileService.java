package com.study.boardv03.service;

import com.study.boardv03.domain.File;
import com.study.boardv03.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * File과 관련된 로직을 처리하는 클래스
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    /**
     * 입력받은 boardId를 가지는 모든 File을 List 형태로 return 한다.
     *
     * @param boardId : select할 File의 boardId
     * @return 해당 boardId를 가지는 모든 File의 List
     */
    public List<File> getFileListByBoardId(int boardId) {

        List<File> fileList = fileRepository.getFileListByBoardId(boardId);
        return fileList;
    }

    /**
     * 입력받은 fileId를 가지는 File을 return 한다.
     *
     * @param fileId : select할 File의 fileId
     * @return 해당 fileId를 가지는 File 인스턴스
     */
    public File getFileById(int fileId) {

        File file = fileRepository.getFileById(fileId);
        return file;
    }

    //TODO 파일 add

    //TODO 파일 delete

}
