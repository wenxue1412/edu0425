package edu0425.spring.interfaces;


import javax.validation.Constraint;
import javax.validation.Payload;

import edu0425.spring.validators.ChineseValidator;

import java.lang.annotation.*;
 
/**
 * validator 注解， 判断是否有中文
 * 属性 allowBlank ：是否允许为空。
 * allowBlank为true时，空字符串会通过校验，反之，allowBlank为false时，不会通过校验。默认为false。
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ChineseValidator.class})
public @interface NoChinese {
    String message() default "{chinese validator failed}";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 
    boolean allowBlank() default false;
 
}
