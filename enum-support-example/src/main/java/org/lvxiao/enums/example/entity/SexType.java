package org.lvxiao.enums.example.entity;

import org.lvxiao.enums.core.IntegerBaseEnum;

public enum SexType implements IntegerBaseEnum {
    MAN(1, "男"),
    WOMAN(2, "女");

    private Integer code;
    private String name;

    SexType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 获取枚举编码，编码主要用于待久化存储、业务处理等场景
     *
     * @return 枚举编码
     */
    @Override
    public Integer getCode() {
        return this.code;
    }

    /**
     * 获取枚举名称，于用可视化及增强可读性
     *
     * @return 枚举名称
     */
    @Override
    public String getName() {
        return this.name;
    }
}
