package org.lvxiao.enums.swagger2;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.google.common.base.Optional;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.lvxiao.enums.core.BaseEnum;
import org.lvxiao.enums.core.BaseEnumUtils;
import org.lvxiao.enums.core.EnumDescAnnotation;
import org.lvxiao.enums.core.EnumParseException;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * BaseEnum枚举swagger2插件，生成枚举相关的文档
 *
 * @author lvxiao
 * @since 1.0.0
 */
public class BaseEnumPlugin implements ModelPropertyBuilderPlugin, ParameterBuilderPlugin {

    @Override
    public void apply(ModelPropertyContext context) {
        Optional<BeanPropertyDefinition> optional = context.getBeanPropertyDefinition();
        if (!optional.isPresent() || !optional.get().hasField()) {
            return;
        }
        BeanPropertyDefinition beanPropertyDefinition = optional.get();
        Class<?> rawPrimaryType = beanPropertyDefinition.getRawPrimaryType();
        if (!rawPrimaryType.isEnum() || !BaseEnum.class.isAssignableFrom(rawPrimaryType)) {
            return;
        }

        context.getBuilder()
                .allowableValues(new AllowableListValues(buildValues(rawPrimaryType), "string"));

        ApiModelProperty annotation = beanPropertyDefinition.getField().getAnnotation(ApiModelProperty.class);
        if (annotation == null || annotation.value().isEmpty()) {
            context.getBuilder().description(buildDescription(rawPrimaryType));
        }
    }

    @Override
    public void apply(ParameterContext parameterContext) {
        ResolvedMethodParameter resolvedMethodParameter = parameterContext.resolvedMethodParameter();
        Class<?> erasedType = resolvedMethodParameter.getParameterType().getErasedType();
        if (!isBaseEnum(erasedType)) {
            return;
        }

        ParameterBuilder parameterBuilder = parameterContext.parameterBuilder();
        AllowableListValues allowableListValues = new AllowableListValues(buildValues(erasedType), "list");
        parameterBuilder.allowableValues(allowableListValues);
        parameterBuilder.modelRef(new ModelRef("string", allowableListValues));

        Optional<ApiParam> annotation = resolvedMethodParameter.findAnnotation(ApiParam.class);
        if (!annotation.isPresent() || annotation.get().value().isEmpty()) {
            parameterBuilder.description(buildDescription(erasedType));
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    /**
     * 构造枚举可用值
     *
     * @param baseEnumClass baseEnumClass
     * @return 返回所有枚举code的集合
     */
    private List<String> buildValues(Class<?> baseEnumClass) {
        if (!isBaseEnum(baseEnumClass)) {
            EnumParseException.throwException("baseEnumClass不是BaseEnum实现类");
        }

        Object[] enumConstants = baseEnumClass.getEnumConstants();
        return Arrays.stream(enumConstants)
                .map(r -> (BaseEnum<?>) r)
                .map(r -> r.getCode().toString()).collect(Collectors.toList());

    }

    /**
     * 构造枚举描述<br/>
     * 描述需要加上EnumDescAnnotation注解，否则只输出可用值的code
     *
     * @param baseEnumClass baseEnumClass
     * @return 格式: {类描述},  可用值: {code}={desc}
     */
    private String buildDescription(Class<?> baseEnumClass) {
        Object[] enumConstants = baseEnumClass.getEnumConstants();

        EnumDescAnnotation enumDescAnnotation = baseEnumClass.getAnnotation(EnumDescAnnotation.class);
        String description = "";
        if (enumDescAnnotation != null) {
            description = enumDescAnnotation.value() + ", ";
        }
        String collect = Arrays.stream(enumConstants)
                .map(r -> (BaseEnum<?>) r)
                .map(r -> r.getCode().toString() + "=" + r.getDesc()).collect(Collectors.joining(","));
        return description + "可用值: " + collect;
    }

    /**
     * 判断类是否BaseEnum枚举的实现类
     *
     * @param clazz baseEnumClass
     * @return 为BaseEnum枚举的实现类返回true
     */
    private boolean isBaseEnum(Class<?> clazz) {
        return clazz.isEnum() && BaseEnum.class.isAssignableFrom(clazz);
    }
}
