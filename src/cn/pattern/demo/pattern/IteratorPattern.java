package cn.pattern.demo.pattern;

/**
 * 迭代器模式
 * 
 * 意图：提供一种方法顺序访问一个聚合对象中各个元素, 而又无须暴露该对象的内部表示。
 * 应用实例：JAVA 中的 iterator。
 *
 */
public class IteratorPattern {	
}

// 叙述导航方法接口
interface Iterator {
   public boolean hasNext();
   public Object next();
}

//返回迭代器的接口
interface Container {
   public Iterator getIterator();
}
//接口实现类
class NameRepository implements Container {
   public String[] names = {"Robert" , "John" ,"Julie" , "Lora"};

   @Override
   public Iterator getIterator() {
      return new NameIterator();
   }

   private class NameIterator implements Iterator {

      int index;

      @Override
      public boolean hasNext() {
         if(index < names.length){
            return true;
         }
         return false;
      }

      @Override
      public Object next() {
         if(this.hasNext()){
            return names[index++];
         }
         return null;
      }		
   }
}
class IteratorPatternDemo {	
   public static void main(String[] args) {
      NameRepository namesRepository = new NameRepository();

      for(Iterator iter = namesRepository.getIterator(); iter.hasNext();){
         String name = (String)iter.next();
         System.out.println("Name : " + name);
      } 	
   }
}