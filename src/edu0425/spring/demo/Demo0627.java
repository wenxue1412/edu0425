package edu0425.spring.demo;

import java.util.Stack;

public class Demo0627 {
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5, 6, 7 };
		moveArray(a, 5);
		int[] b = { 5, 5, 5, 5, 5, 5, 5, 5 };
		findNums(b);
		int[] c = { 3, 5, 7, 8 };
		getTwoSum(c, 15);
		String edu = "13+52-*6/";
		System.out.println(edu + "的结果是：" + rpn(edu));
		System.out.println(calcT(1, 5));
		System.out.println(calcT(2, 5));
		System.out.println(calcT(3, 5));
		System.out.println(calcT(3, 6));
		QueueDemo qd = new QueueDemo(10);
		qd.enqueue(3);
		qd.enqueue(9);
		qd.enqueue(21);
		qd.enqueue(33);
		qd.enqueue(5);
		qd.print();
		System.out.println("出队："+qd.dequeue());
		qd.print();
		System.out.println("出队："+qd.dequeue());
		qd.print();
		NodeDemo node = new NodeDemo(5);
		node.insert(10);
		node.insert(15);
		node.insert(11);
		node.insert(12);
		node.insert(3);
		node.insert(8);
		//中序遍历
		node.inorder();
	}

	private static void moveArray(int[] a, int k) {
		int[] b = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			if (i < a.length - k) {
				b[i] = a[i + k];
			} else {
				b[i] = a[i + k - a.length];
			}
		}

		for (int i : b) {
			System.out.print(i);
		}
		System.out.println();
	}

	private static void findNums(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[Math.abs(nums[i]) - 1] >= 0) {
				nums[Math.abs(nums[i]) - 1] = -nums[Math.abs(nums[i]) - 1];
			}

		}
		for (int j = 0; j < nums.length; j++) {
			if (nums[j] > 0) {
				System.out.println(j + 1);

			}
		}
	}

	private static void getTwoSum(int[] nums, int target) {
		int[] result = new int[2];
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					result[0] = i;
					result[1] = j;
					break;
				}
			}
		}
		System.out.print(result[0] + "," + result[1]);

	}

	private static int rpn(String equ) {
		Stack<Integer> s = new Stack<Integer>();
		char[] charArray = equ.toCharArray();
		int sum = 0;
		for (int i = 0; i < charArray.length; i++) {
			if (isNum(charArray[i])) {
				s.push(Character.getNumericValue(charArray[i]));
			} else {
				int x = 0;
				int y = 0;
				switch (charArray[i]) {
				case '+':
					sum = s.pop() + s.pop();
					s.push(sum);
					break;
				case '-':
					x = s.pop();
					y = s.pop();
					sum = y - x;
					s.push(sum);
					break;
				case '*':
					sum = s.pop() * s.pop();
					s.push(sum);
					break;
				case '/':
					x = s.pop();
					y = s.pop();
					sum = y / x;
					s.push(sum);
					break;
				default:
					break;
				}
			}
		}
		return (int) s.pop();
	}

	private static boolean isNum(char c) {
		if (c < 48 || c > 57)
			return false;
		return true;

	}

	private static final int[] C = { 1, 2, 4, 7 };

	private static int calcT(int n, int m) {//n=3 ,m=6
		int cnt = 0;
		for (int i = n; i >= 0; i--) {//i=3->0;
			if (m % C[i] != 0) {
				cnt = cnt + (m / C[i]);
				if (m >C[i]) {
					m = m % C[i];
				} 
			} else {
				cnt = cnt + (m / C[i]);
				m = 0;
			}
		}

		return cnt;

	}
	private static int calcT2(int n , int m) {
		if(n<0||m<0) {
			return 0;
		}
		int cnt = 0;
		if(m>=C[n]) {
			cnt = m/C[n];
			if(m%C[n]!=0) {
				cnt += calcT2(n-1, m%C[n]);
			}
		}else {
			return calcT2(n-1,m);
		}
		return cnt;
	}
	
}
