package cn.pattern.demo.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**	 字节流能处理所有类型的数据（如图片、avi等），而字符流只能处理字符类型的数据。
 * 字节流：InputStream  OutputStream
 * 字符流：Reader  Writer  （纯文本优先考虑使用）
 */

public class ReadFileByReader {
	static{
	 
	}
  
  public static void main(String[] args) throws Exception{
		File file = new File("E:/1.txt");
		if(file.exists()){
			Reader reader = new InputStreamReader(new FileInputStream(file));
			int len;
			
			//这里是字符数组，不是字节数组
			char [] buf = new char[1024];
			while((len = reader.read(buf)) != -1)
			{
				System.out.println(new String(buf,0,len));								
			}
			reader.close();
		}
	}
	
}