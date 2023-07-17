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
 * 公司信息
 * @TableName company
 */
@TableName(value ="company")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company implements Serializable {
    /**
     * 公司代号
     */
    @TableId
    private Integer cId;

    /**
     * 公司名称
     */
    private String cName;

    /**
     * 法定代表人
     */
    private String representativeName;

    /**
     * 公司地址
     */
    private String cAddress;

    /**
     * 单位：万
     */
    private Integer registeredCapital;

    /**
     * 联系电话
     */
    private Long cPhone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}