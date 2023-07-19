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
 * 货物信息
 * @TableName cargo
 */
@TableName(value ="cargo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cargo implements Serializable {
    /**
     * 货物快递号
     */
    @TableId
    private Long gId;

    /**
     * 发件人
     */
    private String adresser;

    /**
     * 收件人
     */
    private String consignee;

    /**
     * kg
     */
    private Integer weight;

    /**
     * 发件地
     */
    private String departurePlace;

    /**
     * 收获地
     */
    private String reachPlace;

    /**
     * 公司代号
     */
    private Integer cId;

    /**
     * 1:运输中2:仓库中
     */
    private Integer gType;

    /**
     * 如果在仓库中储存的地方
     */
    private String sName;

    /**
     * 如果在运输中在什么车上
     */
    private String vId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}