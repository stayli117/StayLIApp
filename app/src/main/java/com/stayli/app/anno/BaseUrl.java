package com.stayli.app.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by yhgao on 2018/2/9.
 */

@Documented
@Target(value = {TYPE})
@Retention(RUNTIME)
public @interface BaseUrl {
    String value();
}
