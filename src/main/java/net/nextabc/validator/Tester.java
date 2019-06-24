package net.nextabc.validator;

import java.util.HashMap;
import java.util.Map;

/**
 * 检查参数接口
 *
 * @author 陈哈哈 (bitschen@163.com)
 */
public interface Tester {

    /**
     * 根据Options选项，检查输入参数是否符合检查规则
     *
     * @param opts  Options, Nullable
     * @param value 输入参数， Nullable
     * @return 是否符合检查规则
     * @throws Exception 如果检查过程出错，抛出异常
     */
    boolean test(Options opts, String value) throws Exception;

    ////

    /**
     * 检查选项
     */
    class Options extends HashMap<String, Object> {

        public Options(int initialCapacity) {
            super(initialCapacity);
        }

        public Options field(String name, Object value) {
            put(name, value);
            return this;
        }

        @SuppressWarnings("unchecked")
        public <T> T option(String name) {
            return (T) this.get(name);
        }

        public static Options of(String name, Object value) {
            final Options opts = new Options(1);
            opts.put(name, value);
            return opts;
        }

        public static Options from(Map<String, Object> data) {
            final Options opts = new Options(data.size());
            opts.putAll(data);
            return opts;
        }
    }
}
