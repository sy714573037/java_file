package com.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 配置信息
 * 
 * @author shuyi
 * @date 2018/09/17
 */
@SuppressWarnings("unchecked")
public class Consts {

    /** 需要导出的文件目录 */
    private final static String IMPORT_FILE_PATH = "filepath.txt";

    /** 导出文件的属性配置 */
    private final static String EXPORT_FILE_PROP = "export.properties";

    /** java文件 */
    public static String JAVA = "java";

    /** class文件 */
    public static String CLAZZ = "class";

    /** java文件路径 */
    public static List<String> FILE_LIST;

    /** 导出类型 */
    public static String IMPORT_TYPE;

    /** 工作空间 */
    public static String WORK_SPACE;

    /** 导出的目标文件位置 */
    public static String TARGET;

    /** / */
    public static final String FOLDER_SEPARATOR = "/";

    /** . */
    public static final String POINT_STRING = ".";

    static {
        InputStream filePathInputStream = ExportUtils.getFileInputStream(IMPORT_FILE_PATH);
        InputStream exportiInputStream = ExportUtils.getFileInputStream(EXPORT_FILE_PROP);

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
        IMPORT_TYPE = StringUtils.lowerCase(properties.getProperty("project.importType"));
        WORK_SPACE = properties.getProperty("project.workspace");
        TARGET = properties.getProperty("project.target");

        try {
            FILE_LIST = IOUtils.readLines(filePathInputStream);
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
}
