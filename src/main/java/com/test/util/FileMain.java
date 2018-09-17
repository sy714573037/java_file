package com.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

/**
 * @author shuyi
 * @date 2018/09/13
 */
@SuppressWarnings("unchecked")
public class FileMain {

    private final static String FILE_PATH_FILE_NAME = "filepath.txt";
    private final static String EXPORT_FILE_NAME = "export.properties";

    /** java文件 */
    public static String JAVA = "java";
    /** class文件 */
    public static String CLAZZ = "class";

    /** java文件路径 */
    public static List<String> fileList;
    /** 导出类型 */
    public static String IMPORT_TYPE;
    /** 工作空间 */
    public static String WORKSPACE;
    /** 导出的目标文件位置 */
    public static String TARGET;

    static {
        InputStream filePathInputStream = ExportUtils.getFileInputStream(FILE_PATH_FILE_NAME);
        InputStream exportiInputStream = ExportUtils.getFileInputStream(EXPORT_FILE_NAME);

        Properties properties = new Properties();
        try {
            properties.load(exportiInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(exportiInputStream)) {
                try {
                    exportiInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        IMPORT_TYPE = properties.getProperty("project.importType");
        WORKSPACE = properties.getProperty("project.workspace");
        TARGET = properties.getProperty("project.target");

        try {
            fileList = IOUtils.readLines(filePathInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(filePathInputStream)) {
                try {
                    filePathInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        ExportUtils.copyFile();
    }

}
