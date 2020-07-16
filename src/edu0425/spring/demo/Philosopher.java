package edu0425.spring.demo;

public class Philosopher extends Thread{
	private int i;
	
	private String name[] = {"A","B","C","D"};
	
	private Chopstick left;
	
	private Chopstick right;
	
	private Integer thinkFactor;
	
	public Philosopher(int i,Chopstick left,Chopstick right) {
		this.i = i;
		this.left = left;
		this.right = right;
	}
	
	private void think(int i) {
		System.out.println(name[i]+" is thinking");
		try {
			sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		while(!Thread.interrupted()) {
			System.out.println(name[i]+" feels hungry");
			try {
				right.take(i);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name[i]+" picks up "+ right.id[i]);
			try {
				left.take((i+1)%4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name[i]+" picks up "+ left.id[(i+1)%4]);
			System.out.println(name[i]+" is eating");
			think(i);
			right.drop(i);
			System.out.println(name[i]+" puts "+ right.id[i]);
			left.drop((i+1)%4);
			System.out.println(name[i]+" puts "+ left.id[(i+1)%4]);
		}
	}
	public static void main(String[] args) {
		Chopstick left = new Chopstick();
		Chopstick right = new Chopstick();
		new Philosopher(0, left, right).start();
		new Philosopher(1, left, right).start();
		new Philosopher(2, left, right).start();
		new Philosopher(3, left, right).start();
	}
}
