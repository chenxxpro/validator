package net.nextabc.validator;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class Result {

    public final boolean passed;
    public final String errorMessage;

    Result(boolean passed, String errorMessage) {
        this.passed = passed;
        this.errorMessage = errorMessage;
    }

    static Result create(boolean passed, String message){
        return new Result(passed, message);
    }
}
