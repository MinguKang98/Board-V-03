package com.study.boardv03.controller;

import com.study.boardv03.domain.File;
import com.study.boardv03.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * /file 로 시작하는 요청을 처리하는 클래스
 */

@Controller
@RequestMapping(value = "/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @Value("${file.download-location}")
    private String DOWNLOAD_DIR;
    private String separator = java.io.File.separator;

    /**
     * 파일을 다운로드 한다.
     *
     * @param fileId : 다운로드 할 File의 fileId
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/download/{fileId}",method = RequestMethod.GET)
    public void fileDownload(@PathVariable("fileId") int fileId,
                             HttpServletResponse response)throws IOException {

        File file = fileService.getFileById(fileId);

        String fileName = new String(file.getOriginName().getBytes(StandardCharsets.UTF_8), "iso-8859-1");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        java.io.File downloadFile = new java.io.File(DOWNLOAD_DIR + separator + file.getSystemName());
        FileInputStream fileInputStream = new FileInputStream(downloadFile);
        OutputStream out = response.getOutputStream();

        int read = 0;
        byte[] buffer = new byte[1024];
        while ((read = fileInputStream.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

}
