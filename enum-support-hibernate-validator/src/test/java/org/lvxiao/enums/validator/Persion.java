package org.lvxiao.enums.validator;

public class Persion {

    @BaseEnumInclude(value = SexType.class, message = "Integer:性别不匹配, 可用值: {codes}")
    private Integer sexType;

    @BaseEnumInclude(value = SexType.class, message = "String:性别不匹配, 可用值: {codes}")
    private String sexType2;

    public Integer getSexType() {
        return sexType;
    }

    public void setSexType(Integer sexType) {
        this.sexType = sexType;
    }

    public String getSexType2() {
        return sexType2;
    }

    public void setSexType2(String sexType2) {
        this.sexType2 = sexType2;
    }
}
