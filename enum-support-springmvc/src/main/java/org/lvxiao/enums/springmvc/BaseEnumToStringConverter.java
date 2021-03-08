package org.lvxiao.enums.springmvc;

import org.lvxiao.enums.core.BaseEnum;
import org.springframework.core.convert.converter.Converter;

public class BaseEnumToStringConverter implements Converter<BaseEnum<?>, String> {

    @Override
    public String convert(BaseEnum<?> source) {
        return source.getCode().toString();
    }
}
