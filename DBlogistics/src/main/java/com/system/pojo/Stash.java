package com.system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 仓库信息
 * @TableName stash
 */
@TableName(value ="stash")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stash implements Serializable {
    /**
     * 仓库id
     */
    @TableId
    private Integer stashId;

    /**
     * 仓库名字
     */
    private String sName;

    /**
     * 面积
     */
    private Integer sArea;

    /**
     * 仓库地址
     */
    private String sAdress;

    /**
     * 所属公司
     */
    private Integer cId;

    /**
     * 仓库管理人
     */
    private String eName;

    /**
     * 租赁时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sTime;

    /**
     * 截止时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}