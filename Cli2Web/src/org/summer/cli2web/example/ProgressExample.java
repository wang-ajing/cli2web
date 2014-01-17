package org.summer.cli2web.example;

import java.util.Scanner;

public class ProgressExample {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println("progress: " + i + "/100");
			System.out.println("info: processing " + i);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			if (i % 5 == 0) {
				System.out.println("input what you want to jump: ");
				Scanner scanner = new Scanner(System.in);
				i = scanner.nextInt();
			}
		}
	}
}
