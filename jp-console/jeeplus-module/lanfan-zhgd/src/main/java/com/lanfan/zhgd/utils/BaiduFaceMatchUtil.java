package com.lanfan.zhgd.utils;

import com.alibaba.fastjson2.JSONObject;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * 百度人脸 比对
 */
public class BaiduFaceMatchUtil {

//    @Value("${baidu.api_key}")
//    public static String API_KEY;
//    @Value("${baidu.secret_key}")
//    public static String SECRET_KEY;

    public static final String API_KEY = "K4iidMkF1ODQTYCz2GHRLKzS";
    public static final String SECRET_KEY = "i018ugIExRwx4SO5zaTClUDUdYOm9xO2";

    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build();

    // ===== Token 缓存 =====
    private static String cachedToken;
    private static long tokenExpireTime = 0;

    private static final int MAX_RETRY = 3;

    /**
     * ✅ 对外安全方法：自动处理错误码 + 重试
     */
    public static JSONObject faceMatchSafe(String imgPath1, String imgPath2) throws Exception {
        int retry = 0;
        long sleep = 1000;

        while (true) {
            JSONObject result = faceMatch(imgPath1, imgPath2);
            int code = result.getIntValue("error_code");

            // 成功
            if (code == 0) {
                return result;
            }

            // Token 相关错误 → 刷新后重试
            if (isTokenError(code)) {
                invalidateToken();
                if (retry++ < MAX_RETRY) {
                    continue;
                }
            }
            // 可重试型错误
            else if (isRetryable(code)) {
                if (retry++ < MAX_RETRY) {
                    Thread.sleep(sleep);
                    sleep *= 2; // 指数退避
                    continue;
                }
            }

            // 不可重试 or 超过重试次数
            return result;
        }
    }

    /**
     * baidu 人脸比对
     * @param sourceFace
     * @param targetFace
     * @return
     * @throws IOException
     */
    private static JSONObject faceMatch(String sourceFace, String targetFace) throws IOException {
        String base64_1 = imageToBase64(sourceFace);
        String base64_2 = imageToBase64(targetFace);

        JSONObject img1 = new JSONObject();
        img1.put("image", base64_1);
        img1.put("image_type", "BASE64");
        img1.put("face_type", "LIVE");

        JSONObject img2 = new JSONObject();
        img2.put("image", base64_2);
        img2.put("image_type", "BASE64");
        img2.put("face_type", "LIVE");


        String jsonBody = "[" + img1.toJSONString() + "," + img2.toJSONString() + "]";
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonBody);

        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match?access_token=" + getAccessToken();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            String resp = response.body().string();
            return JSONObject.parseObject(resp);
        }
    }

    // ================= Token 相关 =================

    private static synchronized String getAccessToken() throws IOException {
        long now = System.currentTimeMillis();
        if (cachedToken != null && now < tokenExpireTime) {
            return cachedToken;
        }
        refreshToken();
        return cachedToken;
    }

    private static void invalidateToken() {
        cachedToken = null;
        tokenExpireTime = 0;
    }

    private static void refreshToken() throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        String bodyStr = "grant_type=client_credentials"
                + "&client_id=" + API_KEY
                + "&client_secret=" + SECRET_KEY;

        RequestBody body = RequestBody.create(mediaType, bodyStr);
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token")
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            String resp = response.body().string();
            JSONObject json = JSONObject.parseObject(resp);
            cachedToken = json.getString("access_token");
            int expiresIn = json.getIntValue("expires_in"); // 秒
            tokenExpireTime = System.currentTimeMillis() + (expiresIn - 60) * 1000L;
        }
    }

    // ================= 错误码策略 =================

    /**
     * token 错误：100 / 110 / 111
     */
    private static boolean isTokenError(int code) {
        return code == 100 || code == 110 || code == 111;
    }

    /**
     * 可重试错误：服务异常 / system busy / QPS 超限
     */
    private static boolean isRetryable(int code) {
        return code == 2        // Service temporarily unavailable
                || code == 18       // QPS 超限
                || code == 222915;  // system busy
    }

    // ================= 工具方法 =================

    private static String imageToBase64(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] bytes = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 直接获取人脸比对分数
     *
     * @return score，相似度分数；如果失败返回 -1
     */
    public static double faceMatchScore(String imgPath1, String imgPath2) throws Exception {
        JSONObject result = faceMatchSafe(imgPath1, imgPath2);

        if (result != null && result.getIntValue("error_code") == 0) {
            JSONObject res = result.getJSONObject("result");
            if (res != null) {
                return res.getDoubleValue("score");
            }
        }
        return -1;
    }
    // ================= 测试入口 =================

    public static void main(String[] args) throws Exception {

        double score = BaiduFaceMatchUtil.faceMatchScore(
                "/Users/jackson/Desktop/lllll/戴兴建8164965633841886151.jpg",
                "/Users/jackson/Desktop/lllll/1.jpg"
        );

        if (score >= 0) {
            String format = String.format("%.2f%%", score);
            System.out.println("相似度：" + format);
        } else {
            System.out.println("比对失败");
        }
    }
}