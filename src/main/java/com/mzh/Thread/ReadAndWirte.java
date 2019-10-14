package com.mzh.Thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
	private volatile Map<String, String>  map = new HashMap<String, String>();
	private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
	//private ReentrantLock lock = new ReentrantLock();
	public void put(String key,String value) {
		reentrantReadWriteLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t" + "写入开始");
			map.put(key, value);
			System.out.println(Thread.currentThread().getName() + "\t" + "写入结束");
			
		} finally {
			reentrantReadWriteLock.readLock().unlock();
		}
	}
	public void get(String key) {
		reentrantReadWriteLock.readLock().lock();
		try {
			String result = null;
			System.out.println(Thread.currentThread().getName() + "\t" + "读取开始");
			result = map.get(key);
			System.out.println(Thread.currentThread().getName() + "\t" + "读取结束"  + result);
			
		} finally {
			reentrantReadWriteLock.readLock().unlock();
		}
	}
}

public class ReadAndWirte {
	public static void main(String[] args) {
		MyCache myCache = new MyCache();
		
		for (int i = 1; i <= 10; i++) {
			int finali = i;
			new Thread(() -> {
				myCache.put(finali+"",finali+"");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}, i+"").start();
		}
		for (int i = 1; i <= 10; i++) {
			int finali = i;
			new Thread(() -> {
				myCache.get(finali+"");
			}, i+"").start();
		}
	}
}
