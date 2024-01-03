package com.medical.record.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/23 8:44 下午
 */
@Data
public class UserRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String userName;

    private String password;

    private Date createTime;

    private Date updateTime;
}
