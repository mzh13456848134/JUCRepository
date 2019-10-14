package com.mzh.Thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *BlockingQueue阻塞队列能替代生产者和消费者的wait和notify的通信
 *没有东西要生产，消费等待，东西满了不生产，要消费
 *
 */
public class BlockingQueueDemo {
	
	
	public static void main(String[] args) {
		//创建一个长度为3的数组结构的阻塞队列
		BlockingQueue<String> boolBlockingQueue = new ArrayBlockingQueue<>(3);
		
		try {
			
			//超时一类
			System.out.println(boolBlockingQueue.offer("a", 2L, TimeUnit.SECONDS));
			System.out.println(boolBlockingQueue.offer("b", 2L, TimeUnit.SECONDS));
			System.out.println(boolBlockingQueue.offer("c", 2L, TimeUnit.SECONDS));
			//System.out.println(boolBlockingQueue.offer("X", 2L, TimeUnit.SECONDS));
			System.out.println(boolBlockingQueue.poll(2L, TimeUnit.SECONDS));
			System.out.println(boolBlockingQueue.poll(2L, TimeUnit.SECONDS));
			System.out.println(boolBlockingQueue.poll(2L, TimeUnit.SECONDS));
			//System.out.println(boolBlockingQueue.poll(2L, TimeUnit.SECONDS));
			
			
			//阻塞的一类
			/*boolBlockingQueue.put("a");
			boolBlockingQueue.put("b");
			boolBlockingQueue.put("c");
			//boolBlockingQueue.put("X");
			boolBlockingQueue.take();
			boolBlockingQueue.take();
			boolBlockingQueue.take();
			boolBlockingQueue.take();*/
			
			//特殊值的一类
			/*System.out.println(boolBlockingQueue.offer("a"));
			System.out.println(boolBlockingQueue.offer("b"));
			System.out.println(boolBlockingQueue.offer("c"));
			//System.out.println(boolBlockingQueue.offer("X"));
			System.out.println(boolBlockingQueue.poll());
			System.out.println(boolBlockingQueue.poll());
			System.out.println(boolBlockingQueue.poll());
			//System.out.println(boolBlockingQueue.poll());
			System.out.println(boolBlockingQueue.peek());*/
			
			//抛出异常的一类
			/*System.out.println(boolBlockingQueue.add("a"));
			System.out.println(boolBlockingQueue.add("b"));
			System.out.println(boolBlockingQueue.add("c"));
			//System.out.println(boolBlockingQueue.add("X"));
			System.out.println(boolBlockingQueue.remove());
			System.out.println(boolBlockingQueue.remove());
			System.out.println(boolBlockingQueue.remove());
			//System.out.println(boolBlockingQueue.remove());
			System.out.println(boolBlockingQueue.element());*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
