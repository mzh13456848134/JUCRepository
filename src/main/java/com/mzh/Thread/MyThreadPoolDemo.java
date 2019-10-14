package com.mzh.Thread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolDemo {
	public static void main(String[] args) {
		
		//自定义线程池
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				2, 
				5, 
				2L, 
				TimeUnit.SECONDS, 
				new ArrayBlockingQueue<>(3), 
				Executors.defaultThreadFactory(), 
				new ThreadPoolExecutor.CallerRunsPolicy());
		try {
			//模拟20个客户来银行办理业务
			for (int i = 1; i <= 20; i++) {
				threadPoolExecutor.submit(() -> {
					System.out.println(Thread.currentThread().getName() + "\t办理业务" + (new Random().nextInt(10)+1));
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			threadPoolExecutor.shutdown();
		}
		
		
		
		//固定个数线程的线程池
		//ExecutorService executorService = Executors.newFixedThreadPool(5); //一池固定线程
		//ExecutorService executorService = Executors.newSingleThreadExecutor(); //一池一线程
		/*ExecutorService executorService = Executors.newCachedThreadPool(); //一池n线程
		
		
		try {
			//模拟20个客户来银行办理业务
			for (int i = 1; i <=20; i++) {
				executorService.submit(() -> {
					System.out.println(Thread.currentThread().getName() + "\t办理业务" + (new Random().nextInt(10)+1));
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			executorService.shutdown();
		}*/
		
	}
}
