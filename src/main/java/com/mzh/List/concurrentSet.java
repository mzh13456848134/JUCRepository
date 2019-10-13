package com.mzh.List;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class concurrentSet {
	public static void main(String[] args) {
		//错误的写法
		//多线程中使用HashSet是线程不安全，会导致java.uitl.ConcurrentModifycationException的异常
		/*Set<String> set = new HashSet<String>();
		
		for(int i=1;i<=30;i++) {
			new Thread(() -> {
				set.add(UUID.randomUUID().toString().substring(0,6));
				System.out.println(set);
			}, "A").start();
		}*/
		
		
		
		//正确的写法
		//能完全解决掉以上的错误，实现线程安全的ArrayList
		Set<String> set = new CopyOnWriteArraySet<String>();
		
		for (int i = 0; i <= 30; i++) {
			new Thread(() -> {
				set.add(UUID.randomUUID().toString().substring(0,6));
				System.out.println(set);
			}, "线程A").start();
		}
		
	}
}
