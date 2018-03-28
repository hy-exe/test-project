package cn.pattern.demo.springioc;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author yin.huang
 * @date 2017年3月6日 上午11:40:12
 */
public class BeanFactory {
	
	private static Map<String, BeanEntity> beanMap = new HashMap<String, BeanEntity>();
	
	@SuppressWarnings("all")
	private static void getBeanMap(String path) throws Exception {
		
		SAXReader saxReader = new SAXReader();

		Document document = saxReader.read(new File(path));

		// 获取根元素
		Element root = document.getRootElement();
		// 获取特定名称的子元素
		List<Element> beanList = root.elements("bean");
		for (int i = 0; i < beanList.size(); i++) {
			BeanEntity beanEntity = new BeanEntity();
			Element beanEle = beanList.get(i);
			List<Element> proEleList = beanEle.elements("property");
			beanEntity.setId(beanEle.attributeValue("id"));
			beanEntity.setClassName(beanEle.attributeValue("class"));
			for (int j = 0; j < proEleList.size(); j++) {
				PropertyEntity property = new PropertyEntity();
				Element proEle = proEleList.get(j);
				property.setName(proEle.attributeValue("name"));
				property.setValue(proEle.attributeValue("value"));
				property.setRef(proEle.attributeValue("ref"));
				beanEntity.getPropertyList().add(property);
			}
			beanMap.put(beanEle.attributeValue("id"), beanEntity);
		}
	}

	public static void main(String[] args) throws Exception {
		getBeanMap("webroot/applicationContext.xml");
		BeanEntity beanEntity = beanMap.get("aBean");
		System.out.println(beanEntity.getClassName());
	}

}