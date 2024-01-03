package com.medical.record.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.medical.record.annoation.EncryptField;
import com.medical.record.util.AESUtils;
import lombok.Data;

import java.util.Date;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/23 4:10 下午
 */
@Data
public class MedicalRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    @EncryptField
    private String name;

    private Integer sex;

    private Integer age;

    @EncryptField
    private String description;

    @EncryptField
    private String doctorName;

    @EncryptField
    private String historyDescription;

    @EncryptField
    private String drug;

    private Date createTime;

    private Date updateTime;
}
