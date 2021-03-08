package org.lvxiao.enums.mybatis;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();

    User selectId(@Param("id") Integer id);

    User selectId2(@Param("id") Integer id);

    User selectId3(@Param("id") Integer id);

    User selectId4(@Param("id") Integer id);

    User selectBySex(@Param("sex") SexType sexType);
}
