package com.system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
    private Integer eId;

    /**
     * 公司代号
     */
    private Integer cId;

    /**
     * 员工姓名
     */
    private String eName;

    /**
     * 联系电话
     */
    private Long ePhone;

    /**
     * 员工性别
     */
    private String eSex;

    /**
     * 工资
     */
    private BigDecimal salary;

    /**
     * 职位
     */
    private String position;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}