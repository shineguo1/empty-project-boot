package pers.gxj.quickstart.boot.common.util;


import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * @author xinjie_guo
 * @date 2022/5/12 18:29
 */
public class CollectionUtils extends com.baomidou.mybatisplus.core.toolkit.CollectionUtils {

    /**
     * 安全地把集合c加进集合collection
     *
     * @param collection 主集合
     * @param c          增加的集合
     * @param <T>        集合泛型
     */
    public static <T> void addAll(Collection<T> collection, Collection<T> c) {
        if (Objects.nonNull(collection) && isNotEmpty(c)) {
            collection.addAll(c);
        }
    }

    @SuppressWarnings("unchecked")
    public static void addAll(Collection collection, Object[] elements) {
        if (Objects.nonNull(collection) && Objects.nonNull(elements)) {
            Collections.addAll(collection, elements);
        }
    }


}
