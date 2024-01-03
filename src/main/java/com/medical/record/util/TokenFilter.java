package com.medical.record.util;

import com.alibaba.fastjson.JSON;
import com.medical.record.config.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * @author urey.liu
 * @description Token 过滤器
 * @date 2023/5/24 11:12 上午
 */
@Slf4j
public class TokenFilter implements Filter {

    private static final List<String> WHITE_LIST = Arrays.asList("/medical/user/register","/medical/user/login");

    private static final String ACCESS_TOKEN = "accessToken";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();
        if(WHITE_LIST.contains(path) || "OPTIONS".equals(request.getMethod())){
            chain.doFilter(request, response);
            return;
        }
        //token为空
        String token = getFromRequestHead(request, ACCESS_TOKEN);
        if (StringUtils.isEmpty(token)) {
            writeErrorResponse(response, token);
            return;
        }

        if (!JwtTokenUtil.validateToken(token)){
            writeErrorResponse(response, token);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    public void writeErrorResponse(HttpServletResponse response, String token) {
        Response res = Response.fail(0,"token不存在或已过期");
        writeResponse(response, JSON.toJSONString(res));
    }

    public void writeResponse(HttpServletResponse response, String data) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            writer = response.getWriter();
            writer.print(data);
        } catch (IOException e) {
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public String getFromRequestHead(HttpServletRequest request, String what) {
        String value = request.getHeader(what);
        return value;
    }
}
