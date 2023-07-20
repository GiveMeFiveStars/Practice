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
 * 车辆信息
 * @TableName vehicle
 */
@TableName(value ="vehicle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle implements Serializable {
    /**
     * 车牌号
     */
    @TableId
    private String vId;

    /**
     * 车辆类型
     */
    private String vType;

    /**
     * 1:well 2:scrap
     */
    private Integer vStatus;

    /**
     * 所属仓库
     */
    private Integer stashId;

    /**
     * 驾驶员
     */
    private Integer eId;

    /**
     * 许可证编号
     */
    private Integer licenseId;

    /**
     * 有效期限
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tLimit;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}