package org.lvxiao.enums.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.lvxiao.enums.core.BaseEnum;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

/**
 * 指定一个BaseEnum，检验字段值是否存在BaseEnum的Code中
 *
 * @author lvxiao
 * @since 1.0.0
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BaseEnumValidator.class)
public @interface BaseEnumInclude {

    /**
     * 错误消息
     *
     * @return 错误消息
     */
    String message() default "{org.lvxiao.enum.include.message}";

    /**
     * 指定一个BaseEnum，检验字段值是否存在BaseEnum的Code中
     *
     * @return
     */
    Class<? extends BaseEnum<?>> value();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
