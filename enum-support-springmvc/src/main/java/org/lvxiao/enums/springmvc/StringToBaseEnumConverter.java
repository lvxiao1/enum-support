package org.lvxiao.enums.springmvc;

import org.lvxiao.enums.core.BaseEnum;
import org.lvxiao.enums.core.BaseEnumUtils;
import org.springframework.core.convert.converter.Converter;

public class StringToBaseEnumConverter<T extends Enum<T> & BaseEnum<?>> implements Converter<String, T> {

    Class<T> targetClass;

    public StringToBaseEnumConverter(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public T convert(String source) {
        return BaseEnumUtils.getEnum(source, targetClass);
    }
}
