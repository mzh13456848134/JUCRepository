package com.mzh.Thread;

import java.util.concurrent.CountDownLatch;

/**
 * 同步计数器
 * 教室里有7个人,7个人全都走了才能锁门,不能把人锁在教室里
 *
 */
public class CountDownLatchDemo {
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(6);
		for (int i = 1; i <= 5; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "\t离开教室");
				//每次减一个线程
				countDownLatch.countDown();
			}, String.valueOf(i)).start();
		}
		
		try {
			//当线程减到0才能执行之后的线程,否则一直阻塞
			countDownLatch.await();
			//当线程没有减到0,等待了3秒,还阻塞,则直接执行
			//countDownLatch.await(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "\t关闭门");
	}
}
