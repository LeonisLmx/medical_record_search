package com.medical.record.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.medical.record.model.UserRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/23 8:44 下午
 */
@Mapper
@Repository
public interface UserRecordMapper extends BaseMapper<UserRecord> {

    @Select({
            "select * from user_record where user_name like '%s${userName}%s'"
    })
    List<UserRecord> vagueSelect(@RequestParam("userName")String userName);
}
