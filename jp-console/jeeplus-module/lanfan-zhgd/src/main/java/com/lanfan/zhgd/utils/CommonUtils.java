package com.lanfan.zhgd.utils;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import com.jeeplus.common.utils.FileUtils;
import javafx.scene.Parent;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.apache.poi.hssf.record.DVALRecord;
import org.jpedal.display.Display;
import org.springframework.web.multipart.MultipartFile;
import org.terracotta.utilities.exec.Shell;
import sun.misc.BASE64Encoder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    /**
     * 将对象origin的非空值赋值到对象destination
     *
     * @param origin
     * @param destination
     * @param <T>
     */
    public static <T> void mergeObject(T origin, T destination) {
        if (origin == null || destination == null)
            return;
        if (!origin.getClass().equals(destination.getClass()))
            return;

        Field[] fields = origin.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                Object value = fields[i].get(origin);
                if (null != value) {
                    fields[i].set(destination, value);
                }
                fields[i].setAccessible(false);
            } catch (Exception e) {
            }
        }
    }

    /**
     * 数组合并
     *
     * @param al
     * @param bl
     * @return
     */
    public static String[] getMergeArray(String[] al, String[] bl) {
        String[] a = al;
        String[] b = bl;
        String[] c = new String[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    /**
     * 日期格式化
     */
    public static String dateFormat(Date date) {
        if (date != null) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            return sdf1.format(date);
        } else {
            return "";
        }

    }

    /**
     * 日期格式化
     */
    public static String dateFormat(Date date, String s) {
        if (date != null) {
            SimpleDateFormat sdf1 = new SimpleDateFormat(s);
            return sdf1.format(date);
        } else {
            return "";
        }

    }

    /**
     * 日期格式化
     */
    public static String dateTimeFormat(Date date) {
        if (date != null) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf1.format(date);
        } else {
            return "";
        }
    }


    /**
     * 字符串转日期
     */
    public static Date dateTimeParse(String date) {
        if (StringUtils.isNotBlank(date)) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf1.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 字符串转日期
     */
    public static Date dateParse(String date) {
        if (StringUtils.isNotBlank(date)) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return sdf1.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 字符串日期去除时间
     */
    public static String removeTime(String date) {
        if (StringUtils.isNotBlank(date)) {
            date = date.trim();
            if (date.indexOf(" ") != -1) {
                date = date.substring(0, date.indexOf(" "));
            }
            return date;
        }
        return null;
    }

    /**
     * 不四舍五入取整(小于1时默认为1)
     */
    public static Integer transitionInteger(String s) {
        int i = 0;
        try {
            if (StringUtils.isNotBlank(s) && NumberUtil.isNumber(s)) {

                Double d = Double.valueOf(s);
                //特殊处理小于0.01没法显示
                if (d > 0 && d < 1) {
                    i = 1;
                } else {
                    String a = s.toString();
                    if (a.indexOf(".") != -1) {
                        String[] arr = a.split("\\.");
                        if (arr[1].length() > 2) {
                            a = a.substring(0, (a.indexOf(".") + 3));
                        }
                    }
                    d = Double.parseDouble(a);
                    i = d.intValue();//项目完成比例
                }

            }

        } catch (Exception e) {
            throw new RuntimeException("转换int错误");
        }
        return i;
    }


    /**
     * 根据当前时间获取上一年 下一月的日期
     */
    public static Date getLastYearDate(Date date) {
        String toDay = dateFormat(date);
        String[] arr = toDay.split("-");
        String dateStr = (Integer.valueOf(arr[0]) - 1) + "-" + (Integer.valueOf(arr[1]) + 1) + "-1";
        return dateParse(dateStr);
    }

    /**
     * 前/后?小时
     *
     * @param d
     * @param hour
     * @return
     */
    public static Date rollHour(Date d, int hour) {
        return new Date(d.getTime() + hour * 60 * 60 * 1000);
    }

    /**
     * 前/后?分钟
     *
     * @param d
     * @param minute
     * @return
     */
    public static Date rollMinute(Date d, int minute) {
        return new Date(d.getTime() + minute * 60 * 1000);
    }

    /**
     * 前/后?天
     *
     * @param d
     * @param day
     * @return
     */
    public static Date rollDay(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 前/后?月
     *
     * @param d
     * @param mon
     * @return
     */
    public static Date rollMon(Date d, int mon) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MONTH, mon);
        return cal.getTime();
    }

    /**
     * 前/后?年
     *
     * @param d
     * @param year
     * @return
     */
    public static Date rollYear(Date d, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.YEAR, year);
        return cal.getTime();
    }

    public static Date rollDate(Date d, int year, int mon, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, mon);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 获取随机数1-100
     */
    public static int getRandom() {
        Random rm = new Random();
        return rm.nextInt(100) + 1;
    }

    /**
     * 获取随机数
     *
     * @param number 位数
     * @return
     */
    public static int getRandom(int number) {
        Random rm = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < number; i++) {
            buffer.append(rm.nextInt(9) + 1);
        }

        return Integer.valueOf(buffer.toString());
    }


    /**
     * 不四舍五入保留小数
     *
     * @param d
     * @return
     */
    public static Double formatDouble3(double d, int a) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        if (d == 0) {
            return 0.0;
        } else {
            // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
            BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);
            return bg.doubleValue();

        }
    }

    /**
     * 图片转换Base64的方法
     */
    public static String ImageToBase64(MultipartFile mFiles) {
        File f = null;
        String base64 = null;
        try {
            f = File.createTempFile("tmp", null);
            mFiles.transferTo(f);
            FileInputStream inputFile = new FileInputStream(f);

            byte[] buffer = new byte[(int) f.length()];
            inputFile.read(buffer);
            inputFile.close();
            base64 = new BASE64Encoder().encode(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            f.deleteOnExit();     //使用完成删除文件
        }
        return base64;
    }

    /**
     * 图片转换Base64的方法
     */
    public static String ImageToBase64(File f) {
        String base64 = null;
        try {
            FileInputStream inputFile = new FileInputStream(f);

            byte[] buffer = new byte[(int) f.length()];
            inputFile.read(buffer);
            inputFile.close();
            base64 = new BASE64Encoder().encode(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //    f.deleteOnExit();     //使用完成删除文件
        }
        return base64;
    }

    /**
     * 将网络图片转base64
     *
     * @param url
     * @return
     */
    public static String ImageUrlToBase64(String url) {
        String base64 = "";
        File f = null;
        try {
            URL httpurl = new URL(url);
            f = File.createTempFile("tmp", null);
            FileUtils.copyURLToFile(httpurl, f);
            base64 = ImageToBase64(f);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            f.deleteOnExit();
        }

        return base64;

    }

    /**
     * 大写转小写
     *
     * @param str
     * @return
     */
    public static String toLowerCase(String str) {
        if (StringUtils.isNotBlank(str)) {
            return str.toLowerCase();
        } else {
            return str;
        }
    }

    /**
     * 小写转大写
     *
     * @param str
     * @return
     */
    public static String toUpperCase(String str) {
        if (StringUtils.isNotBlank(str)) {
            return str.toUpperCase();
        } else {
            return str;
        }
    }

    /**
     * 获取当月的 天数
     */

    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * MultipartFile转file
     */

    public static File MultipartFilePaseFile(MultipartFile multipartFile) {
        File f = null;
        int n;
        // 输出文件的新name 就是指上传后的文件名称
        System.out.println("getName:" + multipartFile.getName());
        // 输出源文件名称 就是指上传前的文件名称
        System.out.println("Oriname:" + multipartFile.getOriginalFilename());
        // 创建文件
        f = new File(multipartFile.getOriginalFilename());
        try (InputStream in = multipartFile.getInputStream(); OutputStream os = new FileOutputStream(f)) {
            // 得到文件流。以文件流的方式输出到新文件
            // 可以使用byte[] ss = multipartFile.getBytes();代替while
            byte[] buffer = new byte[4096];
            while ((n = in.read(buffer, 0, 4096)) != -1) {
                os.write(buffer, 0, n);
            }
            // 读取文件第一行
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            System.out.println(bufferedReader.readLine());
            // 输出路径
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return f;
        /*
        *
         // 输出file的URL
        System.out.println(f.toURI().toURL().toString());
        // 输出文件的绝对路径
        System.out.println(f.getAbsolutePath());
        // 操作完上的文件 需要删除在根目录下生成的文件
        File file = new File(f.toURI());
        if (file.delete()){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");

        }



        * */

    }


    public static String getStringValue(Map<String, Object> map, String key) {
        String str = null;
        if (map != null && map.get(key) != null) {
            str = map.get(key).toString();
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            str = m.replaceAll("");
            str = str.trim();
        }
        if (StringUtils.isBlank(str)) {
            str = null;
        }
        return str;
    }


    //拆分pdf
    public static void pdfciafen(String pdfPath, String caifenPath) {
        try {
            //创建目录
            FileUtils.createDirectory(caifenPath);
            File file = new File(pdfPath);
            // 打开来源 pdf
            PDDocument pdfDocument = PDDocument.load(file);
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);

            for (int pageNumber = 0; pageNumber < pdfDocument.getNumberOfPages(); pageNumber++) {

                // 提取的页码
//            int pageNumber = 0;
                // 以300 dpi 读取存入 BufferedImage 对象
                int dpi = 300;
                BufferedImage buffImage = pdfRenderer.renderImageWithDPI(pageNumber, dpi, ImageType.RGB);
                // 将 BufferedImage 写入到 png
                ImageIOUtil.writeImage(buffImage, caifenPath + "\\" + file.getName() + getRandom(10000) + "_" + pageNumber + ".png", dpi);

                // 关闭文档
            }
            pdfDocument.close();
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 将一组数据平均分成n组
     *
     * @param source 要分组的数据源
     * @param n      平均分成n组
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remainder = source.size() % n;  //(先计算出余数)
        int number = source.size() / n;  //然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remainder > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remainder--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }

    /**
     * 去除字符串中的多余字符
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n"); // 去除多个空格，去除制表符，回车，换行
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 判断字符串是否包含数字
     * @param str
     * @return
     */
    public static boolean containsDigit(String str) {
        return str.matches(".*\\d.*");
    }


    /**
     * 计算百分比
     *
     * @param numerator
     * @param denominator
     * @return
     */
    public static String calPercent(double numerator, double denominator) {
        double percent = numerator / denominator;
        NumberFormat nt = NumberFormat.getPercentInstance();
        nt.setMinimumFractionDigits(2); // 设置百分数精确度2即保留两位小数
        return nt.format(percent);
    }


    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String seal() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/seal";
        try {
            // 本地文件路径
            String filePath = "[本地文件路径]";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "[调用鉴权接口获取的token]";

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按照JSONArray中的对象的某个字段进行排序(采用fastJson)
     *
     * @param jsonArrStr json数组字符串
     */
    public static String jsonArraySort(String jsonArrStr, String key) {
        JSONArray jsonArr = JSON.parseArray(jsonArrStr);
        JSONArray sortedJsonArray = new JSONArray();
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < jsonArr.size(); i++) {
            jsonValues.add(jsonArr.getJSONObject(i));
        }
        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            // You can change "Name" with "ID" if you want to sort by ID

            @Override
            public int compare(JSONObject a, JSONObject b) {
                Integer aStr = 0;
                Integer bStr = 0;
                try {
                    // 这里是a、b需要处理的业务，需要根据你的规则进行修改。
                    aStr = a.getInteger(key);
                    bStr = b.getInteger(key);
                } catch (JSONException e) {
                    // do something
                }
                return aStr.compareTo(bStr);
                // if you want to change the sort order, simply use the following:
                // return -valA.compareTo(valB);
            }
        });
        for (int i = 0; i < jsonArr.size(); i++) {
            sortedJsonArray.add(jsonValues.get(i));
        }
        return sortedJsonArray.toString();
    }

    public static void main(String[] args) {
//        try {
//            useAWTDesktop("/Users/jackson/Lanfandocuments/营销部/测试1123/3221012200039337宜兴鼎祥置业有限公司-宜兴市新庄街道太湖大道南侧B3地块/3221012200039337宜兴鼎祥置业有限公司宜兴市新庄街道太湖大道南侧B3地块-开工备案/上传省公司全套开工备案资料/施工/建设模式确认单.pdf");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        String a = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),a);

    }

    public static void useAWTDesktop(String filePath) throws IOException {
//        String selectPath = "";
//        JFrame jFrame = new JFrame();
//        JFileChooser jFileChooser = new JFileChooser();
//        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int returnVal = jFileChooser.showOpenDialog(jFrame);
//        if(returnVal == JFileChooser.APPROVE_OPTION) {
//            selectPath =jFileChooser.getSelectedFile().getAbsolutePath() ;
//
//            System.out.println ( "你选择的目录是：" + selectPath );
//            jFileChooser.hide();
//        }
        File[] files = File.listRoots();
        for (File file : files) {
            System.out.println(file);
            JSONObject jsonObject = new JSONObject();
            List<String> files1 = getFiles(file.getPath() + "Users/");
        }

    }
    public static List<String> getFiles(String path) {
        path = "/Users/jackson/Lanfandocuments/sql文件";
        File dir = new File(path);
        ArrayList<String> allFileList = new ArrayList<>();
        // 判断文件夹是否存在
        if (!dir.exists()) {
        }
        JSONArray jsonArray = new JSONArray();

        getAllFile(dir, allFileList,jsonArray);


        return allFileList;
    }

    public static void getAllFile(File fileInput, List<String> allFileList,JSONArray jsonArray) {
        // 获取文件列表

        File[] fileList = fileInput.listFiles();
        assert fileList != null;
        JSONObject jsonObject1 = new JSONObject();

        for (File file : fileList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("label",file.getParent());
            if (file.isDirectory()) {
                // 递归处理文件夹
                // 如果不想统计子文件夹则可以将下一行注释掉
                getAllFile(file, allFileList,jsonArray);
            } else {
                // 如果是文件则将其加入到文件数组中
                allFileList.add(file.getAbsolutePath());
            }
            jsonObject1.put("children",jsonObject);

        }
        jsonArray.add(jsonObject1);

        System.out.println(jsonArray);

    }


    /**
     * 32大
     * @param input
     * @return
     */
    public static String md5(String input) {
        try {
            // 创建一个MessageDigest实例，指定MD5算法
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 对输入的字符串进行摘要计算
            byte[] digest = md.digest(input.getBytes());
            // 将字节数组转换为十六进制字符串
            BigInteger bigInt = new BigInteger(1, digest);
            String md5Hex = bigInt.toString(16);
            // 补全32位长度
            while (md5Hex.length() < 32) {
                md5Hex = "0" + md5Hex;
            }
            // 将结果转换为大写
            return md5Hex.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5加密失败", e);
        }
    }



}
