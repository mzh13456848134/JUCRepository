package com.mzh.Thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：现在有两个线程，可以操作初始值为零的一个变量 实现一个线程对该变量加1，一个变成对该变量减1 实现交替，来10轮，变量初始值为0
 * 
 * 
 * 高内聚，低耦合，线程操作资源类
 * 
 * 1.判断 2.干活 3.通知
 * 
 * 小心，防止多线程的虚假唤醒，判断时候用while而不是if
 *
 * 
 * 
 * lock和synchronized有什么区别 wait和notify是object类中的方法
 * 
 * 
 *
 *
 *
 */

// 传统版本
/*
 * class AirConditioner{ private int number = 0;
 * 
 * public synchronized void increment() { //判断 if(number != 0) { try {
 * this.wait(); } catch (InterruptedException e) { e.printStackTrace(); } }
 * 
 * //干活 number++; System.out.println(Thread.currentThread().getName() + ":" +
 * number);
 * 
 * //通知 this.notifyAll();
 * 
 * }
 * 
 * public synchronized void decrement() { //判断 if(number == 0) { try {
 * this.wait(); } catch (InterruptedException e) { e.printStackTrace(); } }
 * 
 * //干活 number--; System.out.println(Thread.currentThread().getName() + ":" +
 * number);
 * 
 * //通知 this.notify();
 * 
 * } }
 */


//企业级版本
class AirConditioner {
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition c = lock.newCondition();

	public void increment() {
		lock.lock();
		try {
			// 判断
			while(number != 0) {
				try {
					c.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// 干活
			number++;
			System.out.println(Thread.currentThread().getName() + ":" + number);

			// 通知
			c.signalAll();
		} finally {
			lock.unlock();
		}

	}

	public  void decrement() {
		
		lock.lock();
		try {
			// 判断
			while(number == 0) {
				try {
					c.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// 干活
			number--;
			System.out.println(Thread.currentThread().getName() + ":" + number);

			// 通知
			c.signalAll();
		} finally {
			lock.unlock();
		}

	}
}

public class ThreadWaitAndNotify {
	public static void main(String[] args) {
		AirConditioner airConditioner = new AirConditioner();

		new Thread(() -> {
			for (int i = 1; i <= 20; i++) {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
				airConditioner.increment();
			}
		}, "线程A").start();

		new Thread(() -> {
			for (int i = 1; i <= 20; i++) {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
				airConditioner.decrement();
			}
		}, "线程B").start();

		new Thread(() -> {
			for (int i = 1; i <= 20; i++) {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
				airConditioner.increment();
			}
		}, "线程C").start();

		new Thread(() -> {
			for (int i = 1; i <= 20; i++) {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
				airConditioner.decrement();
			}
		}, "线程D").start();
	}
}
