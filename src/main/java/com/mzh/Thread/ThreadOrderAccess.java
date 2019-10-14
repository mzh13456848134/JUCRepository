package com.mzh.Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程之间按顺序调用，实现A->B->C 三个线程启动，要求如下
 * 
 * 
 * AA打印5次，BB打印10次，CC打印15次 接着 AA打印5次，BB打印10次，CC打印15次 .....来10轮
 * 
 * 
 * 1.高内聚低耦合，先后才能操作资源类 2.判断/干活/通知 3.多线程交互，必须要防止线程的虚假唤醒，也判断只用while，不能用if
 * 4.注意判断标志志位的更新
 * 
 */
public class ThreadOrderAccess {
	public static void main(String[] args) {
		ShareResource shareResource = new ShareResource();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				shareResource.print();
			}
		}, "线程A:").start();
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				shareResource.print();
			}
		}, "线程B:").start();
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				shareResource.print();
			}
		}, "线程C:").start();
	}
}

class ShareResource {
	private int flag = 1;// 1:A，2：B，3：C
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();
	
	

	public void print() {
		lock.lock();

		try {
			if(flag == 1) {
				c1.signal();
			}
			while(Thread.currentThread().getName().equals("线程A:")) {
				try {
					c1.await();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				for (int i = 1; i <= 5; i++) {
					System.out.println(Thread.currentThread().getName() + "AA");
				}
					c2.signal();
					flag = 2;
				break;
			}
			while(Thread.currentThread().getName().equals("线程B:")) {
				try {
					c2.await();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				for (int i = 1; i <= 10; i++) {
					System.out.println(Thread.currentThread().getName() + "BB");
				}
					c3.signal();
				break;
			}
			while(Thread.currentThread().getName().equals("线程C:")) {
				try {
					c3.await();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				for (int i = 1; i <= 15; i++) {
					System.out.println(Thread.currentThread().getName() + "CC");
				}
					c1.signal();
				break;
			}
		} finally {
			lock.unlock();
		}

	}

	/*
	 * public void print5() { lock.lock();
	 * 
	 * try { //判断 while(flag != 1) { //系统要停止 try { c1.await(); } catch
	 * (InterruptedException e) { e.printStackTrace(); } } //干活 for (int i = 1; i <=
	 * 5; i++) { System.out.println(Thread.currentThread().getName() + "AA"); }
	 * 
	 * //通知 flag=2; c2.signal(); } finally { lock.unlock(); } } public void
	 * print10() { lock.lock();
	 * 
	 * try { //判断 while(flag != 2) { //系统要停止 try { c2.await(); } catch
	 * (InterruptedException e) { e.printStackTrace(); } } //干活 for (int i = 1; i <=
	 * 10; i++) { System.out.println(Thread.currentThread().getName() + "BB"); }
	 * 
	 * //通知 flag=3; c3.signal(); } finally { lock.unlock(); } } public void
	 * print15() { lock.lock();
	 * 
	 * try { //判断 while(flag != 3) { //系统要停止 try { c3.await(); } catch
	 * (InterruptedException e) { e.printStackTrace(); } } //干活 for (int i = 1; i <=
	 * 15; i++) { System.out.println(Thread.currentThread().getName() + "CC"); }
	 * 
	 * //通知 flag=1; c1.signal(); } finally { lock.unlock(); } }
	 */
}
