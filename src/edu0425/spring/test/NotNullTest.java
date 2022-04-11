package edu0425.spring.test;


import org.junit.Test;

import edu0425.common.util.CheckIsNull;
import edu0425.spring.vo.DeptInfo;

public class NotNullTest {

	@Test
    public void testNotNull() {
        try {
            DeptInfo dept = new DeptInfo();
            //dept.setDeptno(1);
            dept.setDname("���");
            CheckIsNull.doValidator(dept);
        } catch (Exception e) {
        	CheckIsNull.handlerExcpetion(e);
        }
    }


}
