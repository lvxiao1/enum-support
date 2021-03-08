package org.lvxiao.enums.core;

public class EnumParseException extends RuntimeException {
    public EnumParseException() {
    }

    public EnumParseException(String message) {
        super(message);
    }

    public EnumParseException(String message, Throwable e) {
        super(message, e);
    }

    public static void throwException() {
        throw new EnumParseException();
    }

    public static void throwException(String message) {
        throw new EnumParseException(message);
    }

    public static void throwException(String message, Throwable e) {
        throw new EnumParseException(message, e);
    }
}
