package com.keyo.builder;

/**
 * 建造者
 *
 * @Author Keyo
 * @date 2024/7/20
 */
public interface Builder<T> {

    /**
     * 建造
     *
     * @return
     */
    T build();
}