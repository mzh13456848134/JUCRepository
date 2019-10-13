package com.mzh.Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程并发1.0版本
 * 
 * 题目：三个销售员  	卖出		100张票
 * 目的：如何写出企业级的多线程程序
 * 
 * 高内聚，低耦合
 * 口诀：高内聚低耦合：线程		操作 	   	资源类
 * @author Administrator
 *
 */

public class SaleTicketConcurrent {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		
		
		//多线程并发1.0版本
		//使用匿名内部类
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 120; i++) {
					ticket.Sale();
				}
			}
		},"销售员1").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 120; i++) {
					ticket.Sale();
				}
			}
		},"销售员2").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 120; i++) {
					ticket.Sale();
				}
			}
		},"销售员3").start();*/
		
		
		//多线程并发2.0版本
		//使用lambda表达式
		new Thread(() -> {for (int i = 1; i <= 120; i++)ticket.Sale();},"销售窗口1").start(); 
		new Thread(() -> {for (int i = 1; i <= 120; i++)ticket.Sale();},"销售窗口2").start(); 
		new Thread(() -> {for (int i = 1; i <= 120; i++)ticket.Sale();},"销售窗口3").start(); 
		
	}
}
class Ticket{
	private Integer ticketNum = 100;
	
	
	//1.0同步锁
	/*public synchronized void Sale() {
		if(ticketNum > 0) {
			System.out.println(Thread.currentThread().getName() + ",正在卖第:" + (ticketNum--) + "张票！,还剩:" + ticketNum + "张票！！");
		}
	}*/
	
	
	//2.0Lock锁
	Lock lock = new ReentrantLock();
	public void Sale() {
		
		lock.lock();
		try {
			if(ticketNum > 0) {
				System.out.println(Thread.currentThread().getName() + ",正在卖第:" + (ticketNum--) + "张票！,还剩:" + ticketNum + "张票！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
	}
}
