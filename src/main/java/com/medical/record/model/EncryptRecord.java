package com.medical.record.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/23 4:30 下午
 */
@Data
public class EncryptRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String hashId;

    private String encryptKey;

    private String encryptData;
}
