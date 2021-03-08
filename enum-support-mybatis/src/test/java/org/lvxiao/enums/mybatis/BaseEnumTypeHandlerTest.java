package org.lvxiao.enums.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class BaseEnumTypeHandlerTest {
    private static SqlSessionFactory sqlMapper;

    @Before
    public void initialize() throws IOException, SQLException {
        final String resource = "MapperConfig.xml";
        final Reader reader = Resources.getResourceAsReader(resource);
        sqlMapper = new SqlSessionFactoryBuilder().build(reader);

        try (Connection connection = sqlMapper.openSession().getConnection()) {
            ScriptRunner runner = new ScriptRunner(connection);
            runner.setAutoCommit(true);
            runner.setStopOnError(false);
            runner.setLogWriter(null);
            runner.setErrorLogWriter(null);
            runner.runScript(Resources.getResourceAsReader("init.sql"));
        }
    }

    @Test
    public void selectResultTest() {
        SqlSession sqlSession = sqlMapper.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        System.out.println(mapper.selectId(1).getSex());
        //tinyint
        assertTrue(mapper.selectId(1).getSex() == SexType.MAN);
        assertTrue(mapper.selectId(2).getSex() == SexType.WOMAN);

        //int
        assertTrue(mapper.selectId2(1).getSex() == SexType.MAN);
        assertTrue(mapper.selectId2(2).getSex() == SexType.WOMAN);

        //char
        assertTrue(mapper.selectId3(1).getSex() == SexType.MAN);
        assertTrue(mapper.selectId3(2).getSex() == SexType.WOMAN);

        //varchar
        assertTrue(mapper.selectId4(1).getSex() == SexType.MAN);
        assertTrue(mapper.selectId4(2).getSex() == SexType.WOMAN);
    }

    @Test
    public void selectParamTest() {
        SqlSession sqlSession = sqlMapper.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        assertTrue(mapper.selectBySex(SexType.MAN).getId().compareTo(1) == 0);
        assertTrue(mapper.selectBySex(SexType.WOMAN).getId().compareTo(2) == 0);
    }
}