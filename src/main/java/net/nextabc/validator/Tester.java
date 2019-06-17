package net.nextabc.validator;

import java.util.HashMap;

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

        @SuppressWarnings("unchecked")
        public <T> T option(String name) {
            return (T) this.get(name);
        }

        public static Options of(String name, Object value) {
            final Options opts = new Options(1);
            opts.put(name, value);
            return opts;
        }
    }
}
