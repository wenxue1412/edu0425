package edu0425.spring.interfaces;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNull {
 
  String msg() default "字段不能为空";
   
}
