package org.lvxiao.enums.core;


/**
 * 基础的枚举超类， 所有基础特征支持功能都是基于该接口
 *
 * @author lvxiao
 * @since 1.0.0
 */
public interface BaseEnum<T> {

    /**
     * 获取枚举编码，编码主要用于待久化存储、业务处理等场景
     *
     * @return 枚举编码
     */
    T getCode();

    /**
     * 获取枚举名称，于用可视化及增强可读性
     *
     * @return 枚举名称
     */
    String getName();

    /**
     * 获取枚举说明信息
     *
     * @return 枚举说明信息
     */
    default String getDesc() {
        return this.getName();
    }

    /**
     * 比较入参Code与当前枚举是否一致，用于枚举检验、反序列化等
     * <br/>
     * 默认已处理如下情况, 其它情况需要自行实现:
     * <br/>
     * t->String, code->Integer
     * <br/>
     * t->Integer, code->Integer
     * <br/>
     * t->String, code->String
     *
     * @param t Code 需要匹配的Code
     * @return 匹配结果
     * @see BaseEnumUtils#getEnum(Object, Class)
     */
    default boolean equalsCode(Object t) {
        if (t == null) {
            return false;
        }

        if (this.getCode() instanceof Integer) {
            Integer code = (Integer) this.getCode();
            Integer targetCode;

            if (t instanceof String) {
                targetCode = Integer.valueOf((String) t);
            } else if (t instanceof Integer) {
                targetCode = (Integer) t;
            } else {
                targetCode = Integer.valueOf(t.toString());
            }

            return code.compareTo(targetCode) == 0;
        }

        return this.getCode().equals(t);
    }
}
