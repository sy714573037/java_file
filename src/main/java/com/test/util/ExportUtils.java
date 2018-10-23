package com.test.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

/**
 * 导出工具类
 * 
 * @author shuyi
 * @date 2018/09/13
 */
public class ExportUtils {

    /**
     * 复制文件
     */
    public static void copyFile() {

        // 删除已经导出的文件夹
        // ExportUtils.deleteDir();

        // 实际文件
        File realFile = null;
        // 导出的文件
        File targetFile = null;
        for (String filePath : Consts.fileList) {
            filePath = StringUtils.trim(filePath);
            // 是空值或者有#注释的跳过
            if (StringUtils.isEmpty(filePath) || StringUtils.startsWith(filePath, "#")) {
                continue;
            }
            // 文件实际路径
            String realPath = Consts.WORK_SPACE + filePath;
            realFile = new File(realPath);
            // 导出文件路径
            String targetPath = Consts.TARGET + filePath;
            // 实际文件的父目录
            String parentPath = ExportUtils.getParentPath(realPath);
            // 目标文件夹
            File targetFileDir = new File(ExportUtils.getParentPath(targetPath));
            // 是文件夹就复制所有文件
            if (realFile.isDirectory()) {
                targetFile = new File(targetPath);
                targetFile.mkdirs();
                // 复制整个文件夹的文件
                try {
                    // 如果需要导出的是class文件就去找class文件
                    if (Consts.CLAZZ.equals(Consts.IMPORT_TYPE)) {
                        realPath = realPath.replace("src/main/java", "target/classes").replace(".java", ".class");
                        realFile = new File(realPath);
                    }
                    // 复制文件到文件夹
                    FileUtils.copyDirectoryToDirectory(realFile, targetFileDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // 不是文件夹,复制单个文件
                try {
                    if (Consts.CLAZZ.equals(Consts.IMPORT_TYPE)) {
                        realPath = realPath.replace("src/main/java", "target/classes").replace(".java", ".class");
                        realFile = new File(realPath);
                        if (realPath.endsWith(".class")) {
                            // 获取目录先的class和class$x
                            List<File> clazzFileList
                                = ExportUtils.getClazzFileList(parentPath.replace("src/main/java", "target/classes"),
                                    ExportUtils.getFileName(realPath));
                            // 复制class和class$x
                            for (File file : clazzFileList) {
                                FileUtils.copyFileToDirectory(file, targetFileDir);
                                System.out.println(file.getPath());
                            }
                            continue;
                        }
                    }
                    FileUtils.copyFileToDirectory(realFile, targetFileDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(realPath);
        }
    }

    /**
     * 获取文件流
     * 
     * @param fileName
     * @return
     */
    public static InputStream getFileInputStream(String fileName) {
        InputStream inputStream = ExportUtils.class.getClassLoader().getResourceAsStream(fileName);
        return inputStream;
    }

    /**
     * 获取上级目录
     * 
     * @param path
     * @return
     */
    public static String getParentPath(String path) {
        return path.substring(0, path.lastIndexOf(IOUtils.DIR_SEPARATOR_UNIX));
    }

    /**
     * 获取class文件和内部类
     * 
     * @param parentPath
     * @return
     */
    public static List<File> getClazzFileList(String parentPath, String fileName) {
        File file = new File(parentPath);
        List<File> fileList = Lists.newArrayList();
        File[] listFiles = file.listFiles();
        for (File sonFile : listFiles) {
            String sonFileName = org.springframework.util.StringUtils.delete(sonFile.getName(), ".class");
            if (sonFileName.startsWith(fileName + ClassUtils.INNER_CLASS_SEPARATOR_CHAR) || sonFileName.equals(fileName)) {
                fileList.add(sonFile);
            }
        }
        return fileList;
    }

    /**
     * 获取文件名
     * 
     * @param path
     * @return
     */
    public static String getFileName(String path) {
        return path.substring(path.lastIndexOf(Consts.FOLDER_SEPARATOR) + 1, path.lastIndexOf(Consts.POINT_STRING));
    }

    /**
     * 删除文件
     */
    public static void deleteDir() {
        try {
            FileUtils.deleteDirectory(new File(Consts.TARGET));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
