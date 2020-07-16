package edu0425.spring.demo;

public class MyThread extends Thread{
	
	private SharedObj o;
	
	public MyThread (SharedObj o) {
		this.o = o;
	}
	
	public void run() {
		int n = 0;
		while(n<10) {
			n++;
			o.inc_ij();
		}
	}

}
