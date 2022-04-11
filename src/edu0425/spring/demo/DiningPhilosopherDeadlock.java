package edu0425.spring.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DiningPhilosopherDeadlock {
	@SuppressWarnings("unused")
	public static void main(String[] args) { 
		 try { 
		 int think = 0; 
		 int size = 4; 
		 ExecutorService exec = Executors.newCachedThreadPool(); 
		 Chopstick[] sticks = new Chopstick[size]; 
		 String[] philosophers = { "A", "B", "C", "D" }; 
		 for (int i = 0; i < size; i++) 
		 //sticks[i] = new Chopstick().id[i+1]; 
		 //for (int i = 0; i < size; i++) 
		 //exec.execute(new Philosopher(philosophers[i], sticks[i], sticks[(i + 1) % size], think)); 
		 TimeUnit.SECONDS.sleep(5); 
		 exec.shutdownNow(); 
		 } catch (Exception e) { 
		 e.printStackTrace(); 
		 } 
		 }

}
