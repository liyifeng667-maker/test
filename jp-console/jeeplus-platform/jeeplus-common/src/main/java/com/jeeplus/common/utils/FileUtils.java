/**
 * Copyright &copy; 2021-2026 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.common.utils;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.MalformedInputException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 文件操作工具类
 * 实现文件的创建、删除、复制、压缩、解压以及目录的创建、删除、复制、压缩解压等功能
 *
 * @author jeeplus
 * @version 2021-06-21
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

    private static Logger log = LoggerFactory.getLogger(FileUtils.class);

    private static String[] zipEncoding = new String[]{"GBK", "UTF-8", "ISO-8859-1"};

    public static String lineSeparator = System.getProperty("line.separator");

    /**
     * 判断是否是文件
     *
     * @param source
     */
    public static boolean isFile(String source) {
        return new File(source).isFile();
    }

    /**
     * 判断是否是目录
     *
     * @param source
     */
    public static boolean isFolder(String source) {
        return new File(source).isDirectory();
    }

    /**
     * 复制单个文件，如果目标文件存在，则不覆盖
     *
     * @param srcFileName  待复制的文件名
     * @param descFileName 目标文件名
     * @return 如果复制成功，则返回true，否则返回false
     */
    public static boolean copyFile(String srcFileName, String descFileName) {
        return FileUtils.copyFileCover(srcFileName, descFileName, false);
    }

    /**
     * 复制单个文件
     *
     * @param srcFileName  待复制的文件名
     * @param descFileName 目标文件名
     * @param coverlay     如果目标文件已存在，是否覆盖
     * @return 如果复制成功，则返回true，否则返回false
     */
    public static boolean copyFileCover(String srcFileName,
                                        String descFileName, boolean coverlay) {
        File srcFile = new File(srcFileName);
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            log.debug("复制文件失败，源文件 " + srcFileName + " 不存在!");
            return false;
        }
        // 判断源文件是否是合法的文件
        else if (!srcFile.isFile()) {
            log.debug("复制文件失败，" + srcFileName + " 不是一个文件!");
            return false;
        }
        File descFile = new File(descFileName);
        // 判断目标文件是否存在
        if (descFile.exists()) {
            // 如果目标文件存在，并且允许覆盖
            if (coverlay) {
                log.debug("目标文件已存在，准备删除!");
                if (!FileUtils.delFile(descFileName)) {
                    log.debug("删除目标文件 " + descFileName + " 失败!");
                    return false;
                }
            } else {
                log.debug("复制文件失败，目标文件 " + descFileName + " 已存在!");
                return false;
            }
        } else {
            if (!descFile.getParentFile().exists()) {
                // 如果目标文件所在的目录不存在，则创建目录
                log.debug("目标文件所在的目录不存在，创建目录!");
                // 创建目标文件所在的目录
                if (!descFile.getParentFile().mkdirs()) {
                    log.debug("创建目标文件所在的目录失败!");
                    return false;
                }
            }
        }

        // 准备复制文件
        // 读取的位数
        int readByte = 0;
        InputStream ins = null;
        OutputStream outs = null;
        try {
            // 打开源文件
            ins = new FileInputStream(srcFile);
            // 打开目标文件的输出流
            outs = new FileOutputStream(descFile);
            byte[] buf = new byte[1024];
            // 一次读取1024个字节，当readByte为-1时表示文件已经读取完毕
            while ((readByte = ins.read(buf)) != -1) {
                // 将读取的字节流写入到输出流
                outs.write(buf, 0, readByte);
            }
            log.debug("复制单个文件 " + srcFileName + " 到" + descFileName
                    + "成功!");
            return true;
        } catch (Exception e) {
            log.debug("复制文件失败：" + e.getMessage());
            return false;
        } finally {
            // 关闭输入输出流，首先关闭输出流，然后再关闭输入流
            if (outs != null) {
                try {
                    outs.close();
                } catch (IOException oute) {
                    oute.printStackTrace();
                }
            }
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException ine) {
                    ine.printStackTrace();
                }
            }
        }
    }

    /**
     * 复制整个目录的内容，如果目标目录存在，则不覆盖
     *
     * @param srcDirName  源目录名
     * @param descDirName 目标目录名
     * @return 如果复制成功返回true，否则返回false
     */
    public static boolean copyDirectory(String srcDirName, String descDirName) {
        return FileUtils.copyDirectoryCover(srcDirName, descDirName,
                false);
    }

    /**
     * 复制整个目录的内容
     *
     * @param srcDirName  源目录名
     * @param descDirName 目标目录名
     * @param coverlay    如果目标目录存在，是否覆盖
     * @return 如果复制成功返回true，否则返回false
     */
    public static boolean copyDirectoryCover(String srcDirName,
                                             String descDirName, boolean coverlay) {
        File srcDir = new File(srcDirName);
        // 判断源目录是否存在
        if (!srcDir.exists()) {
            log.debug("复制目录失败，源目录 " + srcDirName + " 不存在!");
            return false;
        }
        // 判断源目录是否是目录
        else if (!srcDir.isDirectory()) {
            log.debug("复制目录失败，" + srcDirName + " 不是一个目录!");
            return false;
        }
        // 如果目标文件夹名不以文件分隔符结尾，自动添加文件分隔符
        String descDirNames = descDirName;
        if (!descDirNames.endsWith(File.separator)) {
            descDirNames = descDirNames + File.separator;
        }
        File descDir = new File(descDirNames);
        // 如果目标文件夹存在
        if (descDir.exists()) {
            if (coverlay) {
                // 允许覆盖目标目录
                log.debug("目标目录已存在，准备删除!");
                if (!FileUtils.delFile(descDirNames)) {
                    log.debug("删除目录 " + descDirNames + " 失败!");
                    return false;
                }
            } else {
                log.debug("目标目录复制失败，目标目录 " + descDirNames + " 已存在!");
                return false;
            }
        } else {
            // 创建目标目录
            log.debug("目标目录不存在，准备创建!");
            if (!descDir.mkdirs()) {
                log.debug("创建目标目录失败!");
                return false;
            }

        }

        boolean flag = true;
        // 列出源目录下的所有文件名和子目录名
        File[] files = srcDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 如果是一个单个文件，则直接复制
            if (files[i].isFile()) {
                flag = FileUtils.copyFile(files[i].getAbsolutePath(),
                        descDirName + files[i].getName());
                // 如果拷贝文件失败，则退出循环
                if (!flag) {
                    break;
                }
            }
            // 如果是子目录，则继续复制目录
            if (files[i].isDirectory()) {
                flag = FileUtils.copyDirectory(files[i]
                        .getAbsolutePath(), descDirName + files[i].getName());
                // 如果拷贝目录失败，则退出循环
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            log.debug("复制目录 " + srcDirName + " 到 " + descDirName + " 失败!");
            return false;
        }
        log.debug("复制目录 " + srcDirName + " 到 " + descDirName + " 成功!");
        return true;

    }

    /**
     * 删除文件，可以删除单个文件或文件夹
     *
     * @param fileName 被删除的文件名
     * @return 如果删除成功，则返回true，否是返回false
     */
    public static boolean delFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            log.debug(fileName + " 文件不存在!");
            return true;
        } else {
            if (file.isFile()) {
                return FileUtils.deleteFile(fileName);
            } else {
                return FileUtils.deleteDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 被删除的文件名
     * @return 如果删除成功，则返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                log.debug("删除文件 " + fileName + " 成功!");
                return true;
            } else {
                log.debug("删除文件 " + fileName + " 失败!");
                return false;
            }
        } else {
            log.debug(fileName + " 文件不存在!");
            return true;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dirName 被删除的目录所在的文件路径
     * @return 如果目录删除成功，则返回true，否则返回false
     */
    public static boolean deleteDirectory(String dirName) {
        String dirNames = dirName;
        if (!dirNames.endsWith(File.separator)) {
            dirNames = dirNames + File.separator;
        }
        File dirFile = new File(dirNames);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            log.debug(dirNames + " 目录不存在!");
            return true;
        }
        boolean flag = true;
        // 列出全部文件及子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtils.deleteFile(files[i].getAbsolutePath());
                // 如果删除文件失败，则退出循环
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtils.deleteDirectory(files[i]
                        .getAbsolutePath());
                // 如果删除子目录失败，则退出循环
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            log.debug("删除目录失败!");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            log.debug("删除目录 " + dirName + " 成功!");
            return true;
        } else {
            log.debug("删除目录 " + dirName + " 失败!");
            return false;
        }

    }

    /**
     * 创建单个文件
     *
     * @param descFileName 文件名，包含路径
     * @return 如果创建成功，则返回true，否则返回false
     */
    public static boolean createFile(String descFileName) {
        File file = new File(descFileName);
        if (file.exists()) {
            log.debug("文件 " + descFileName + " 已存在!");
            return false;
        }
        if (descFileName.endsWith(File.separator)) {
            log.debug(descFileName + " 为目录，不能创建目录!");
            return false;
        }
        if (!file.getParentFile().exists()) {
            // 如果文件所在的目录不存在，则创建目录
            if (!file.getParentFile().mkdirs()) {
                log.debug("创建文件所在的目录失败!");
                return false;
            }
        }

        // 创建文件
        try {
            if (file.createNewFile()) {
                log.debug(descFileName + " 文件创建成功!");
                return true;
            } else {
                log.debug(descFileName + " 文件创建失败!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(descFileName + " 文件创建失败!");
            return false;
        }

    }

    /**
     * 创建目录
     *
     * @param descDirName 目录名,包含路径
     * @return 如果创建成功，则返回true，否则返回false
     */
    public static boolean createDirectory(String descDirName) {
        String descDirNames = descDirName;
        if (!descDirNames.endsWith(File.separator)) {
            descDirNames = descDirNames + File.separator;
        }
        File descDir = new File(descDirNames);
        if (descDir.exists()) {
            log.debug("目录 " + descDirNames + " 已存在!");
            return false;
        }
        // 创建目录
        if (descDir.mkdirs()) {
            log.debug("目录 " + descDirNames + " 创建成功!");
            return true;
        } else {
            log.debug("目录 " + descDirNames + " 创建失败!");
            return false;
        }

    }


    /**
     * 获取可以创建的文件名（如果有同名文件存在，参照Windows系统重命名为xxx(2).xxx)
     *
     * @param name
     * @param index
     * @return
     */
    public static File getAvailableFile(String name, int index) {
        File newFile = null;

        String suffix = StrUtil.subAfter(name, ".", true);
        String filePath = StrUtil.subBefore(name, ".", true);
        if (index == 0) {
            newFile = new File(filePath + "." + suffix);
        } else {
            newFile = new File(filePath + "(" + index + ")" + "." + suffix);
        }
        if (newFile.exists()) {
            return getAvailableFile(name, index + 1);
        } else {
            return newFile;
        }
    }

    ;

    /**
     * 获取可以创建的目录名（如果有同名目录存在，参照Windows系统重命名为xxx(2))
     *
     * @param name
     * @param index
     * @return
     */
    public static File getAvailableFolder(String name, int index) {
        File newFolder = null;
        if (index == 0) {
            newFolder = new File(name);
        } else {
            newFolder = new File(name + "(" + index + ")");
        }
        if (newFolder.exists()) {
            return getAvailableFolder(name, index + 1);
        } else {
            return newFolder;
        }
    }

    ;

    /**
     * 写入文件
     */
    public static void writeToFile(String fileName, String content, boolean append) {
        try {
            FileUtils.write(new File(fileName), content, "utf-8", append);
            log.debug("文件 " + fileName + " 写入成功!");
        } catch (IOException e) {
            log.debug("文件 " + fileName + " 写入失败! " + e.getMessage());
        }
    }

    /**
     * 写入文件
     */
    public static void writeToFile(String fileName, String content, String encoding, boolean append) {
        try {
            FileUtils.write(new File(fileName), content, encoding, append);
            log.debug("文件 " + fileName + " 写入成功!");
        } catch (IOException e) {
            log.debug("文件 " + fileName + " 写入失败! " + e.getMessage());
        }
    }


    /**
     * 获取待压缩文件在ZIP文件中entry的名字，即相对于跟目录的相对路径名
     *
     * @param dirPath 目录名
     * @param file    entry文件名
     * @return
     */
    private static String getEntryName(String dirPath, File file) {
        if (StringUtils.isBlank(dirPath)) {
            return file.getName();
        }
        String dirPaths = dirPath;
        if (!dirPaths.endsWith(File.separator)) {
            dirPaths = dirPaths + File.separator;
        }
        String filePath = file.getAbsolutePath();//bidorgcheck/批次-标段/批次-标段/报价文件/
        // 对于目录，必须在entry名字后面加上"/"，表示它将以目录项存储
        if (file.isDirectory()) {
            filePath += "/";
        }
        int index = filePath.indexOf(dirPaths);
        String aaaString = filePath.substring(index + dirPaths.length());
        //System.out.println(aaaString);
        return aaaString;
    }


    /**
     * 解压文件
     *
     * @param src
     * @param desc
     */
    public static void deCompress(String src, String desc) {
        File srcFile = new File(src);
        if (!srcFile.exists() || !srcFile.isFile()) {
            return;
        }
        File descFile = new File(desc);
        if (descFile.exists()) {
            try {
                FileUtils.forceDelete(descFile);
            } catch (IOException e) {
                log.error("deCompress", e);
            }
        }
        String type = StringUtils.substringAfterLast(srcFile.getName() == null ? "" : srcFile.getName(), ".").toLowerCase();
        switch (type) {
            case "zip":
                unZip(src, desc, 0);
                break;
            case "war":
                unWar(src, desc);
                break;
            case "7z":
                un7z(src, desc);
                break;
            case "rar":
//                unRar(src, desc);
                break;
            default:
                throw new RuntimeException("当前仅支持zip、7z、war格式文件解压");
        }
    }

    public static void unZip(String src, String desc) {
        unZip(src, desc, 0);
    }

    public static void unZip(String src, String desc, int encoding) {

        ZipFile zipFile = null;
        MalformedInputException malformedException = null;
        File descFile = new File(desc);
        try {
            File srcFile = new File(src);
            zipFile = new ZipFile(srcFile, zipEncoding[encoding]);
            ZipArchiveEntry entry;
            Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
            while (entries.hasMoreElements()) {
                entry = entries.nextElement();
                if (entry.isDirectory()) {
                    continue;
                }
                if (entry.getName().startsWith("__MACOSX")) {
                    continue;
                }
                if (!iisWinFileNameEncoding(entry.getName())) {
                    log.error("乱码文件" + zipEncoding[encoding] + ":" + entry.getName());
//                    throw new MalformedInputException(-1);
                }
                File outputFile = new File(entry.getName().startsWith(descFile.getName())
                        ? descFile.getParent() : desc
                        , entry.getName());
                if (!outputFile.getParentFile().exists()) {
                    outputFile.getParentFile().mkdirs();
                }
                try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                    IOUtils.write(
                            IOUtils.toByteArray(zipFile.getInputStream(entry))
                            , fos);
                    fos.close();
                } catch (Exception e) {
                    log.error("Exception", e);
                }
                if (outputFile.isFile() && StringUtils.endsWithIgnoreCase(outputFile.getName(), ".zip")) {
                    File childDescFile = new File(outputFile.getParentFile(), StringUtils.substringBeforeLast(outputFile.getName(), "."));
                    childDescFile.mkdirs();
                    unZip(outputFile.getAbsolutePath(), childDescFile.getAbsolutePath(), 0);
                }
            }
        } catch (MalformedInputException e) {
            log.error("MalformedInputException", e);
            malformedException = e;
        } catch (Exception e) {
            log.error("Exception", e);
            if (e.getCause() instanceof MalformedInputException) {
                malformedException = (MalformedInputException) e.getCause();
            } else {
                throw new RuntimeException("zip解压失败," + e.getMessage());
            }
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    log.error("IOException", e);
                }
            }
        }
        if (malformedException != null) {
            encoding++;
            if (encoding < zipEncoding.length) {
                unZip(src, desc, encoding);
            } else {
                throw new RuntimeException("压缩包编码不支持,解压失败");
            }
        }
    }

    public static void moveSameFile(File descFile) {
        if (descFile.listFiles() == null
                || descFile.listFiles().length == 0
                || descFile.listFiles().length > 1) {
            return;
        }
        File rootFile = descFile.listFiles()[0];
        while (rootFile.isDirectory()
                && descFile.getName().replaceAll("\\(\\d+\\)$", "").equals(rootFile.getName())) {
            try {
                copyDirectory(rootFile, descFile);
                FileUtils.deleteDirectory(rootFile);
                if (descFile.listFiles() != null && descFile.listFiles().length == 1) {
                    rootFile = descFile.listFiles()[0];
                } else {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException("移动失败");
            }
        }
    }


    public static void unWar(String warPath, String unzipPath) {
        System.out.println("war");

        File warFile = new File(warPath);
        ArchiveInputStream in = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(warFile));
            in = new ArchiveStreamFactory().createArchiveInputStream(ArchiveStreamFactory.JAR,
                    bufferedInputStream);
            JarArchiveEntry entry = null;
            while ((entry = (JarArchiveEntry) in.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    new File(unzipPath, entry.getName()).mkdir();
                } else {
                    OutputStream out = FileUtils.openOutputStream(new File(unzipPath, entry.getName()));
                    IOUtils.copy(in, out);
                    out.close();
                }
            }
            in.close();
        } catch (ArchiveException e) {
            log.error("不支持的压缩格式", e);
        } catch (Exception e) {
            log.error("文件写入发生错误", e);
            throw new RuntimeException("war解压失败," + e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("IOException", e);
                }
            }
        }
    }


    public static void un7z(String src, String desc) {
        SevenZFile sevenZFile = null;
        try {
            File srcFile = new File(src);
            sevenZFile = new SevenZFile(srcFile);
            SevenZArchiveEntry entry = null;//sevenZFile.getNextEntry();
            while ((entry = sevenZFile.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    File outputFile = new File(desc, entry.getName());
                    if (!outputFile.getParentFile().exists()) {
                        outputFile.getParentFile().mkdirs();
                    }
//					try (FileOutputStream fos = new FileOutputStream(outputFile)) {
//						IOUtils.write(
//								IOUtils.toByteArray(sevenZFile.getInputStream(entry))
//								, fos);
//						fos.close();
//					} catch (Exception e) {
//						log.error("Exception",e);
//					}
                    OutputStream out = new FileOutputStream(outputFile);
                    BufferedOutputStream bos = new BufferedOutputStream(out);
                    int len = -1;
                    byte[] buf = new byte[1024];
                    while ((len = sevenZFile.read(buf)) != -1) {
                        bos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    bos.close();
                    out.close();

                }
            }
        } catch (Exception e) {
            log.error("Exception", e);
            throw new RuntimeException("7z解压失败," + e.getMessage());
        } finally {
            if (sevenZFile != null) {
                try {
                    sevenZFile.close();
                } catch (IOException e) {
                    log.error("IOException", e);
                }
            }
        }
    }




    public static void zipFilesToZipFile(String dirPath,
                                         ZipOutputStream zouts, File... files) {
        FileInputStream fin = null;
        ZipEntry entry = null;
        // 创建复制缓冲区
        byte[] buf = new byte[4096];
        int readByte = 0;
        for (File file : files) {
            if (".DS_Store".equals(file.getName())) {
                continue;
            }
            if (file.isFile()) {
                try {
                    fin = new FileInputStream(file);
                    entry = new ZipEntry(dirPath + File.separator + file.getName());
                    // 存储信息到压缩文件
                    zouts.putNextEntry(entry);
                    // 复制字节到压缩文件
                    while ((readByte = fin.read(buf)) != -1) {
                        zouts.write(buf, 0, readByte);
                    }
                    zouts.closeEntry();
                    fin.close();
                    System.out.println("添加文件 " + file.getAbsolutePath() + " 到zip文件中!");
                } catch (Exception e) {
                    log.error("zipFilesToZipFile", e);
                    throw new RuntimeException("zip压缩失败," + e.getMessage());
                } finally {
                    if (fin != null) {
                        try {
                            fin.close();
                        } catch (IOException e) {
                            log.error("IOException", e);
                        }
                    }
                }
            } else {
                File[] listFiles = file.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    // 需要保留原来的文件结构时,需要对空文件夹进行处理
                    // 空文件夹的处理
                    try {
                        zouts.putNextEntry(new ZipEntry(dirPath + file.getName() + File.separator));
                        zouts.closeEntry();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (fin != null) {
                            try {
                                fin.close();
                            } catch (IOException e) {
                                log.error("IOException", e);
                            }
                        }
                    }
                } else {
                    zipFilesToZipFile(dirPath + File.separator + file.getName() + File.separator, zouts, listFiles);

                }
            }
        }
    }

    public static void zipFilesToZipFile(
            File descFile, File... files) {
        if (!descFile.getParentFile().exists()) {     //如果压缩包不存在  则创建
            descFile.getParentFile().mkdirs();
        }
        // 创建压缩文件
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(descFile));
            out.setEncoding("GBK");
            zipFilesToZipFile("", out, files);
            out.close();
        } catch (Exception e) {
            log.error("zipFilesToZipFile", e);
            throw new RuntimeException("zip压缩失败," + e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error("IOException", e);
                }
            }
        }

    }

    public static void main(String[] args) {
        String bString = "投标文件10BB06资质/资质文件/10BB06-9003001-0211/南京智友电力检测有限公司/01标段层_投标文件/附件/南京智友电力检测有限公司_承装（承修、承试）文件及网站截图（PDF版）2022070408465440139899.pdf";
        System.out.println(iisWinFileNameEncoding(bString));
    }

    private static boolean iisWinFileNameEncoding(String fileName) {
        try {
            for (int i = fileName.length(); --i >= 0; ) {
                int chr = fileName.charAt(i);
                // 符合文件路径要求的字符 0-9(30-39) a-z(97-122) A-Z(56-90) 汉字(19968-40869)\u4e00-\u9fa5 （）【】｛｝-_()[]^'
                // （）【】｛｝
                if (chr == 32 || chr == 65288 || chr == 65289 || chr == 12304 || chr == 12305 || chr == 65371 || chr == 65373 || chr == 12289) {
                    continue;
                }
                // !"#$%& *+/?\     63:? 42:*  43:+  47:/  chr:\
                if (chr <= 38 || chr == 63 /*|| chr == 42 || chr == 43 || chr == 47 || chr == 92*/) {
                    return false;
                }
                // 特殊字符 ASCII 124-19968  
                if (chr > 126 && chr < 19968) {
                    System.out.println(fileName.charAt(i) + ":" + chr);
                    return false;
                }
                if (chr > 40869) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Workbook getWorkbook(File file) throws IOException, InvalidFormatException {
        return WorkbookFactory.create(file);
    }

    public static String getAllCellString(Row row, int i) {
        Cell cell = row.getCell(i);
        if (cell != null) {
            return getCellData(cell).toString();
        } else {
            return "";
        }
    }

    private static Object readNumericCell(Cell cell) {
        double value = cell.getNumericCellValue();
        if (((int) value) == value) {
            return Integer.valueOf((int) value);
        } else {
            return Double.valueOf(value);
        }
    }

    public static Object getCellData(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue() == null ? ""
                        : cell.getRichStringCellValue().getString();
            case NUMERIC:
                return readNumericCell(cell);
            case BOOLEAN:
                return new Boolean(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return readNumericCell(cell);
                } catch (Exception e) {
                    try {
                        return cell.getRichStringCellValue() == null ? ""
                                : cell.getRichStringCellValue().getString();
                    } catch (Exception ee) {
                        throw new RuntimeException("获取公式类型的单元格失败", ee);
                    }
                }
            default:
                return "";
        }
    }

    public static Cell createCellAndSetValue(Row row, int colNo, String value) {
        Cell cell = row.createCell(colNo);
        cell.setCellValue(new XSSFRichTextString(StringUtils.trim(value)));
        return cell;
    }

    public static Cell createCellAndSetValue(Row row, int colNo, Double value) {
        Cell cell = row.createCell(colNo);
        cell.setCellValue(value);
        return cell;
    }

    public static Cell createCellAndSetValue(Row row, int colNo, Integer value) {
        Cell cell = row.createCell(colNo);
        cell.setCellValue(value);
        return cell;
    }

    public static double roundDouble(double inNumber, int param) {
        BigDecimal bd = new BigDecimal(String.valueOf(inNumber));
        bd = bd.setScale(param, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    public static double getSimilarity(String doc1, String doc2) {
        if (StringUtils.isBlank(doc1) && StringUtils.isBlank(doc2)) {
            return 1L;
        }
        if (StringUtils.isBlank(doc1) || StringUtils.isBlank(doc2)) {
            return 0L;
        }
        Map<Character, int[]> algMap = new HashMap<>();
        for (int i = 0; i < doc1.length(); i++) {
            char d1 = doc1.charAt(i);
            int[] fq = algMap.get(d1);
            if (fq != null && fq.length == 2) {
                fq[0]++;
            } else {
                fq = new int[2];
                fq[0] = 1;
                fq[1] = 0;
                algMap.put(d1, fq);
            }
        }
        for (int i = 0; i < doc2.length(); i++) {
            char d2 = doc2.charAt(i);
            int[] fq = algMap.get(d2);
            if (fq != null && fq.length == 2) {
                fq[1]++;
            } else {
                fq = new int[2];
                fq[0] = 0;
                fq[1] = 1;
                algMap.put(d2, fq);
            }
        }
        double sqdoc1 = 0;
        double sqdoc2 = 0;
        double denuminator = 0;
        for (Map.Entry entry : algMap.entrySet()) {
            int[] c = (int[]) entry.getValue();
            denuminator += c[0] * c[1];
            sqdoc1 += c[0] * c[0];
            sqdoc2 += c[1] * c[1];
        }
        return denuminator / Math.sqrt(sqdoc1 * sqdoc2);
    }

    public final static String PATTERN_SIMPLE = "yyyy-MM-dd";

    public final static String PATTERN_SIMPLE_EXT = "yyyy.MM.dd";

    public final static String PATTERN_SIMPLE_EXT_WITHOUT0 = "yyyy.M.d";

    public final static String PATTERN_SIMPLE_CHS = "yyyy年MM月dd日";

    public final static String PATTERN_WHOLE = "yyyy-MM-dd HH:mm:ss";

    public final static String PATTERN_COMPACT = "yyyyMMddHHmmss";

    public final static String PATTERN_SUCCINCT = "yyyyMMdd";

    public final static String PATTERN_EXCELTYPE = "yyyy/MM/dd";

    public static Date formatDate(Object cellValue) {
        if (cellValue == null) {
            return null;
        }
        String dateStr = (String) cellValue;

        SimpleDateFormat sdf = null;
        Date oldDate = null;
        //将excel中日期字符串转换成日期，再根据自定义格式转回字符串
        try {
            sdf = new SimpleDateFormat(PATTERN_SIMPLE);
            oldDate = sdf.parse(dateStr);
        } catch (ParseException e) {

            try {
                sdf = new SimpleDateFormat(PATTERN_SIMPLE_EXT);
                oldDate = sdf.parse(dateStr);
            } catch (ParseException e1) {
                try {
                    sdf = new SimpleDateFormat(PATTERN_SIMPLE_EXT_WITHOUT0);
                    oldDate = sdf.parse(dateStr);
                } catch (ParseException e2) {
                    try {
                        sdf = new SimpleDateFormat(PATTERN_SIMPLE_CHS);
                        oldDate = sdf.parse(dateStr);
                    } catch (ParseException e3) {

                        try {
                            sdf = new SimpleDateFormat(PATTERN_WHOLE);
                            oldDate = sdf.parse(dateStr);
                        } catch (ParseException e4) {
                            try {
                                sdf = new SimpleDateFormat(PATTERN_COMPACT);
                                oldDate = sdf.parse(dateStr);
                            } catch (ParseException e5) {
                                try {
                                    sdf = new SimpleDateFormat(PATTERN_SUCCINCT);
                                    oldDate = sdf.parse(dateStr);
                                } catch (ParseException e6) {

                                    sdf = new SimpleDateFormat(PATTERN_EXCELTYPE);
                                    try {
                                        oldDate = sdf.parse(dateStr);
                                    } catch (ParseException e7) {
                                        log.error(e7.getMessage());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return oldDate;
    }

    public static String dateToStr(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }

    public static String dateToStr(Date date) {
        return dateToStr(date, PATTERN_SIMPLE);
    }


}
