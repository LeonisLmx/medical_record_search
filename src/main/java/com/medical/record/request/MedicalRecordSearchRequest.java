package com.medical.record.request;

import com.medical.record.util.AESUtils;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/26 11:30 下午
 */
@Data
public class MedicalRecordSearchRequest {

    private String name;

    private String sex;

    private String description;

    private String drug;
}
