package edu0425.spring.validators;


import org.springframework.util.StringUtils;

import edu0425.spring.interfaces.NoChinese;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
 
/**
 * 描述：the validator of Chinese
 * @author
 */
//@Component
public class ChineseValidator implements ConstraintValidator<NoChinese, String> {
    @SuppressWarnings("unused")
	private NoChinese annotation;
    private String patternNoChinese;
    @SuppressWarnings("unused")
	private String patternNoChineseOrBlank;
 
    @Override
    public void initialize(NoChinese constraintAnnotation) {
        this.annotation = constraintAnnotation;
        patternNoChinese = "[^\\u4e00-\\u9fa5]+";
        patternNoChineseOrBlank = "[^\\u4e00-\\u9fa5]*";
    }
 
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //return this.annotation.allowBlank() ?
        //        Pattern.matches(patternNoChineseOrBlank, s) : Pattern.matches(patternNoChinese, s);
        if (StringUtils.isEmpty(s))
            return true;
        if (!Pattern.matches(patternNoChinese, s)) {
            return false;
        } else {
            return Pattern.matches("[\\x00-\\xff]+",s);
        }
    }
}