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
 * 地址信息
 * @TableName place
 */
@TableName(value ="place")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place implements Serializable {
    /**
     * 地点id
     */
    @TableId
    private Integer pId;

    /**
     * 名称
     */
    private String pName;

    /**
     * 省份
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}