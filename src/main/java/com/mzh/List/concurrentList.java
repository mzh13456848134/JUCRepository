package com.mzh.List;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * 1.故障现象
 * 		如果多线程使用ArrayList会出现java.util.concurrentModifycationException
 * 2.导致原因
 * 		当多线程中有读又有写的操作的时候
 * 3.解决方法
 * 	使用Vector
	使用Collections.synchronizedList
	使用CopyOnWriteArrayList   原理：读写分离       ，写时复制
 * 4.优化建议
 *
 */
public class concurrentList {
	public static void main(String[] args) {
		//1.0基本ArrayList线程不安全
		//List<String> list = new ArrayList<String>();
		//2.0使用Vector
		//List<String> list = new Vector<String>();
		//3.0将ArrayList变成一个线程安全的
		//List<String> list = Collections.synchronizedList(new ArrayList<>());
		//4.0使用CopyOnWriteArrayList
		
		
		//错误的写法
		/*List<String> list = new ArrayList<String>();
		
		for (int i = 0; i < 30; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					list.add(UUID.randomUUID().toString().substring(0, 6));
					System.out.println(list);
				}
			}, "A").start();
		}*/
		
		
		//正确的写法
		List<String> list = new CopyOnWriteArrayList<String>();

		for (int i = 0; i < 30; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					list.add(UUID.randomUUID().toString().substring(0, 6));
					System.out.println(list);
				}
			}, "A").start();
		}
	}
}
