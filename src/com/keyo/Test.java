package com.keyo;
/**
 * 
 * @Author Keyo
 * @date 2024/5/4
 */
public class Test {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("-------str����δʹ��ǰ-------------");
        System.out.println("JVM��ͼʹ�õ�����ڴ�����"+runtime.maxMemory()+"�ֽ�");
        System.out.println("JVM�����ڴ�����"+runtime.freeMemory()+"�ֽ�");
        System.out.println("JVM�ڴ�������"+runtime.totalMemory()+"�ֽ�");

        String str = "00";
        for (int i=0;i<2000;i++){
            str += "xx"+i;
        }
        System.out.println("-------str����ʹ�ú�-------------");
        System.out.println("JVM��ͼʹ�õ�����ڴ�����"+runtime.maxMemory()+"�ֽ�");
        System.out.println("JVM�����ڴ�����"+runtime.freeMemory()+"�ֽ�");
        System.out.println("JVM�ڴ�������"+runtime.totalMemory()+"�ֽ�");
        runtime.gc();
        System.out.println("-------�������պ�-------------");
        System.out.println("JVM��ͼʹ�õ�����ڴ�����"+runtime.maxMemory()+"�ֽ�");
        System.out.println("JVM�����ڴ�����"+runtime.freeMemory()+"�ֽ�");
        System.out.println("JVM�ڴ�������"+runtime.totalMemory()+"�ֽ�");
    }
}
