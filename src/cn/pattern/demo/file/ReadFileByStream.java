package cn.pattern.demo.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/** 
* @author yin.huang 
* @date 2016年10月17日 下午5:06:35 
*/
public class ReadFileByStream {
	public static void main(String[] args) throws Exception{
		File file = new File("E:/1.txt");
		if(file.exists()){
			 InputStream in=new FileInputStream(file);
			 int len;
			 byte[] b=new byte[1024];
			 while((len =in.read(b))!=-1){
				 System.out.println(new String(b,0,len));
			 }
			 in.close();
		}	
	}
}	