package com.medical.record.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.medical.record.model.EncryptRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/23 4:31 下午
 */
@Mapper
@Repository
public interface EncryptRecordMapper extends BaseMapper<EncryptRecord> {
}
