package org.lvxiao.enums.example.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.lvxiao.enums.example.entity.SexType;
import org.lvxiao.enums.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/example/mybatis")
public class MyBatisExampleController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SqlSessionFactory sqlSession;

    @PostConstruct
    public void initSql() throws SQLException, IOException {
        try (Connection connection = sqlSession.openSession().getConnection()) {
            ScriptRunner runner = new ScriptRunner(connection);
            runner.setAutoCommit(true);
            runner.setStopOnError(false);
            runner.setLogWriter(null);
            runner.setErrorLogWriter(null);
            runner.runScript(Resources.getResourceAsReader("init.sql"));
        }
    }

    @GetMapping("/select")
    public User select(Integer id) {
        return userMapper.selectId(id);
    }

    @GetMapping("/selectBySex")
    public User selectBySex(SexType sexType) {
        return userMapper.selectBySex(sexType);
    }

    @PostMapping("/update")
    public String update(@RequestBody User user) {
        userMapper.update(user);
        return "ok";
    }

}
