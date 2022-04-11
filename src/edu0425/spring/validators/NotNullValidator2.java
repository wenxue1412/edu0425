package edu0425.spring.validators;


import edu0425.common.util.CheckIsNull;

import edu0425.spring.interfaces.NotNull2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
 
/**
 * ������the validator of NotNull
 * @author
 */
/*@Component*/
public class NotNullValidator2 implements ConstraintValidator<NotNull2, Object> {
    @SuppressWarnings("unused")
	private NotNull2 annotation;

 
    @Override
    public void initialize(NotNull2 constraintAnnotation) {
    }
 
    @Override
    public boolean isValid(Object s, ConstraintValidatorContext constraintValidatorContext) {
//        return this.annotation.allowBlank() ?
//                Pattern.matches(patternNoChineseOrBlank, s) : Pattern.matches(patternNoChinese, s);
    	try {
            if(CheckIsNull.notNull(s)) {
            	return true;
            };
            return false;
        }catch (Exception e){
            return false;
        }
    	
    }

}
