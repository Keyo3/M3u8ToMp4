package com.keyo;
/**
 * 
 * @Author Keyo
 * @date 2024/5/4
 */
public class Test {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("-------str变量未使用前-------------");
        System.out.println("JVM试图使用的最大内存量："+runtime.maxMemory()+"字节");
        System.out.println("JVM空闲内存量："+runtime.freeMemory()+"字节");
        System.out.println("JVM内存总量："+runtime.totalMemory()+"字节");

        String str = "00";
        for (int i=0;i<2000;i++){
            str += "xx"+i;
        }
        System.out.println("-------str变量使用后-------------");
        System.out.println("JVM试图使用的最大内存量："+runtime.maxMemory()+"字节");
        System.out.println("JVM空闲内存量："+runtime.freeMemory()+"字节");
        System.out.println("JVM内存总量："+runtime.totalMemory()+"字节");
        runtime.gc();
        System.out.println("-------垃圾回收后-------------");
        System.out.println("JVM试图使用的最大内存量："+runtime.maxMemory()+"字节");
        System.out.println("JVM空闲内存量："+runtime.freeMemory()+"字节");
        System.out.println("JVM内存总量："+runtime.totalMemory()+"字节");
    }
}
