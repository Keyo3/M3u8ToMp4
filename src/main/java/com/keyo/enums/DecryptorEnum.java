package com.keyo.enums;

/**
 * @Author Keyo
 * @date 2024/7/21
 */
public enum DecryptorEnum {

    AES("AES");

    private String id;

    DecryptorEnum(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
