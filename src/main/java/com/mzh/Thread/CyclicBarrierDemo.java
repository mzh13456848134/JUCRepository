package com.mzh.Thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 循环屏障
 *集齐7个七龙珠才能召唤神龙 
 *7个线程都到了，则才能接下去执行
 *
 */
public class CyclicBarrierDemo {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
			System.out.println(Thread.currentThread().getName() + "\t" +"集齐7龙珠召唤神龙");
		});
		
		for (int i = 1; i <= 6; i++) {
			int finali = i;
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "\t" + "收集到第" + finali +"个龙珠" );
				try {
					//如果达到7个线程，则可以执行cyclicBarrier内的线程方法，否则一致阻塞
					//cyclicBarrier.await();
					//如果没有达到7个线程，则抛出异常TimeoutException，阻塞超时异常
					cyclicBarrier.await(2, TimeUnit.SECONDS);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
	}
}
