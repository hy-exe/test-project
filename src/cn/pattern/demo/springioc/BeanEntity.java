package cn.pattern.demo.springioc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yin.huang
 * @date 2017年3月6日 上午11:35:32
 */
public class BeanEntity {

	private String id;

	private String className;

	private List<PropertyEntity> propertyList = new ArrayList<PropertyEntity>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<PropertyEntity> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<PropertyEntity> propertyList) {
		this.propertyList = propertyList;
	}

}
