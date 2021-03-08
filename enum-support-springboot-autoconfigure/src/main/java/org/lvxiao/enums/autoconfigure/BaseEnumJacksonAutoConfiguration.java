package org.lvxiao.enums.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lvxiao.enums.json.jackson.BaseEnumModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@ConditionalOnClass(value = {BaseEnumModule.class, ObjectMapper.class})
public class BaseEnumJacksonAutoConfiguration implements Jackson2ObjectMapperBuilderCustomizer {

    @Override
    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        jacksonObjectMapperBuilder.modules(new BaseEnumModule());
    }
    
}
