package com.mzh.Lambda;


/**
 * Lambda Express(前提是函数式接口，只有一个抽象方法)
 * 
 *使用Lambda口诀 ：拷贝括号，写死右箭头，落地大括号
 *注解@FunctionInterface
 *default方法可以有多个，该方法是必须包含方法体的
 *static方法可以有多个，该方法是必须包含方法体的
 *
 */
@FunctionalInterface //标记一个接口是一个函数式接口，被该注解标记的接口，只能有且仅有一个抽象方法
interface Foo{
	public int add(int a,int b);
	default int sub(int a,int b) {
		return a - b ;
	}
	public static int mul(int a,int b) {
		return a * b;
	}
	
}



public class LambdaExpressDemo {
	public static void main(String[] args) {
		Foo foo = (int a,int b) -> {
			System.out.println("调用了Foo类中add方法");
			return a + b;
		};
		System.out.println(foo.add(5, 6));
		
		System.out.println(foo.sub(5, 6));
		
		System.out.println(Foo.mul(5, 6));
	}
}
