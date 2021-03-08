package org.lvxiao.enums.autoconfigure;

import org.lvxiao.enums.swagger2.BaseEnumPlugin;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.schema.Model;

@Configuration
@ConditionalOnClass(value = {BaseEnumPlugin.class, Model.class})
@AutoConfigureBefore()
public class BaseEnumSwagger2AutoConfiguration {

    @Bean
    public BaseEnumPlugin baseEnumPlugin() {
        return new BaseEnumPlugin();
    }
}
