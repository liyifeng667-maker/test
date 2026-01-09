/**
 * Copyright &copy; 2021-2026 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.file.controller;

import cn.hutool.core.util.StrUtil;
import com.jeeplus.common.utils.ResponseUtil;
import com.jeeplus.file.config.FileProperties;
import com.jeeplus.file.repository.AccessoryRepository;
import com.jeeplus.file.utils.FileUtils;
import com.jeeplus.sys.utils.AESUtil;
import com.jeeplus.sys.utils.FileKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;


/**
 * 文件管理Controller
 *
 * @author liugf
 * @version 2021-01-21
 * 提供了配套的upload/download, uploadFile/downloadFile
 * 区别在于访问文件的方式一个是通过重定向，一个是通过下载文件流
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileProperties fileProperties;

    @Autowired
    private AccessoryRepository accessoryRepository;

    private String FILE_SERVER = "/api/file";

    /**
     * 获取文件网络地址
     *
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @GetMapping("download")
    public void download(String uploadPath, String name, HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            uploadPath = AESUtil.decryptTokenStr(uploadPath);
            name = AESUtil.decryptTokenStr(name);
        } catch (Exception e) {
            throw new Exception("文件路径错误!");
        }
        String url = accessoryRepository.getURL(uploadPath, name, request, response);
        log.error("{文件下载地址}", url);
        if (StrUtil.isNotEmpty(url)) {
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                throw new Exception("文件不存在!");
            }
        }
    }

    /**
     * 上传文件
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("upload")
    public ResponseEntity upload(HttpServletRequest request, MultipartFile file) {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        String uploadPath = request.getParameter("uploadPath") + "/" + year + "/" + month + "/";
        if (!FileUtils.isFolder(uploadPath)) {
            log.info("创建文件夹" + uploadPath);
            FileUtils.createDirectory(uploadPath);
        }


        // 判断文件是否为空
        if (!file.isEmpty()) {
            String name = file.getOriginalFilename();
            if (fileProperties.isAvailable(name)) {
                // 文件保存路径
                // 转存文件
                accessoryRepository.save(file, uploadPath, name);

                uploadPath = AESUtil.encryptTokenHex(uploadPath);
                name = AESUtil.encryptTokenHex(name);

                return ResponseEntity.ok(FILE_SERVER + "/download?uploadPath=" + uploadPath + "&name=" + name);

            } else {
                return ResponseEntity.badRequest().body("请勿上传非法文件!");
            }
        } else {
            return ResponseEntity.badRequest().body("文件不存在!");
        }
    }


    /**
     * 下载文件流
     *
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @GetMapping(value = "downloadFile", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<StreamingResponseBody> downloadFile(String uploadPath, String name) throws Exception {
        File file = accessoryRepository.get(uploadPath, name);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-disposition", "attachment; filename=" + name)
                .body(outputStream -> {
                    try (InputStream inputStream = new FileInputStream(file)) {
                        StreamUtils.copy(inputStream, outputStream);
                    } catch (IOException e) {

                    }
                });
    }

    /**
     * 上传文件
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("uploadFile")
    public ResponseEntity uploadFile(HttpServletRequest request, MultipartFile file) {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        String uploadPath = request.getParameter("uploadPath") + "/" + year + "/" + month + "/";
        ;
        // 判断文件是否为空
        if (!file.isEmpty()) {
            String name = file.getOriginalFilename();
            if (fileProperties.isAvailable(name)) {
                // 文件保存路径
                // 转存文件
                accessoryRepository.save(file, uploadPath, name);
                return ResponseEntity.ok(FILE_SERVER + "/downloadFile?uploadPath=" + uploadPath + "&name=" + name);

            } else {
                return ResponseEntity.badRequest().body("请勿上传非法文件!");
            }
        } else {
            return ResponseEntity.badRequest().body("文件不存在!");
        }
    }

    @RequestMapping("uploadFile2")
    public ResponseEntity uploadFile2(HttpServletRequest request, MultipartFile file) {

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        String uploadPath = request.getParameter("uploadPath") + "/" + year + "/" + month;
        ;
        // 判断文件是否为空
        if (!file.isEmpty()) {
            String name = file.getOriginalFilename();
            if (fileProperties.isAvailable(name)) {
                // 文件保存路径
                // 转存文件
                accessoryRepository.save(file, uploadPath, name);
                return ResponseEntity.ok(FILE_SERVER + "/downloadFile?uploadPath=" + uploadPath + "&name=" + name);

            } else {
                return ResponseEntity.badRequest().body("请勿上传非法文件!");
            }
        } else {
            return ResponseEntity.badRequest().body("文件不存在!");
        }
    }


    @RequestMapping("webupload/upload")
    public ResponseEntity webupload(HttpServletRequest request, MultipartFile file) throws IOException {

        String uploadPath = request.getParameter("uploadPath");
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
//        String fileUrl = com.jeeplus.file.utils.FileKit.getAttachmentUrl()+uploadPath+"/"+year+"/"+month+"/";
        String fileUrl = com.jeeplus.file.utils.FileKit.getAttachmentUrl();
        String fileDir = com.jeeplus.file.utils.FileKit.getAttachmentDir() + uploadPath + "/" + year + "/" + month + "/";
        // 判断文件是否为空
        if (!file.isEmpty()) {
            String name = file.getOriginalFilename();
            if (fileProperties.isAvailable(name)) {
                // 文件保存路径
                // 转存文件
                FileUtils.createDirectory(fileDir);
                String filePath = fileDir + name;
                File newFile = FileUtils.getAvailableFile(filePath, 0);
                file.transferTo(newFile);
                return ResponseUtil.newInstance().add("id", FileKit.transDirToUrl(newFile.getAbsolutePath())).add("url", fileUrl + newFile.getName()).add("newName", newFile.getName()).ok();
            } else {
                return ResponseEntity.badRequest().body("请勿上传非法文件!");
            }
        } else {
            return ResponseEntity.badRequest().body("文件不存在!");
        }
    }

}
