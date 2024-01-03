package com.medical.record.request;

import com.medical.record.util.AESUtils;
import lombok.Data;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/23 8:32 下午
 */
@Data
public class UserRequest {

    private String userName;

    private String password;

    public String getUserName() {
        return AESUtils.decryptRequest(userName);
    }

    public String getPassword() {
        return AESUtils.decryptRequest(password);
    }
}
