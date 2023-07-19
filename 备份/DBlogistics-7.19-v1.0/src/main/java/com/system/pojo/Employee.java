package com.system.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 员工信息
 * @TableName employee
 */
@TableName(value ="employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    /**
     * 员工id
     */
    @TableId
    @JSONField(ordinal = 1)
    private Integer eId;

    /**
     * 公司代号
     */
    @JSONField(ordinal = 2)
    private Integer cId;

    /**
     * 员工姓名
     */
    @JSONField(ordinal = 3)
    private String eName;

    /**
     * 联系电话
     */
    @JSONField(ordinal = 4)
    private Long ePhone;

    /**
     * 员工性别
     */
    @JSONField(ordinal = 5)
    private String eSex;

    /**
     * 工资
     */
    @JSONField(ordinal = 6)
    private BigDecimal salary;

    /**
     * 职位
     */
    @JSONField(ordinal = 7)
    private String position;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}