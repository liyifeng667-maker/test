package com.lanfan.zhgd.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class QiniuPhotoUploadScheduler {

    /* ========== 本地目录配置 ========== */
    @Value("${photo.upload.dir}")
    private String watchDir;

    /* ========== 七牛云配置 ========== */
    @Value("${qiniu.access-key}")
    private String accessKey;

    @Value("${qiniu.secret-key}")
    private String secretKey;

    @Value("${qiniu.bucket}")
    private String bucket;

    private UploadManager uploadManager;
    private String uploadToken;

    @PostConstruct
    public void init() {
        Configuration cfg = new Configuration(Region.autoRegion());
        uploadManager = new UploadManager(cfg);

        Auth auth = Auth.create(accessKey, secretKey);
        uploadToken = auth.uploadToken(bucket);
    }

    /**
     * 定时扫描并上传
     * fixedDelay：上一次执行完成后再延迟
     */
    @Scheduled(fixedDelayString = "#{${photo.upload.interval} * 1000}")
    public void scanAndUpload() {

        System.out.println("定时扫描并上传");
        File dir = new File(watchDir);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        File[] files = dir.listFiles(file ->
                file.isFile() && isImage(file.getName()));

        if (files == null || files.length == 0) {
            return;
        }

        for (File file : files) {
            System.out.println("有文件" + file.getName());

            // 防止正在写入
            if (isWriting(file)) {
                continue;
            }

            boolean success = upload(file);
            if (success) {
                boolean deleted = file.delete();
                if (!deleted) {
                    System.err.println("删除失败：" + file.getAbsolutePath());
                }
            }
        }
    }

    private boolean upload(File file) {
        try {
            String key = System.currentTimeMillis() + "_" + file.getName();
            Response response = uploadManager.put(file, key, uploadToken);
            return response.isOK();
        } catch (QiniuException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isImage(String name) {
        String n = name.toLowerCase();
        return n.endsWith(".jpg")
                || n.endsWith(".jpeg")
                || n.endsWith(".png");
    }

    /**
     * 判断文件是否还在写入
     */
    private boolean isWriting(File file) {
        long size1 = file.length();
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {
        }
        long size2 = file.length();
        return size1 != size2;
    }
    private static final String ACCESS_KEY = "PyvpRXXVvxF4UrdB8H4YIoRZ5IIEvi-AQ5Gotzbq";
    private static final String SECRET_KEY = "K_W6r9bPLgS6MFjakOHSauN12udFbRlsSTB9gP6f";
    private static final String DOMAIN = "https://t82q2z2cb.hn-bkt.clouddn.com";

    public static String getPrivateUrl(String key) {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        long expire = 3600; // 1小时
        return auth.privateDownloadUrl(DOMAIN + "/" + key, expire);
    }

    public static void main(String[] args) {
        String url = getPrivateUrl(
                "1767145031265_192.168.110.64_01_20251230163500918_MIX_TARGET_HUMAN_24.jpg"
        );
        System.out.println(url);
    }
}