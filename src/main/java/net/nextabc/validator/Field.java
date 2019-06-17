package net.nextabc.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 陈哈哈 (bitschen@163.com)
 * @since 1.0.0
 */
public class Field {

    private String key;

    final List<Scheme> schemes = new ArrayList<>();

    Source source;

    public Field addSchemes(Scheme... schemes) {
        this.schemes.addAll(Arrays.asList(schemes));
        return this;
    }

    public Field setSource(Source source) {
        this.source = source;
        return this;
    }

    public Field setKey(String key) {
        this.key = key;
        return this;
    }

    ///

    void setSourceByKey(Map<String, String> map) {
        if (!Texts.isNullOrEmpty(this.key)) {
            setSource(() -> map.get(key));
        }
    }
}
