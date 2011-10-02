package org.tenxperts.eazzybus.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA.
 * User: vineeth
 * Date: 10/2/11
 * Time: 3:40 PM
 * To change this template use File | Settings | File Templates.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Service
@Transactional
public @interface TxService {

    String value() default "";
}
