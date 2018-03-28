package cn.pattern.demo.test;

import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.List;
import static java.lang.System.*;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
/** 
* @author yin.huang 
* @date 2016年11月15日 上午9:59:28 
* 1.import static java.lang.System.*是新的语法，将System中的所有静态成员进行引用，以后就可以直接使用out、in或err了
* 2.动态调用 脚本引擎必须实现Invocable接口才可以动态调用脚本语言中的方法 使用Invocable强转
*/
public class Test6 {
	public static void main(String[] args) {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("js");
		try { 
			/*运行js代码
			String script = "function say(){ return 'Hello World!!' }"; 
			engine.eval(script); 
			Invocable inv2 = (Invocable) engine;
			String res = (String) inv2.invokeFunction("say"); 
			System.out.println(res);*/
			//运行js文件
			String jsPath = "webroot/js/test.js";
			FileReader reader = new FileReader(jsPath);
			Compilable compEngine = (Compilable)engine;
			CompiledScript script = compEngine.compile(reader);
			
			//engine.eval(reader);
			/*Object object = engine.eval("obj.array");
			Class<?> clazz = object.getClass();
			Field denseField = clazz.getDeclaredField("dense");
		    denseField.setAccessible(true);
		    Object[] o = (Object[]) denseField.get(object);
		    for(int i=0;i<o.length;i++){
		    	System.out.print(o[i]);
		    }*/
			/*Invocable inv2 = (Invocable) engine;
			String res = (String) inv2.invokeFunction("say","yin"); 
			System.out.println(res);*/			
			List<ScriptEngineFactory> factories = sem.getEngineFactories();
			// 这是Java SE 5 和Java SE 6的新For语句语法
			
			for (ScriptEngineFactory factory: factories){
				out.printf("Name: %s%n" +
				"Version: %s%n" +
				"Language name: %s%n" +
				"Language version: %s%n" +
				"Extensions: %s%n" +
				"Mime types: %s%n" +
				"Names: %s%n",
				factory.getEngineName(),
				factory.getEngineVersion(),
				factory.getLanguageName(),
				factory.getLanguageVersion(),
				factory.getExtensions(),
				factory.getMimeTypes(),
				factory.getNames());
				// 得到当前的脚本引擎
				
				ScriptEngine engine2 = factory.getScriptEngine();
			}
	    } catch (Exception ex) { 
	       ex.printStackTrace();  
	    } 
	}
	
}
