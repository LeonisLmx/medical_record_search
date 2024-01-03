package com.medical.record.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.medical.record.mapper.EncryptRecordMapper;
import com.medical.record.mapper.MedicalRecordMapper;
import com.medical.record.model.EncryptRecord;
import com.medical.record.model.MedicalRecord;
import com.medical.record.request.MedicalRecordRequest;
import com.medical.record.config.DynamicSearchableEncryption;
import com.medical.record.annoation.EncryptField;
import com.medical.record.request.MedicalRecordSearchRequest;
import com.medical.record.response.MedicalRecordResponse;
import com.medical.record.util.AESUtils;
import com.medical.record.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/23 4:12 下午
 */
@Slf4j
@RestController
@RequestMapping("/medical/record")
public class MedicalRecordController {

    @Resource
    private MedicalRecordMapper medicalRecordMapper;

    @Resource
    private DynamicSearchableEncryption dynamicSearchableEncryption;

    @Resource
    private EncryptRecordMapper encryptRecordMapper;

    /**
     * 新增记录
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/insert")
    public Response insertRecord(@RequestBody MedicalRecordRequest request) throws Exception {
        MedicalRecord record = new MedicalRecord();
        record.setAge(Integer.parseInt(request.getAge()));
        record.setSex(Integer.parseInt(request.getSex()));
        record.setDescription(dynamicSearchableEncryption.encrypt(request.getDescription()));
        record.setName(dynamicSearchableEncryption.encrypt(request.getName()));
        record.setHistoryDescription(dynamicSearchableEncryption.encrypt(request.getHistoryDescription()));
        record.setDoctorName(dynamicSearchableEncryption.encrypt(request.getDoctorName()));
        record.setDrug(dynamicSearchableEncryption.encrypt(request.getDrug()));
        medicalRecordMapper.insert(record);
        return Response.ok();
    }

    /**
     * 记录查询
     * @param name
     * @param sex
     * @param description
     * @param drug
     * @return
     * @throws Exception
     */
    @PostMapping("/search")
    public Response search(@RequestBody MedicalRecordSearchRequest request) throws Exception {
        QueryWrapper<MedicalRecord> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(request.getSex())){
            wrapper.eq("sex", AESUtils.decryptRequest(request.getSex()));
        }
        if (!StringUtils.isEmpty(request.getName())){
            wrapper.eq("name", dynamicSearchableEncryption.encryptRequest(AESUtils.decryptRequest(request.getName())));
        }
        if (!StringUtils.isEmpty(request.getDescription())){
            wrapper.eq("description", dynamicSearchableEncryption.encryptRequest(AESUtils.decryptRequest(request.getDescription())));
        }
        if (!StringUtils.isEmpty(request.getDrug())){
            wrapper.eq("drug", dynamicSearchableEncryption.encryptRequest(AESUtils.decryptRequest(request.getDrug())));
        }
        List<MedicalRecord> list = medicalRecordMapper.selectList(wrapper);
        List<MedicalRecordResponse> results = new ArrayList<>();
        for (MedicalRecord record : list) {
            MedicalRecordResponse res = new MedicalRecordResponse();
            res.setId(record.getId() + "");
            res.setAge(record.getAge() + "");
            res.setSex(record.getSex() + "");
            res.setDescription(dynamicSearchableEncryption.decrypt(record.getDescription()));
            res.setName(dynamicSearchableEncryption.decrypt(record.getName()));
            res.setDrug(dynamicSearchableEncryption.decrypt(record.getDrug()));
            res.setHistoryDescription(dynamicSearchableEncryption.decrypt(record.getHistoryDescription()));
            res.setDoctorName(dynamicSearchableEncryption.decrypt(record.getDoctorName()));
            res.setCreateTime(record.getCreateTime().getTime() + "");
            res.setUpdateTime(record.getUpdateTime().getTime() + "");
            results.add(res);
        }
//        for (MedicalRecord record : list) {
//            record.setDescription(dynamicSearchableEncryption.decrypt(record.getDescription()));
//            if (StringUtils.isEmpty(description) || record.getDescription().contains(description)) {
//                record.setName(dynamicSearchableEncryption.decrypt(record.getName()));
//                if (StringUtils.isEmpty(name) || record.getName().contains(name)) {
//                    record.setDrug(dynamicSearchableEncryption.decrypt(record.getDrug()));
//                    if (StringUtils.isEmpty(drug) || record.getDrug().contains(drug)) {
//                        record.setHistoryDescription(dynamicSearchableEncryption.decrypt(record.getHistoryDescription()));
//                        record.setDoctorName(dynamicSearchableEncryption.decrypt(record.getDoctorName()));
//                        result.add(record);
//                    }
//                }
//            }
//        }
        return Response.ok(results);
    }

    /**
     * 记录更改
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    public Object update(
            @RequestParam("id")String id,
            @RequestBody MedicalRecordRequest request) throws Exception {
        MedicalRecord record = medicalRecordMapper.selectById(Long.parseLong(AESUtils.decryptRequest(id)));
        if (record == null){
            return Response.fail("记录不存在");
        }
        if (!StringUtils.isEmpty(request.getDescription())){
            encryptRecordMapper.delete(new QueryWrapper<EncryptRecord>().eq("hash_id",record.getDescription()));
            record.setDescription(dynamicSearchableEncryption.encrypt(request.getDescription()));
        }
        if (!StringUtils.isEmpty(request.getDrug())){
            encryptRecordMapper.delete(new QueryWrapper<EncryptRecord>().eq("hash_id",record.getDrug()));
            record.setDrug(dynamicSearchableEncryption.encrypt(request.getDrug()));
        }
        if (!StringUtils.isEmpty(request.getDoctorName())){
            encryptRecordMapper.delete(new QueryWrapper<EncryptRecord>().eq("hash_id",record.getDoctorName()));
            record.setDoctorName(dynamicSearchableEncryption.encrypt(request.getDoctorName()));
        }
        if (!StringUtils.isEmpty(request.getHistoryDescription())){
            encryptRecordMapper.delete(new QueryWrapper<EncryptRecord>().eq("hash_id",record.getHistoryDescription()));
            record.setHistoryDescription(dynamicSearchableEncryption.encrypt(request.getHistoryDescription()));
        }
        if (!StringUtils.isEmpty(request.getName())){
            encryptRecordMapper.delete(new QueryWrapper<EncryptRecord>().eq("hash_id",record.getName()));
            record.setName(dynamicSearchableEncryption.encrypt(request.getName()));
        }
        if (!StringUtils.isEmpty(request.getAge())){
            record.setAge(Integer.parseInt(request.getAge()));
        }
        if (!StringUtils.isEmpty(request.getSex())){
            record.setSex(Integer.parseInt(request.getSex()));
        }
        medicalRecordMapper.updateById(record);
        return Response.ok();
    }

    /**
     * 记录删除
     * @param id
     * @return
     * @throws IllegalAccessException
     */
    @PostMapping("/delete")
    public Object delete(@RequestParam(value = "id")String id) throws IllegalAccessException {
//        MedicalRecord record = medicalRecordMapper.selectById(id);
//        if (record == null){
//            return Response.fail("记录不存在");
//        }
//        for (Field field : MedicalRecord.class.getDeclaredFields()) {
//            if (field.isAnnotationPresent(EncryptField.class)){
//                field.setAccessible(true);
//                log.info("delete by field {} value {}",field.getName(),field.get(record));
//                encryptRecordMapper.delete(new QueryWrapper<EncryptRecord>().eq("hash_id", field.get(record)));
//            }
//        }
        medicalRecordMapper.deleteById(Long.parseLong(AESUtils.decryptRequest(id)));
        return Response.ok("success");
    }
}
