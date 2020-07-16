package edu0425.spring.demo;

public class Sync_SharedObj extends SharedObj {
	public synchronized void inc_ij() {
		i = i + 1;
		int newj = j + 1;
		try {
			Thread.sleep(500);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		j = newj;
		System.out.println(String.format("i=%s,j=%s", i, j));
	}

	@Override
	public String toString() {
		return "SharedObj [i=" + i + ",j=" + j + "]";
	}

}
