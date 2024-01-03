package com.medical.record.response;

import com.medical.record.util.AESUtils;
import lombok.Data;

import java.util.Date;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/26 11:07 下午
 */
@Data
public class MedicalRecordResponse {

    private String id;

    private String name;

    private String sex;

    private String age;

    private String description;

    private String doctorName;

    private String historyDescription;

    private String drug;

    private String createTime;

    private String updateTime;

    public void setId(String id) throws Exception {
        this.id = AESUtils.encrypt(id);
    }

    public void setName(String name) throws Exception {
        this.name = AESUtils.encrypt(name);
    }

    public void setSex(String sex) throws Exception {
        this.sex = AESUtils.encrypt(sex);
    }

    public void setAge(String age) throws Exception {
        this.age = AESUtils.encrypt(age);
    }

    public void setDescription(String description) throws Exception {
        this.description = AESUtils.encrypt(description);
    }

    public void setDoctorName(String doctorName) throws Exception {
        this.doctorName = AESUtils.encrypt(doctorName);
    }

    public void setHistoryDescription(String historyDescription) throws Exception {
        this.historyDescription = AESUtils.encrypt(historyDescription);
    }

    public void setDrug(String drug) throws Exception {
        this.drug = AESUtils.encrypt(drug);
    }

    public void setCreateTime(String createTime) throws Exception {
        this.createTime = AESUtils.encrypt(createTime);
    }

    public void setUpdateTime(String updateTime) throws Exception {
        this.updateTime = AESUtils.encrypt(updateTime);
    }
}
