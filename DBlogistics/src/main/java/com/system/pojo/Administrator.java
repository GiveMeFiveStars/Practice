package com.system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 管理员信息
 * @TableName administrator
 */
@TableName(value ="administrator")
@Data
public class Administrator implements Serializable {
    /**
     * 管理员id
     */
    @TableId
    private String aUsername;

    /**
     * 管理员密码
     */
    private String aPassword;

    /**
     * 管理员姓名
     */
    private String aName;

    /**
     * 管理员电话
     */
    private Long aPhone;

    /**
     * 公司姓名
     */
    private String cName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}