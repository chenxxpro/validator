package net.nextabc.validator;

/**
 * 数据源接口，用于返回等待检查的输入参数
 *
 * @author 陈哈哈 (yoojiachen@gmail.com)
 */
public interface Source {

    /**
     * 返回等待检查的输入参数
     *
     * @return String类型的输入参数
     */
    String value();
}
