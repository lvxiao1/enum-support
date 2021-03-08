package org.lvxiao.enums.json.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.lvxiao.enums.core.BaseEnum;

import java.io.IOException;

/**
 * jackson 序列化器
 *
 * @author lvxiao
 * @since 1.0
 */
public class BaseEnumJacksonSerializer<T extends Enum<T> & BaseEnum<?>> extends JsonSerializer<T> {

    public BaseEnumJacksonSerializer() {

    }

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeObject(value.getCode());
    }
}
