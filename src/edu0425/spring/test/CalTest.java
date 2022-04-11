package edu0425.spring.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu0425.spring.demo.CalNumbers;
import edu0425.spring.service.DeptService;
import edu0425.spring.service.impl.DeptServiceImpl;

public class CalTest {

	private CalNumbers calNumbers= new CalNumbers();
	private DeptService deptService = new DeptServiceImpl();
	@Test
	public void addTest() {

		assertEquals(5,calNumbers.add(3,2));
		assertEquals(3,calNumbers.div(3,1));
	}
	@Test
	public void countTest() {

		assertEquals(6,deptService.getDeptCount().intValue());
	}

}
