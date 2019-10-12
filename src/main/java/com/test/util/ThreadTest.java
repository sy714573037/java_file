package com.test.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest {


    private static int cupNum = Runtime.getRuntime().availableProcessors();

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(cupNum, cupNum,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(Integer.MAX_VALUE));

    public static void main(String[] args) throws InterruptedException {
        try {
            List<Integer> list = Lists.newArrayList();
            int i = 0;
            while (i < 9) {
                list.add(i);
                i++;
            }
            List<List<Integer>> partition = Lists.partition(list, 1);
            partition.forEach(integers -> {
                threadPoolExecutor.execute(new MyThread(integers));
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

        threadPoolExecutor.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("----------------------------------->");
    }

    static class MyThread implements Runnable {

        private List<Integer> list;

        private MyThread(List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            list.forEach(System.out::println);
        }
    }

}
