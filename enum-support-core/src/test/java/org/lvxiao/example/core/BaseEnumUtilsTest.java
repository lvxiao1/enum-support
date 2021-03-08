package org.lvxiao.example.core;

import org.junit.After;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.lvxiao.enums.core.BaseEnum;
import org.lvxiao.enums.core.BaseEnumUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * BaseEnumUtils Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>3月 6, 2021</pre>
 */
public class BaseEnumUtilsTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getEnum(Object code, Class<T> clz)
     */
    @Test
    public void testGetEnum() throws Exception {
        assertEquals(SexType.MAN, BaseEnumUtils.getEnum(1, SexType.class));
        assertEquals(SexType.WOMAN, BaseEnumUtils.getEnum(2, SexType.class));

        assertEquals(SexType.MAN, BaseEnumUtils.getEnum("1", SexType.class));
        assertEquals(SexType.WOMAN, BaseEnumUtils.getEnum("2", SexType.class));

        assertNull(BaseEnumUtils.getEnum("3", SexType.class));
    }

    /**
     * Method: isExist(Object code, Class<T> clz)
     */
    @Test
    public void testIsExist() throws Exception {
        assertTrue(BaseEnumUtils.isExist(1, SexType.class));
        assertTrue(BaseEnumUtils.isExist(2, SexType.class));
        assertFalse(BaseEnumUtils.isExist(3, SexType.class));

        assertTrue(BaseEnumUtils.isExist("1", SexType.class));
        assertTrue(BaseEnumUtils.isExist("2", SexType.class));
        assertFalse(BaseEnumUtils.isExist("3", SexType.class));
    }

    /**
     * Method: listPackageEnums(String... packageList)
     */
    @Test
    public void testListPackageEnums() throws Exception {

        List<Class<? extends BaseEnum<?>>> classes = BaseEnumUtils.listPackageEnums("org.lvxiao.example.core");
        assertEquals(2, classes.size());
        assertTrue(classes.contains(SexType.class));
    }

    /**
     * Method: listPackageEnumMap(String... packages)
     */
    @Test
    public void testListPackageEnumMap() throws Exception {
        Map<String, List<BaseEnum<?>>> map = BaseEnumUtils.listPackageEnumMap("org.lvxiao.example.core");
        assertEquals(2, map.size());
        assertTrue(map.containsKey("org.lvxiao.example.core.SexType"));

        List<BaseEnum<?>> baseEnums = map.get("org.lvxiao.example.core.SexType");
        assertEquals(2, baseEnums.size());
        assertTrue(baseEnums.containsAll(Arrays.asList(SexType.MAN, SexType.WOMAN)));
        
        assertTrue(map.containsKey("user.sex"));


    }

    /**
     * Method: buildValues(Class<T> baseEnumClass)
     */
    @Test
    public void testBuildValues() throws Exception {
    }


} 
