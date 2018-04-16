package com.dn.andemo.aspect;

import java.util.Collection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dn.andemo.util.BeanUtil;

@Component
@Aspect
public class SetFieldValueAspect {

	@Autowired
	private BeanUtil beanUtil;

	@Around("@annotation(com.dn.andemo.annotation.NeedSetFieldValue)")
	public Object doSetFieldValue(ProceedingJoinPoint pjp) throws Throwable {
		Object ret = pjp.proceed();
		// 设置属性值
		if (ret instanceof Collection) {
			this.beanUtil.setFieldValueForCollection((Collection) ret);
		} else {
			// 不是集合，也需要设置属性值，，beanUtil中还提供一个设置单个对象属性值的方法
		}

		return ret;
	}
}
