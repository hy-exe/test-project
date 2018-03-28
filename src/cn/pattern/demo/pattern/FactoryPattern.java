package cn.pattern.demo.pattern;
/**
 * 工厂模式
 * 
 * hibernate 换数据库只需换方言和驱动
 * 
 */
public interface FactoryPattern {
	/**
	 * 定义一个创建对象的接口，让子类决定实例化哪一个工厂类，工厂模式使其创建过程延迟到子类进行
	 * 何时使用：我们明确地计划不同条件下创建不同实例时。
	 * 如何解决：让其子类实现工厂接口，返回的也是一个抽象的产品
	 * 创建过程在子类进行
	 */
	
	void draw(); //子类实现时根据不同清况实现不同状态
	
	/**
	 * 创建一个工厂类，根据传递类型信息的不同创建不同的实现接口的实例。
	*       public Shape getShape(String shapeType){
		      if(shapeType == null){
		         return null;
		      }		
		      if(shapeType.equalsIgnoreCase("CIRCLE")){
		         return new Circle(); //实现接口的类
		      } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
		         return new Rectangle(); //实现接口的类
		      } else if(shapeType.equalsIgnoreCase("SQUARE")){
		         return new Square(); //实现接口的类
		      }
		      return null;
		   	}
	 */
}
