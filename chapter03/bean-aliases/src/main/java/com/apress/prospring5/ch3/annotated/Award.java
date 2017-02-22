package com.apress.prospring5.ch3.annotated;

import org.springframework.core.annotation.AliasFor;

/**
 * Created by iuliana.cosmina on 2/19/17.
 */
public @interface Award {

	@AliasFor("prize")
	String[] value() default {};

	@AliasFor("value")
	String[] prize() default {};
}
