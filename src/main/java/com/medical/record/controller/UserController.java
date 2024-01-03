package com.medical.record.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.medical.record.mapper.UserRecordMapper;
import com.medical.record.model.UserRecord;
import com.medical.record.request.UserRequest;
import com.medical.record.util.AESUtils;
import com.medical.record.config.JwtTokenUtil;
import com.medical.record.util.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/23 8:31 下午
 */
@RestController
@RequestMapping("/medical/user")
public class UserController {

    @Resource
    private UserRecordMapper userRecordMapper;

    /**
     * 用户注册
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/register")
    public Response registerUser(@RequestBody UserRequest request) throws Exception {
        String userName = AESUtils.encrypt(request.getUserName());
        List<UserRecord> userRecords = userRecordMapper.selectList(new QueryWrapper<UserRecord>().eq("user_name",userName));
        // 校验是否存在相同的加密字段
        if (userRecords != null && userRecords.size() > 0){
            return Response.fail("该用户已经注册");
        }
        UserRecord userRecord = new UserRecord();
        userRecord.setUserName(userName);
        userRecord.setPassword(AESUtils.encrypt(request.getPassword()));
        userRecordMapper.insert(userRecord);
        return Response.ok();
    }

    /**
     * 用户登录
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public Response login(@RequestBody UserRequest request) throws Exception {
        String userName = AESUtils.encrypt(request.getUserName());
        String password = AESUtils.encrypt(request.getPassword());
        List<UserRecord> userRecords = userRecordMapper.selectList(new QueryWrapper<UserRecord>()
                .eq("user_name",userName).eq("password", password));
        if (userRecords.isEmpty()){
            return Response.fail("账号或者密码错误");
        }
        // 生成 token
        String token = JwtTokenUtil.generateToken(userRecords.get(0));
        return Response.ok(token);
    }
}
