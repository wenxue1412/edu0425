package edu0425.spring.demo;

public class Chopstick extends Thread{
	public Integer id[] = {1,2,3,4};
	
	private boolean taken[] = {false,false,false,false};
	
	public synchronized void take(int i) throws InterruptedException {
		while(taken[i] == true) {
			wait();
		}
		taken[i] = true;
	
	}
	public synchronized void drop(int i) {
		taken[i]=false;
		notifyAll();
	}
	
}