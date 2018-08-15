package com.rq.practice.framework.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Fragment使用的Scope
 * @author rock you
 * @version [1.0.0, 2018.8.15]
 */
@Scope // 注明是Scope
@Documented // 标记在文档
@Retention(RetentionPolicy.RUNTIME) // 运行时级别
public @interface FragmentScope {
}
