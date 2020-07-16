package edu0425.spring.demo;

public class Philosopher extends Thread{
	private int i;
	
	private String name[] = {"A","B","C","D"};
	
	private Chopstick chop;
	
	private Integer thinkFactor = 0;
	
	public Philosopher(int i,Chopstick chop) {
		this.i = i;
		this.chop = chop;
	}
	
	private void think(int i) {
		System.out.println(name[i]+" is thinking");
		try {
			sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		thinkFactor++;
		while(thinkFactor==2) {
			interrupt();
		}
		
	}
	public synchronized void run() {
		while(!Thread.interrupted()) {
			System.out.println(name[i]+" feels hungry");
			try {
				chop.take(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name[i]+" picks up "+ chop.id[i]);
			try {
				chop.take((i+1)%4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name[i]+" picks up "+ chop.id[(i+1)%4]);
			System.out.println(name[i]+" is eating");
			think(i);
			chop.drop(i);
			System.out.println(name[i]+" puts "+ chop.id[i]);
			chop.drop((i+1)%4);
			System.out.println(name[i]+" puts "+ chop.id[(i+1)%4]);
		}
	}
	public static void main(String[] args) {
		Chopstick chop = new Chopstick();
		new Philosopher(0, chop).start();
		new Philosopher(1, chop).start();
		new Philosopher(2, chop).start();
		new Philosopher(3, chop).start();
	}
}
