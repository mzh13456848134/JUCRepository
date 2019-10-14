package com.mzh.Thread;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯
 * 具体：3个停车位6个车来停放，先进来3辆车，剩下3辆车阻塞这，其中有车走了，通知其他车进来
 *
 */
public class SemaphoreDemo {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);//模拟3个停车位
		
		for (int i = 1; i <= 6; i++) {
			new Thread(() -> {
				boolean flag = false;
				try {
					//抢夺占用
					semaphore.acquire();
					flag = true;
					System.out.println(Thread.currentThread().getName() + "\t抢到车位");
					//停放1-5秒钟
					TimeUnit.SECONDS.sleep(new Random().nextInt(5) + 1);
					System.out.println(Thread.currentThread().getName() + "\t离开车位----------");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					if(flag == true) {
						//释放
						semaphore.release();
					}
				}
			}, String.valueOf(i)).start();
		}
	}
}
