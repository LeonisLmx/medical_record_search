package com.medical.record.annoation;

import java.lang.annotation.*;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/23 6:09 下午
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptField {

    boolean value() default true;
}
