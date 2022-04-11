package edu0425.spring.demo;

import java.util.ArrayList;
import java.util.List;

public class Demo0815 {
	public static void main(String[] args) {
		int arrays[] = {1, 2, 3, 4, 1, 5, 3, 6};
		isRepeat(arrays);
		for (int i = 0; i < isRepeat(arrays).length; i++) {
			System.out.print(isRepeat(arrays)[i] + ", ");
		}
		System.out.println();
		printNarcissisticNumber();
	}

	private static int[] isRepeat(int[] arrays) {
		// �ҵ��������ظ������ֲ�����
		int count = 0;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < arrays.length; i++) {
			for (int j = i + 1; j < arrays.length; j++) {
				if (arrays[i] == arrays[j]) {
					count++;
					list.add(arrays[i]);
					break;
				}
			}
		}
		int arr[] = new int[count];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = list.get(i);
		}
		return arr;
	}

	private static void printNarcissisticNumber() {
		// ��ӡˮ�ɻ���
		System.out.println("100-999�����е�ˮ�ɻ����У�");
		for (int i = 100; i < 1000; i++) {
			int a = i / 1 % 10;
			int b = i / 10 % 10;
			int c = i / 100 % 10;
			if (i == a * a * a + b * b * b + c * c * c) {
				System.out.println(i + " ");
			}
		}
	}
	
	
}
