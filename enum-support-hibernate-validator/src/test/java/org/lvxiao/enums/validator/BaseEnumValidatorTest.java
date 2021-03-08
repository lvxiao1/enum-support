package org.lvxiao.enums.validator;

import org.hibernate.validator.HibernateValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


public class BaseEnumValidatorTest {

    private Validator validator;

    @Before
    public void initValidator() {
        ValidatorFactory validatorFactory = Validation
                .byProvider(HibernateValidator.class)
                .configure()
                .buildValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testValidator() {
        Persion persion = new Persion();
        persion.setSexType(3);
        Set<ConstraintViolation<Persion>> validate = validator.validate(persion);

        Assert.assertSame(validate.size(), 1);

        ConstraintViolation<Persion> violation = validate.stream().findFirst().get();
        Assert.assertEquals(violation.getPropertyPath().toString(), "sexType");
        Assert.assertEquals(violation.getMessage(), "Integer:性别不匹配, 可用值: 1=男,2=女");

        persion.setSexType(1);
        validate = validator.validate(persion);
        Assert.assertSame(validate.size(), 0);

        persion.setSexType(2);
        validate = validator.validate(persion);
        Assert.assertSame(validate.size(), 0);

        persion.setSexType(null);
        persion.setSexType2("1");
        validate = validator.validate(persion);
        Assert.assertSame(validate.size(), 0);

        persion.setSexType2("2");
        validate = validator.validate(persion);
        Assert.assertSame(validate.size(), 0);

        persion.setSexType2("3");
        validate = validator.validate(persion);
        Assert.assertSame(validate.size(), 1);

        violation = validate.stream().findFirst().get();
        Assert.assertEquals(violation.getPropertyPath().toString(), "sexType2");
        Assert.assertEquals(violation.getMessage(), "String:性别不匹配, 可用值: 1=男,2=女");

    }

}
