package cn.pattern.demo.test;

import java.math.BigDecimal;

/** 
* @author yin.huang 
* @date 2016年10月14日 下午4:36:25 
*/
public class Test {
	public static void main(String[] args) {
		Parent parent = new Parent();
		Child child = new Child();
		System.out.println(parent.getName());
		System.out.println(child.getName());
		BigDecimal a = new BigDecimal("2.00");
		BigDecimal b = new BigDecimal("1.10");
		System.out.println(a.subtract(b));
		
		String[] num = null;
		String sLine = "101494|360103660318444|2008/06/17|暗黑世界|1292.0|3085.76|2778.28|912.91|106.0|||";
		num  = sLine.split("\\|",-1);
		for(int i=0;i<num.length;i++){
			System.out.println(i + ": " + num[i]);
		}
	}
}	

class Parent{
	public static String getName(){
		return "Parent";
	}
}

class Child extends Parent{
	public static String getName(){
		return "Child";
	}
}
