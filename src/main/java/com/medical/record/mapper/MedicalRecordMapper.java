package com.medical.record.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.medical.record.model.MedicalRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/23 4:12 下午
 */
@Mapper
@Repository
public interface MedicalRecordMapper extends BaseMapper<MedicalRecord> {
}
