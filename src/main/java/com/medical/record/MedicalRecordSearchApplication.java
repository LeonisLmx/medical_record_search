package com.medical.record;

import com.medical.record.util.TokenFilter;
import jdk.nashorn.internal.parser.Token;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
public class MedicalRecordSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalRecordSearchApplication.class, args);
    }
}
