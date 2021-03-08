package org.lvxiao.enums.example.entity;

import org.apache.ibatis.type.Alias;

@Alias("user")
public class User {

    private Integer id;

    private SexType sex;

    public User() {
    }

    public User(Integer id, SexType sex) {
        this.id = id;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", sex=" + sex +
                '}';
    }
}
