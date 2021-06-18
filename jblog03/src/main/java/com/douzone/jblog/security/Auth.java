package com.douzone.jblog.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
	//public String value() default "USER";
public @interface Auth {
	public String role() default "GUEST";
	public String id() default ""; 
}
