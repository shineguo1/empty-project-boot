package pers.gxj.quickstart.boot.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;
import java.util.Objects;

/**
 * @author xinjie_guo
 * @version 1.0.0 createTime:  2022/2/14 14:07
 */
public class MysqlMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createdBy = this.getFieldValByName("createdBy", metaObject);
        String username = Objects.nonNull(createdBy) ? String.valueOf(createdBy) : "SYSTEM";
        this.setFieldValByName("createdBy", username, metaObject);
        this.setFieldValByName("createdAt", new Date(), metaObject);
        this.setFieldValByName("updatedBy", username, metaObject);
        this.setFieldValByName("updatedAt", new Date(), metaObject);
        //有值，则写入
        if (metaObject.hasGetter("deleteFlag")) {
            this.setFieldValByName("deleteFlag", false, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updatedBy = this.getFieldValByName("updatedBy", metaObject);
        String username = Objects.nonNull(updatedBy) ? String.valueOf(updatedBy) : "SYSTEM";
        this.setFieldValByName("updatedBy", username, metaObject);
        this.setFieldValByName("updatedAt", new Date(), metaObject);
    }
}

