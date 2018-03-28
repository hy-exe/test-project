package cn.pattern.demo.test;
/** 
* @author yin.huang 
* @date 2016年10月14日 下午5:50:46 
*/
public class Test2 {
	
	Test2(){
		System.out.println("Test2 Constructor....");
	}
	Test2(String name){
		this();
		System.out.println("Test2 name"+name);
	}
	public void getName(){
		//   this();  //报错 方法内部不能调用构造器 
	}
	
	public static void main(String args[]){
        int[] array = {1,0,1,2,4,2,3,3,6,3,4,4,5};
        int[] output = new int[array.length];
         
        output[0] = array[0];
         
        int current = 0;
        int forward = 1;
        int count = 0;
         
        while (forward <= array.length-1){       
        	for(int i=0;i<array.length;i++){
        		if (array[current] == array[forward]){
        			current = forward;
        			forward ++;
        		}
        		else {           
        			current = forward;
        			count ++;   
        			output[count] = array[forward];
        			forward ++;
        		}
        	}
        }       
         
        for(int i=0; i<count+1; i++){
            System.out.print(output[i]);
             
            if (i != count)
                System.out.print(",");
        }
    }
}
