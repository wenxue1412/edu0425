package edu0425.spring.demo;

public class Demo66 {

	public static void main(String[] args) {
		// fact(n)=1 * 2 * 3 *... * n-i * n = fact(n-1)*n
		// fact(1)=1
		System.out.println(fact(5));
		System.out.println(fib(10));
		System.out.println(gcd(888, 54));
		int[][] a = {{1,2,3},{10,20,30}};
		int[][]ext_a = extend_sum(extend_sum(a));
		print_2D(ext_a);
	}

	private static int fact(int n) {
		if (n == 1) {
			return 1;
		} else {
			return fact(n - 1) * n;
		}
	}

	private static int fib(int n) {

		// 0 1 1 2 3 5 8 13 21
		// 结束条件 n==0 和 n == 1
		// fib(n)=fib(n-2)+fib(n-1)
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return fib(n - 2) + fib(n - 1);
		}
	}

	private static int gcd(int a, int b) {
		if (a % b == 0) {
			return b;
		} else {
			return gcd(b, a % b);
		}
	}

	private static int[][] extend_sum(int[][] a) {
		int[][] rs = new int[a.length + 1][a[0].length + 1];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				rs[i][j]=a[i][j];
				rs[i][a[i].length]=rs[i][a[i].length]+a[i][j];
				rs[a.length ][j]=rs[a.length][j]+a[i][j];
				rs[a.length][a[0].length]=rs[a.length][a[0].length]+a[i][j];
			}

		}
		return rs;
	}
	private static void print_2D(int[][]a) {
		for(int i = 0;i<a.length;i++) {
			for(int j=0;j<a[i].length;j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
}
