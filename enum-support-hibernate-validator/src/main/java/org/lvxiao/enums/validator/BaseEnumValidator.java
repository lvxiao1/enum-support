package org.lvxiao.enums.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.lvxiao.enums.core.BaseEnum;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 检验字段值是否存在BaseEnum的Code中
 *
 * @author lvxiao
 * @since 1.0.0
 */
public class BaseEnumValidator implements ConstraintValidator<BaseEnumInclude, Object> {

    private Set<BaseEnum<?>> codes;

    private String err;

    @Override
    public void initialize(BaseEnumInclude constraintAnnotation) {
        BaseEnum<?>[] baseEnums = constraintAnnotation.value().getEnumConstants();
        codes = new HashSet<>();
        StringBuilder build = new StringBuilder();
        for (BaseEnum<?> baseEnum : baseEnums) {
            codes.add(baseEnum);
            build.append(baseEnum.getCode())
                    .append("=")
                    .append(baseEnum.getDesc())
                    .append(",");
        }

        build.deleteCharAt(build.length() - 1);
        err = build.toString();
    }

    @Override
    public boolean isValid(Object code, ConstraintValidatorContext constraintValidatorContext) {
        if (code == null) {
            return true;
        }
        HibernateConstraintValidatorContext unwrap = constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class);
        unwrap.addMessageParameter("codes", err);

        for (Iterator<BaseEnum<?>> it = codes.iterator(); it.hasNext(); ) {
            BaseEnum<?> next = it.next();
            if (next.equalsCode(code)) {
                return true;
            }
        }

        return false;
    }
}
