package org.lvxiao.enums.json.jackson;

import com.fasterxml.jackson.databind.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BaseEnumJacksonDeserializerTest {
    ObjectMapper mapper;

    @Before
    public void init() {
        mapper = new ObjectMapper();
        mapper.registerModule(new BaseEnumModule());
    }

    @Test
    public void deserializeTest() throws Exception {
        String code = "2";
        Assert.assertTrue(mapper.readValue(code, InvoiceStatus.class) == InvoiceStatus.OPEN);

        // 不影响其它枚举类
        String code2 = "1";
        Assert.assertTrue(mapper.readValue(code2, TestEnum.class) == TestEnum.B);
    }
}
