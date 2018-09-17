package com.test.util;

/**
 * 文件导出
 * 
 * @author shuyi
 * @date 2018/09/13
 */
public class FileMain {

    public static void main(String[] args) {
        System.out.println("----------开始导出----------");
        // 导出文件
        ExportUtils.copyFile();
        System.out.println("----------导出完毕----------");
    }

}
