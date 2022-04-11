package edu0425.spring.interfaces;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


import edu0425.spring.validators.NotNullValidator2;

//@Component
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { NotNullValidator2.class })
public @interface NotNull2 {
	//@AliasFor(annotation = Component.class)
	String value();
	
	String message() default "{notNull validator failed}";
	
	Class<?>[] groups() default {};
	 
    Class<? extends Payload>[] payload() default {};
}
