package com.test.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Lists;

/**
 * 文件操作的小例子
 * 
 * @author shuyi
 * @date 2018/10/23
 */
public class Test {

    public static void main(String[] args) throws IOException {
        File file = new File("E:\\t.txt");

        List<?> list = FileUtils.readLines(file, "utf-8");
        List<String> newList = Lists.newArrayList();
        list.stream().forEach(x -> {
            newList.add(x + "hello");
        });
        FileUtils.writeLines(file, newList);
    }

}
