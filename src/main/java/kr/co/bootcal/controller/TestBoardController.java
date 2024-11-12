package kr.co.bootcal.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.AllArgsConstructor;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/tBoard/*")
public class TestBoardController {
    private static final String upload_Dir = "./uploads/";
    @PostMapping("/upload")
    @ResponseBody
    public String upLoadImage(@RequestParam("file")MultipartFile file, HttpServletRequest req) throws IllegalAccessError, IOException {
        //String contextRoot = req.getServletContext().getRealPath("/");
        //System.out.println(contextRoot);
        String fileRoot = "./uploads/"; // 상대 경로로 설정

        File dir = new File(fileRoot);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = UUID.randomUUID() + extension;

        File targetFile = new File(fileRoot + savedFileName);
        try {
            InputStream fileStream = file.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
        } catch (IOException e){
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }

        String a = savedFileName;
        System.out.println(savedFileName);
        return a;
    }
}
