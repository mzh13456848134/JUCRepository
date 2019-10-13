package com.mzh.List;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class concurrentMap {
	public static void main(String[] args) {

		// 错误写法
		/*
		 * Map<String, String> map = new HashMap<String, String>();
		 * 
		 * for (int i = 0; i <= 30; i++) { new Thread(() -> {
		 * map.put(Thread.currentThread().getName(),
		 * UUID.randomUUID().toString().substring(0,6)); System.out.println(map); },
		 * i+"").start(); }
		 */

		
		// 正确写法
		Map<String, String> map = new ConcurrentHashMap<String,String>();

		for (int i = 0; i <= 30; i++) {
			new Thread(() -> {
				map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 6));
				System.out.println(map);
			}, i + "").start();
		}
	}
}
