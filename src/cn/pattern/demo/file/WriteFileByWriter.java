package cn.pattern.demo.file;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

/** 
* @author yin.huang 
* @date 2016年10月17日 下午5:17:14 
*/
public class WriteFileByWriter {
	public static void main(String[] args) throws Exception{
		File file = new File("E:/2.txt");
		if(file.exists()){
			Writer w = new FileWriter(file);
			String str = "javascript--";
			w.write(str);
			System.out.println("success");
			w.close(); 
		}	
	}
}