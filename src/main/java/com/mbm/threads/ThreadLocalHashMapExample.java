package com.mbm.threads;

import java.util.HashMap;
import java.util.Hashtable;

public class ThreadLocalHashMapExample {

	private static final ThreadLocal<HashMap<String, String>> hmap = ThreadLocal.withInitial(HashMap::new);

	public static void main(String[] args) throws InterruptedException {

		Runnable task = () -> {
			HashMap<String, String> localMap = hmap.get();
			localMap.put(Thread.currentThread().getName(), "TestData");
			System.out.println(Thread.currentThread().getName() + " " + localMap);
		};
		Thread t1 = new Thread(task, "Thread_1");
		Thread t2 = new Thread(task, "Thread_2");
		t1.start();
		t2.start();

		Hashtable<Integer, String> table = new Hashtable<>();
		Thread t3 = new Thread(() -> {
			for (int i = 1; i <= 5; i++) {
				table.put(i, "value " + i);
				System.out.println("Thread_3 added " + i);
			}
		});

		Thread t4 = new Thread(() -> {
			for (int i = 6; i <= 10; i++) {
				table.put(i, "value " + i);
				System.out.println("Thread_4 added " + i);
			}
		});

		t3.start();
		t4.start();
		t3.join();
		t4.join();
		System.out.println("Final Hash Table " + table);
	}

}
