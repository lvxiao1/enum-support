package org.lvxiao.enums.example.entity;

import org.lvxiao.enums.validator.BaseEnumInclude;

import javax.validation.constraints.NotNull;

public class Person {

    @BaseEnumInclude(value = SexType.class, message = "性别填写错误，正常值为{codes}")
    private Integer sex;

    @NotNull
    private SexType sexType;

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public SexType getSexType() {
        return sexType;
    }

    public void setSexType(SexType sexType) {
        this.sexType = sexType;
    }

    @Override
    public String toString() {
        return "Person{" +
                "sex=" + sex +
                ", sexType=" + sexType +
                '}';
    }
}
