package org.lvxiao.enums.json.jackson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import org.lvxiao.enums.core.BaseEnum;

/**
 * jackson 序列化模块
 *
 * @author lvxiao
 * @since 1.0
 */
public class BaseEnumModule extends SimpleModule {
    public BaseEnumModule() {
        init();
    }

    private void init() {
        // 进行代理，非BaseEnum类型的枚举使用默认Serializer
        SimpleSerializers simpleSerializers = new SimpleSerializers() {
            @Override
            public JsonSerializer<?> findSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc) {
                if (!BaseEnum.class.isAssignableFrom(type.getRawClass())) {
                    return null;
                }
                return super.findSerializer(config, type, beanDesc);
            }
        };

        simpleSerializers.addSerializer(Enum.class, new BaseEnumJacksonSerializer());
        this.setSerializers(simpleSerializers);

        // 进行代理，非BaseEnum类型的枚举使用默认Deserializer
        SimpleDeserializers simpleDeserializers = new SimpleDeserializers() {
            @Override
            public JsonDeserializer<?> findEnumDeserializer(Class<?> type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
                if (!BaseEnum.class.isAssignableFrom(type)) {
                    return null;
                }
                return super.findEnumDeserializer(type, config, beanDesc);
            }
        };

        simpleDeserializers.addDeserializer(Enum.class, new BaseEnumJacksonDeserializer());
        this.setDeserializers(simpleDeserializers);
    }
}
