package cn.pattern.demo.remake.one;
/** 
 * 影片
* @author yin.huang 
* @date 2016年10月11日 下午3:57:24 
*/
public class Movie {
	//影片的三种类型
	public static final int CHILDRENS = 2; 
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	
	private String _title;
	private int _priceCode;
	
	public Movie(String title,int priceCode){
		this._title = title;
		this._priceCode = priceCode;
	}

	public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}

	public int get_priceCode() {
		return _priceCode;
	}

	public void set_priceCode(int _priceCode) {
		this._priceCode = _priceCode;
	}
}
