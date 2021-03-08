package org.lvxiao.enums.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.lvxiao.enums.core.BaseEnum;
import org.lvxiao.enums.core.BaseEnumUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * mybatis 类型处理器, 转换成JDBC类型与获取JDBC类型分别调用：
 * <br/>
 * PreparedStatement#setObject、PreparedStatement#getObject
 * <br/>
 * 如mysql： AbstractQueryBindings#DEFAULT_MYSQL_TYPES
 *
 * @author lvxiao
 * @since 1.0.0
 */
public class BaseEnumTypeHandler<T extends Enum<T> & BaseEnum<?>> extends BaseTypeHandler<Enum<T>> {

    private final Class<T> type;

    public BaseEnumTypeHandler(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Enum<T> parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, ((BaseEnum<?>) parameter).getCode());
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return BaseEnumUtils.getEnum(rs.getObject(columnName), type);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return BaseEnumUtils.getEnum(rs.getObject(columnIndex), type);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return BaseEnumUtils.getEnum(cs.getObject(columnIndex), type);
    }
}
