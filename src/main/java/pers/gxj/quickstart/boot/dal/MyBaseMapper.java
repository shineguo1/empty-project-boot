package pers.gxj.quickstart.boot.dal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.gxj.quickstart.boot.common.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author xinjie_guo
 * @version 1.0.0 createTime:  2022/2/15 11:33
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {

    default T selectOne(T query) {
        QueryWrapper<T> qw = getQueryWrapper(query);
        //设置limit，避免全量查询；设置limit 2，为了进行唯一性校验。
        qw.last(" limit 2");
        return selectOne(qw);
    }

    default List<T> selectList(T query) {
        QueryWrapper<T> qw = getQueryWrapper(query);
        return selectList(qw);
    }

    /**
     * 备注：例如trino查iceberg不支持limit offset, rows语法，所以不能复用select(query,offset,rows)实现。
     *
     * @param query 查询条件
     * @param limit 限制条数
     * @return
     */
    default List<T> selectList(T query, int limit) {
        QueryWrapper<T> qw = getQueryWrapper(query);
        qw.last(" limit  " + limit);
        return selectList(qw);
    }

    default List<T> selectList(T query, int offset, int rows) {
        QueryWrapper<T> qw = getQueryWrapper(query);
        qw.last(" limit " + offset + ", " + rows);
        return selectList(qw);
    }


    static <T> QueryWrapper<T> getQueryWrapper(T query) {
        QueryWrapper<T> qw = new QueryWrapper<>();
        List<Field> fields = ReflectionUtils.getAnnotationField(query.getClass(), TableField.class);
        for (Field field : fields) {
            String jdbcField = ReflectionUtils.getAnnotationValue(field, TableField.class, TableField::value);
            Object fieldValue = ReflectionUtils.getFieldValue(field, query);
            if (fieldValue != null) {
                qw.eq(jdbcField, fieldValue);
            }
        }
        return qw;
    }
}
