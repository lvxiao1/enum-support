package org.lvxiao.enums.json.jackson;

import org.lvxiao.enums.core.BaseEnum;

public enum InvoiceStatus implements BaseEnum<Integer> {
    OPEN_ING(1, "开票中"), OPEN(2, "已开票");

    private final Integer code;
    private final String name;

    InvoiceStatus(Integer code, String name) {
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
