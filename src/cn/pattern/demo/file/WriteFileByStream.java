package cn.pattern.demo.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/** 
* @author yin.huang 
* @date 2016年10月17日 下午5:17:35 
*/
public class WriteFileByStream {
	public static void main(String[] args) throws Exception{
		File file = new File("E:/2.txt");
		if(file.exists()){
			OutputStream out  = new FileOutputStream(file);
			String str = "javascript--";
			byte[] b = str.getBytes();
			out.write(b);
			out.close();
		}
	}
}