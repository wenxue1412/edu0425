package edu0425.spring.test;

import edu0425.spring.demo.MyThread;
import edu0425.spring.demo.SharedObj;
import edu0425.spring.demo.Sync_SharedObj;

public class ThreadTest {
	
	public static void main(String[] args) {
	//	SharedObj o = new SharedObj();
		SharedObj o = new Sync_SharedObj();
		MyThread mt = new MyThread(o);
		mt.start();
		MyThread mt2 = new MyThread(o);
		mt2.start();
	}

}
