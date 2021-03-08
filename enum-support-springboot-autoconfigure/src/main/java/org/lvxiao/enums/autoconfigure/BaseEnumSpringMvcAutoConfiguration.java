package org.lvxiao.enums.autoconfigure;

import org.lvxiao.enums.springmvc.StringToBaseEnumConverterFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnClass(value = {StringToBaseEnumConverterFactory.class, DispatcherServlet.class})
@AutoConfigureBefore(AutoConfigureAfter.class)
public class BaseEnumSpringMvcAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToBaseEnumConverterFactory());
    }
}
