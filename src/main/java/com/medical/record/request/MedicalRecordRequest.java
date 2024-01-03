package com.medical.record.request;

import com.medical.record.util.AESUtils;
import lombok.Data;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/23 4:15 下午
 */
@Data
public class MedicalRecordRequest {

    private String name;

    private String sex;

    private String age;

    private String description;

    private String doctorName;

    private String historyDescription;

    private String drug;

    public String getName() {
        return AESUtils.decryptRequest(name);
    }

    public String getSex() {
        return AESUtils.decryptRequest(sex);
    }

    public String getAge() {
        return AESUtils.decryptRequest(age);
    }

    public String getDescription() {
        return AESUtils.decryptRequest(description);
    }

    public String getDoctorName() {
        return AESUtils.decryptRequest(doctorName);
    }

    public String getHistoryDescription() {
        return AESUtils.decryptRequest(historyDescription);
    }

    public String getDrug() {
        return AESUtils.decryptRequest(drug);
    }
}
