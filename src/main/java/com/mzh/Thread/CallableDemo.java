package com.mzh.Thread;

import java.util.concurrent.FutureTask;

/**
 *多线程中，第3种获得多线程的方式 
 *第1中 实现Runnable接口
 *第2种 继承Thread类
 *第三种 实现Callable接口
 *
 *
 *Runnable与Callable的区别：
 *实现方法的方法名不同
 *是否有返回值
 *是否抛异常
 *
 *
 *为什么要抛异常，为什么要返回值
 *这些业务是为了要么成功，要么失败
 *但是有些业务是成功的就成功，抛异常的可以再次处理，重新执行
 */
/*class MyThread implements Callable<String>{

	@Override
	public String call() throws Exception {
		
	}
	
}*/

/*class MyThread1 implements Runnable{

	@Override
	public void run() {
		
	}
	
}*/


/**
 *将两个没有关系的接口有联系，使用适配器模式 
 *
 *为什么要用Callable??
 *使用Callable使用多线程的时候，分别操作，需要汇总其总结果，可以先汇总先执行完的线程，最后一个线程走完了，再总的汇总，不用因为一个线阻塞了后续的所有线程
 *
 *FureTask的get方法要放在最后，否则会阻塞线程
 */
public class CallableDemo {
	public static void main(String[] args) {
		//MyThread myThread = new MyThread();
		//将Callable和Runnable有关联的类
		FutureTask<String> futureTask = new FutureTask<String>(() -> {
			Thread.sleep(4000);
			System.out.println(Thread.currentThread().getName() + "\t come in");
			return "王宝宝";
		});
		
		
		new Thread(futureTask, "线程A").start();
		
		System.out.println(Thread.currentThread().getName() + "\t主线程");
		
		
		//FureTask的get方法要放在最后，否则会阻塞线程
		try {
			String result = futureTask.get();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
