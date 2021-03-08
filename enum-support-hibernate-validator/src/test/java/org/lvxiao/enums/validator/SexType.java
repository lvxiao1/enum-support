package org.lvxiao.enums.validator;

import org.lvxiao.enums.core.BaseEnum;

public enum SexType implements BaseEnum<Integer> {
    MAN(1, "男"), WOMAN(2, "女");

    private final Integer code;
    private final String name;

    SexType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
