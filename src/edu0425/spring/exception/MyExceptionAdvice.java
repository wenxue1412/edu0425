package edu0425.spring.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

@ControllerAdvice
public class MyExceptionAdvice extends AbstractHandlerExceptionResolver{
	@ExceptionHandler(BindException.class)
    @ResponseBody
    public String handler(Exception e) {
        StringBuilder message = new StringBuilder("参数不合法!");
        BindException methodArgumentNotValidException = (BindException) e;
        List<ObjectError> objectErrors = methodArgumentNotValidException.getBindingResult().getAllErrors();
        if (!objectErrors.isEmpty()) {
            message = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                if (objectError instanceof FieldError) {
                    FieldError fieldError = (FieldError) objectError;
                    String fieldName = fieldError.getField();
                    String defaultMessage = fieldError.getDefaultMessage();
                    message.append("[" + fieldName + "]" + "-[" + defaultMessage + "]").append(",");
                } else {
                    message.append(objectError.getDefaultMessage()).append(",");
                }
            }
        }
        return message.toString();
    }

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8"); //如果是json数据,需要设置为("text/javascript;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		return null;
	}
}
