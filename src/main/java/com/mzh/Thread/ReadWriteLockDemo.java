package com.mzh.Thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *多个线程同时读一个资源类没有任何问题， 所以为了满足并发量，读取共享资源应该可以同时进行。
 *但是
 *如果有一个线程想去写共享资源来，就不应该再有其他线程可以对该资源进行读或写
 *小结：	
 *		读-读能共存
 *		读-写不能共存 
 *		写-写不能共存
 *
 */

class MyCache{
	private volatile Map<String,String> map = new HashMap<>();
	
	private Lock lock = new ReentrantLock();
	
	
	//写入操作
	public void put(String key,String value) {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t写入开始");
			map.put(key, value);
			System.out.println(Thread.currentThread().getName() + "\t写入结束");
		} finally {
			lock.unlock();
		}
	}
	
	
	//读取操作
	public void get(String key) {
		lock.lock();
		try {
			String result = null;
			System.out.println(Thread.currentThread().getName() + "\t读取开始");
			result = map.get(key);
			System.out.println(Thread.currentThread().getName() + "\t读取结束,结果为：" + result);
		} finally {
			lock.unlock();
		}
	}
}


public class ReadWriteLockDemo {
	public static void main(String[] args) {
		MyCache myCache = new MyCache();
		
		//模拟10个线程并发写
		for (int i = 1; i <= 10; i++) {
			int finali = i;
			new Thread(() -> {
				myCache.put(finali+"", finali+"");
			}, String.valueOf(i)).start();;
		}
		//模拟10个线程并发读
		for (int i = 1; i <= 10; i++) {
			int finali = i;
			new Thread(() -> {
				myCache.get(finali+"");
			}, String.valueOf(i)).start();;
		}
	}
}


