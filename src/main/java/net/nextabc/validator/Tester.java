package net.nextabc.validator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public interface Tester {

    boolean test(Options opts, String value) throws Exception;

    ////

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
