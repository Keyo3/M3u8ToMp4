package com.keyo;

import com.keyo.m3u8tomp4.M3u8ToMp4;

/**
 * @author Keyo
 * @date 2024/5/3
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("����ʼ----------------------------");
        M3u8ToMp4 m3u8ToMp4 = new M3u8ToMp4();
        m3u8ToMp4.transfer();
        System.out.println("�������----------------------------");
    }

}
