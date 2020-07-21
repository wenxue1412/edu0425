package edu0425.spring.demo;

public class Chopstick extends Thread{
	public Integer id[] = {1,2,3,4};
	
	private boolean taken[] = {false,false,false,false};
	
	public synchronized void take(int i) throws InterruptedException {
		while(getTaken()[i]){
			wait();
		}
		getTaken()[i] = true;
	
	}
	public synchronized void drop(int i) {
		getTaken()[i]=false;
		notifyAll();
	}
	public boolean[] getTaken() {
		return taken;
	}
	public void setTaken(boolean taken[]) {
		this.taken = taken;
	}
	
}