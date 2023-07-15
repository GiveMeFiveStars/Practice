package com.system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 机电信息
 * @TableName machine
 */
@TableName(value ="machine")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Machine implements Serializable {
    /**
     * 机电编号
     */
    @TableId
    private Integer mId;

    /**
     * 名称
     */
    private String mName;

    /**
     * 型号
     */
    private String modelNum;

    /**
     * 1:Settings that are still in use 2:Scrap equipment
     */
    private Integer mStatus;

    /**
     * 仓库的id
     */
    private Integer stashId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}