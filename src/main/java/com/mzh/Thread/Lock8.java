package com.mzh.Thread;

import java.util.concurrent.TimeUnit;

/**
 * 多线程的8锁
 * 1.标准访问，请问先打印邮件还是访问短信？		答案：邮件
 * 2.邮件方法里面，新增暂停4秒钟的方法，打印的邮件还是短信？  答案：邮件
 * 3.新增普通的hello方法，先打印的邮件还是hello？   答案：hello
 * 4.有两个手机，请问先打印邮件还是短信？   答案：短信
 * 5.两个静态同步方法，同一部手机，请问先打印邮件还是短信？	答案：邮件
 * 6.两个静态同步方法，同一部手机，请问先打印邮件还是短信？	答案：邮件
 * 7.1个静态同步方法，一个普通同步方法，一部手机，请问先打印邮件还是短信？	答案：短信
 * 8.1个静态同步方法，一个普通同步方法，二部手机，请问先打印邮件还是短信？	答案：短信
 * 
 * 
 * 
 * 
 * 总结：
 * 1~2题目结论
 * 一个对象里面如果有多个synchronized方法，某一时刻内，
 * 只要一个线程去调用其中的一个 synchronized方法了，
 * 其他的线程只能等待，换句话来说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
 * 锁的是当前对象this,被锁定后其他的线程都不能进入到当前对象的其他synchronized方法
 * 
 * 3题目结论
 * 加个普通方法后发现和同步锁无关
 * 
 * 4题目结论
 * 换成两个对象后，不是同一把锁了，情况立刻变化。
 * 
 * 题目5
 * 都换成静态同步方法后，情况又变化了
 * 若是普通同步方法，new    	this,具体的一部部手机，所有的普通同步方法用都是同一把锁---实例对象本省
 * 若是静态同步方法，static 	Class，唯一的一个模板
 * 
 * 
 * 题目6
 * synchronized是实现同步的基础：Java中的每一个对象都可以作为锁
 * 具体的表现一下3个形式
 * 对于普通同步方法，锁的是当前实例对象。它等同于   对于同步方法块，锁是synchronized括号配置的对象
 * 对于静态同步方法，锁的是当前类的Class对象本身
 * 
 * 
 * 
 * 当一个线程试图访问同步代码时它首先必须等到锁，退出或者抛出异常必须释放锁。
 * 
 * 题目7
 * 所有的普通同步方法用的都是同一把锁----实例本身，就是new出来的具体的实例对象本身
 * 也就是说如果一个实例对象的普通同步方法获取锁后，该实例对象的其他同步方法必须等待获取锁的方法释放锁后才能获取锁
 * 可是别的实例对象的普通同步方法因为跟该实例对象的普通同步方法用的不同的锁，所以不用等待该实例对象已获取的普通
 * 同步方法释放锁就可以获取自己的锁
 * 
 * 
 * 题目8
 * 所有的静态同步方法用的也是同一把锁----类对象本身，就是我们说过的唯一模板Class
 * 具体实例对象this和唯一模板class，这两把锁是两个不同的对象，所有静态同步方法与普通同步方法之间是不会有竞态条件的
 * 但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放后才能获取锁
 */



class Phone{
	
	public static synchronized void sendEmail() throws Exception {
		TimeUnit.SECONDS.sleep(4);
		System.out.println("发邮件");
	}
	public synchronized void sendSMS() throws Exception{
		System.out.println("发短信");
	}
	public void sayHello() {
		System.out.println("hello");
	}
}

public class Lock8 {
	public static void main(String[] args) {
		Phone phone = new Phone();
		Phone phone2 = new Phone();
		
		new Thread(() -> {
			try {
				phone.sendEmail();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"线程A").start();
		
		//保证第一个线程先执行，睡眠0.1秒
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		
		
		new Thread(() -> {
			try {
				//phone.sendSMS();
				//phone.sayHello();
				 phone2.sendSMS();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"线程B").start();
	}
}



