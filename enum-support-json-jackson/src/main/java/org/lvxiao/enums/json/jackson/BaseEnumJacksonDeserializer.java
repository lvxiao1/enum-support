package org.lvxiao.enums.json.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import org.lvxiao.enums.core.BaseEnum;
import org.lvxiao.enums.core.BaseEnumUtils;

import java.io.IOException;


/**
 * jackson反序列化器
 *
 * @author lvxiao
 */
public class BaseEnumJacksonDeserializer<T extends Enum<T> & BaseEnum<?>> extends JsonDeserializer<Enum<T>> implements ContextualDeserializer {

    public BaseEnumJacksonDeserializer() {
    }

    private Class<T> clz;

    private BaseEnumJacksonDeserializer(Class<T> clz) {
        this.clz = clz;
    }

    @Override
    public Enum<T> deserialize(JsonParser p, DeserializationContext ct) throws IOException {
        String currentValue = p.getText();
        Enum<T> result = null;
        if (BaseEnum.class.isAssignableFrom(clz)) {
            result = BaseEnumUtils.getEnum(currentValue, clz);
        }
        return result;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
        return new BaseEnumJacksonDeserializer(ctxt.getContextualType().getRawClass());
    }
}
