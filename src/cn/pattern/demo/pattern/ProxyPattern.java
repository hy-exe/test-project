package cn.pattern.demo.pattern;
/**
 * 代理模式
 * 
 * 应用实例：1、spring aop。
 * 意图：为其他对象提供一种代理以控制对这个对象的访问。
 * 
 *  何时使用：想在访问一个类时做一些控制。
 *	如何解决：增加中间层。
 *	关键代码：实现与被代理类组合。
 *
 *  通过创建一个代理类，在代理类中在使用到具体方法时才创建实际类，通过实际类去执行具体方法。
 */
public class ProxyPattern {

}
/**
 * 实现代码
 * @author HY
 *
 */
interface Image {
	void display();
}
class RealImage implements Image {

	private String fileName;

	public RealImage(String fileName){
	    this.fileName = fileName;
	    loadFromDisk(fileName);
	}

	@Override
	public void display() {
	   System.out.println("Displaying " + fileName);
	}

	private void loadFromDisk(String fileName){
	   System.out.println("Loading " + fileName);
	}
}
class ProxyImage implements Image{

   private RealImage realImage;
   private String fileName;

   public ProxyImage(String fileName){
      this.fileName = fileName;
   }

   @Override
   public void display() {
      if(realImage == null){
         realImage = new RealImage(fileName);
      }
      realImage.display();
   }
}