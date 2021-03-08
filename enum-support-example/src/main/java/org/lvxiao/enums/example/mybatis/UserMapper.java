package org.lvxiao.enums.example.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.lvxiao.enums.example.entity.SexType;
import org.lvxiao.enums.example.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAll();

    User selectId(@Param("id") Integer id);

    User selectBySex(@Param("sex") SexType sex);

    void update(@Param("user") User user);

}
