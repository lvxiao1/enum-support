package org.lvxiao.enums.core;

import org.reflections.Reflections;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 枚举工具类
 *
 * @author lvxiao
 * @since 1.0.0
 */
public abstract class BaseEnumUtils {

    /**
     * 通过Code获取枚举常量
     *
     * @param code 枚举编码
     * @param clz  枚举Class
     * @param <T>  枚举类型
     * @return 如果在枚举常量中匹配成功则返回常量、否则返回NULL
     */
    public static <T extends Enum<T> & BaseEnum<?>> T getEnum(Object code, Class<T> clz) {
        if (code == null) {
            EnumParseException.throwException("code is not null");
        }

        if (clz == null) {
            EnumParseException.throwException("clz is not null");
        }

        if (!clz.isEnum()) {
            EnumParseException.throwException("clz is not enum");
        }

        T result = null;
        T[] enumConstants = clz.getEnumConstants();
        for (T enumConstant : enumConstants) {
            if (enumConstant.equalsCode(code)) {
                result = enumConstant;
                break;
            }
        }

        return result;
    }


    /**
     * 判断Code是否存在枚举常量中
     *
     * @param code 枚举编码
     * @param clz  枚举Class
     * @param <T>  枚举类型
     * @return 如果在枚举常量中匹配成功则返回true、否则返回false
     */
    public static <T extends Enum<T> & BaseEnum<?>> boolean isExist(Object code, Class<T> clz) {
        return null != getEnum(code, clz);
    }

    /**
     * 获取指定包下的BaseEnum枚举Class
     *
     * @param packageList 包名
     * @return BaseEnum枚举Class集合
     */
    public static List<Class<? extends BaseEnum<?>>> listPackageEnums(String... packageList) {
        Reflections refs = new Reflections(packageList);
        Set<Class<? extends BaseEnum>> subTypes = refs.getSubTypesOf(BaseEnum.class);

        return subTypes
                .stream()
                .filter(Class::isEnum)
                .map(e -> (Class<? extends BaseEnum<?>>) e)
                .collect(Collectors.toList());
    }

    /**
     * 获取指定包下的BaseEnum枚举, 并以业务编码进行分组
     *
     * @param packages 包名
     * @return key为业务编码，value为枚举所有常量
     */
    public static Map<String, List<BaseEnum<?>>> listPackageEnumMap(String... packages) {
        return listPackageEnums(packages)
                .stream()
                .filter(Class::isEnum)
                .collect(Collectors.toMap(clz -> {
                    EnumDescAnnotation annotation = clz.getAnnotation(EnumDescAnnotation.class);
                    if (annotation == null || annotation.businessCode().isEmpty()) {
                        return clz.getName();
                    }
                    return annotation.businessCode();
                }, clz -> Arrays.asList(clz.getEnumConstants())));
    }


    /**
     * 构造枚举可用值
     *
     * @param baseEnumClass baseEnumClass
     * @return 返回所有枚举code的集合
     */
    public static <T extends Enum<T> & BaseEnum<?>> List<?> buildValues(Class<T> baseEnumClass) {
        if (baseEnumClass == null) {
            EnumParseException.throwException("baseEnumClass is not null");
        }

        Object[] enumConstants = baseEnumClass.getEnumConstants();
        return Arrays.stream(enumConstants)
                .map(r -> (BaseEnum<?>) r)
                .map(BaseEnum::getCode).collect(Collectors.toList());
    }

}
