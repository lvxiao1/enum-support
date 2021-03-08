package org.lvxiao.enums.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaseEnumJacksonSerializerTest {

    ObjectMapper mapper;

    @Before
    public void init() {
        mapper = new ObjectMapper();
        mapper.registerModule(new BaseEnumModule());
    }

    @Test
    public void serializeTest() throws JsonProcessingException {
        String jsonString = "2";
        assertEquals(jsonString, mapper.writeValueAsString(InvoiceStatus.OPEN));

        assertEquals("\"B\"", mapper.writeValueAsString(TestEnum.B));
    }

}