package com.medical.record.util;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/23 8:24 下午
 */
public class Response {

    private static final Integer SUCCESS_CODE = 200;
    private static final Integer ERROR_CODE = -1;

    private final int code;
    private final Object data;
    private final String msg;

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public Response(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static Response ok(){
        return new Response(SUCCESS_CODE, null, "成功");
    }

    public static Response ok(Object data){
        return new Response(SUCCESS_CODE, data, "成功");
    }

    public static Response fail(String msg){
        return new Response(ERROR_CODE, null, msg);
    }

    public static Response fail(Integer code, String msg){
        return new Response(code, null, msg);
    }
}
