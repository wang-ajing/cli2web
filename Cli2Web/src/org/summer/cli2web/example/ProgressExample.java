package org.summer.cli2web.example;

import java.util.Scanner;

public class ProgressExample {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println("progress: " + i + "/100");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (i % 5 == 0) {
				System.out.println("input any data you want: ");
				Scanner scanner = new Scanner(System.in);
				i = scanner.nextInt();
			}
		}
	}
}
