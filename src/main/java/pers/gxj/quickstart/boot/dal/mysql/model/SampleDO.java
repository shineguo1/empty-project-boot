package pers.gxj.quickstart.boot.dal.mysql.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import pers.gxj.quickstart.boot.dal.mysql.BaseDO;

/**
 * @author xinjie_guo
 * @date 2022/5/20 17:34
 */
@Data
@ApiModel(value = "SubGraph配置")
@TableName("T_CONFIG_SUBGRAPH")
public class SampleDO extends BaseDO {

    @TableField(value = "DELETE_FLAG", fill = FieldFill.INSERT)
    private Boolean deleteFlag;
}
