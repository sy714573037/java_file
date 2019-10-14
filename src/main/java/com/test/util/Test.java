package com.test.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件操作的小例子,与导出java/class文件无关
 *
 * @author shuyi
 * @date 2018/10/23
 */
public class Test {

    public static void main(String[] args) throws IOException {
        File file = new File("E:\\t.txt");
        List<?> list = FileUtils.readLines(file, "utf-8");
        List<String> newList = list.stream().map(o -> o + "hello").collect(Collectors.toList());
        FileUtils.writeLines(file, newList);
    }

}
