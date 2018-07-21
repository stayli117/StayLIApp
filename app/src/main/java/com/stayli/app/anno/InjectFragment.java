package com.stayli.app.anno;

import com.stayli.app.base.BaseFragment;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by yhgao on 2018/2/9.
 */

@Documented
@Target(value={TYPE})
@Retention(RUNTIME)
public @interface InjectFragment {
     Class value() default BaseFragment.class;
}
