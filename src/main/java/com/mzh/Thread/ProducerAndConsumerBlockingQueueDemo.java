package com.mzh.Thread;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 使用阻塞队列实现生产者消费者的问题
 * 
 *
 */
class ProducerAndConsumer {
	private int number = 0;
	private ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(1);

	public void increment() {
		try {
			arrayBlockingQueue.put("a");
			number++;
			System.out.println(Thread.currentThread().getName() + ":" + number);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void decrement() {
		try {
			arrayBlockingQueue.take();
			number--;
			System.out.println(Thread.currentThread().getName() + ":" + number);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

	}
}

public class ProducerAndConsumerBlockingQueueDemo {
	public static void main(String[] args) {
		/*ExecutorService executorService = Executors.newFixedThreadPool(4);
		
		ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer();
		
		for (int i = 1; i <= 20; i++) {
			executorService.submit(() -> {
				producerAndConsumer.increment();
				producerAndConsumer.decrement();
			});
		}*/
		
		ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
				producerAndConsumer.increment();
			}
		}, "线程A").start();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
				producerAndConsumer.decrement();
			}
		}, "线程B").start();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
				producerAndConsumer.increment();
			}
		}, "线程C").start();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
				producerAndConsumer.decrement();
			}
		}, "线程D").start();
		
		
	}
}
