package com.lxy.jvm;

import java.text.DecimalFormat;

public class Demo01 {

    public static void main(String[] args) throws InterruptedException {
        jvmInfo();
        byte[] bytes01 = new byte[1024 * 1024];
        System.out.println("分配了1m内存");
        jvmInfo();
        Thread.sleep(3000);
        byte[] bytes02 = new byte[4 * 1024 * 1024];
        System.out.println("分配了4m内存");
        jvmInfo();
    }

    private static String toM(long maxMemory) {
        float num = maxMemory / (1024 * 1024);
        DecimalFormat dlf = new DecimalFormat("0.00");
        return dlf.format(num);
    }

    private static void jvmInfo() {
        //最大内存配置信息
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("最大内存: " + toM(maxMemory) + "M");
        //空闲内存
        long freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("空闲内存: " + toM(freeMemory) + "M");
        //已使用内存
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("已使用内存: " + toM(totalMemory) + "M");

    }
}
