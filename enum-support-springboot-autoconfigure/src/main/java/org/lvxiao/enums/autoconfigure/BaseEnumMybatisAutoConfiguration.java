package org.lvxiao.enums.autoconfigure;

import org.apache.ibatis.session.SqlSession;
import org.lvxiao.enums.core.BaseEnum;
import org.lvxiao.enums.mybatis.BaseEnumTypeHandler;
import org.lvxiao.enums.springmvc.StringToBaseEnumConverterFactory;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnClass(value = {BaseEnumTypeHandler.class, SqlSession.class})
@AutoConfigureBefore(MybatisAutoConfiguration.class)
public class BaseEnumMybatisAutoConfiguration implements ConfigurationCustomizer {

    @Override
    public void customize(org.apache.ibatis.session.Configuration configuration) {
        configuration.getTypeHandlerRegistry().register(BaseEnum.class, BaseEnumTypeHandler.class);
    }
}
