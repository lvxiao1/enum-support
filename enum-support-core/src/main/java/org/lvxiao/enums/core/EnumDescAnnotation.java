package org.lvxiao.enums.core;

import java.lang.annotation.*;

/**
 * 枚举描述，生成API文档时需要使用
 *
 * @author lvxiao
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumDescAnnotation {

    /**
     * 业务编码
     *
     * @return
     */
    String businessCode() default "";

    /**
     * 枚举类描述
     *
     * @return
     */
    String value() default "";
}
