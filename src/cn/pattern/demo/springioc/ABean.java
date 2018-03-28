package cn.pattern.demo.springioc;
/** 
* @author yin.huang 
* @date 2017年3月6日 下午2:50:35 
*/
public class ABean {
	
	private String id;
	
	private String name;
	
	private BBean bBean;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BBean getbBean() {
		return bBean;
	}
	public void setbBean(BBean bBean) {
		this.bBean = bBean;
	}
}
