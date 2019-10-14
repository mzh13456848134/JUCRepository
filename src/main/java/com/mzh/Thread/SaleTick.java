package com.mzh.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Tickets{
	private int ticknum = 100;
	private Lock lock = new ReentrantLock();
	
	public void sale() {
		lock.lock();
		try {
			while(ticknum > 0) {
				System.out.println(Thread.currentThread().getName() + "正在卖第" + ticknum-- + ",还剩" + ticknum);
			}
		} finally {
			lock.unlock();
		}
	}
	
}

public class SaleTick {
	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		Tickets tickets = new Tickets();
		
		try {
			for (int i = 1; i <= 200 ; i++) {
				executorService.execute(() -> {
					
					tickets.sale();
				});
			}
		} catch (Exception e) {
		}finally {
			executorService.shutdown();
		}
		
	}
}
