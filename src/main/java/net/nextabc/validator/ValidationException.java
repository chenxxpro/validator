package net.nextabc.validator;

/**
 * @author 陈哈哈 (bitschen@163.com)
 */
final public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
