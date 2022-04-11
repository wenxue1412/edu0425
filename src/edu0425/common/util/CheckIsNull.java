package edu0425.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import edu0425.spring.exception.CustBusinessException;
import edu0425.spring.interfaces.NotNull2;

public class CheckIsNull {

	public static <T> void doValidator(T t) {
		// �õ������class
		Class<?> clazz = t.getClass();
		// �õ�class�����ԣ�
        Field[] fields = clazz.getDeclaredFields();
        // ��������
        for(Field field:fields){
        	// �жϸ������Ƿ�NotNullע������
            NotNull2 notNull = field.getDeclaredAnnotation(NotNull2.class);
            if(null!=notNull){
            	// �����Ϊ�գ������getValue����
                Object value = getValue(t,field.getName());
                if(!notNull(value)){
                    throwExcpetion(notNull.value());
                }
            }
        }
	}
	
	public static <T> Object getValue(T t,String fieldName){
        Object value = null;
        try {
        	// �õ���bean��������Ϣ
            BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
            // �õ���bean����������
            PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor property:props){
            	// ��������
                if(fieldName.equals(property.getName())){
                	// ������Ե�name��֮ǰ�����еı�NotNull���ε�������ͬ
                    Method method = property.getReadMethod();
                    value = method.invoke(t,new Object[]{});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
     
    public static boolean notNull(Object value){
        if(null==value){
            return false;
        }
        if(value instanceof String && isEmpty((String)value)){
            return false;
        }
        if(value instanceof List && isEmpty((List<?>)value)){
            return false;
        }
        return null!=value;
    }
     
    public static boolean isEmpty(String str){
        return null==str || str.isEmpty();
    }
     
    public static boolean isEmpty(List<?> list){
        return null==list || list.isEmpty();
    }
     
    public static void throwExcpetion(String msg){
        if(null!=msg){
            throw new CustBusinessException(msg);
        }
    }
     
    public static String handlerExcpetion(Exception e){
        if(e instanceof CustBusinessException){
            return ((CustBusinessException)e).getMessage();
        }else{
            return "调用失败";
        }
    }
}
