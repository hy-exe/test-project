package cn.pattern.demo.generics;

public class GenercisTest {
	
	public static <T> void display(T t) {
        System.out.println(t.getClass());
    }
	
	public static void main(String[] args){
		display(111);
	}
	
}
