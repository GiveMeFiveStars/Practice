package com.system.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;

/**
 * 
 * @TableName employee
 */
@TableName(value ="employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer eId;

    /**
     * 
     */
    private Integer cId;

    /**
     * 
     */
    private String eName;

    /**
     * 
     */
    private Long ePhone;

    /**
     * 
     */
    private String eSex;

    /**
     * 
     */
    private BigDecimal salary;

    /**
     * 
     */
    private String position;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
