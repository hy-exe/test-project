package cn.pattern.demo.remake.one;
/** 
 * 租赁
* @author yin.huang 
* @date 2016年10月11日 下午4:00:53 
*/
public class Rental {
	private Movie _movie;
	private int _dayRented;
	
	public Rental(Movie movie,int dayRented){
		this._movie = movie;
		this._dayRented = dayRented;
	}
	
	public double getCharge(){
		double result = 0;
		switch(get_movie().get_priceCode()){
		case Movie.REGULAR:
			result += 2;
			if(get_dayRented()>2)
				result += (get_dayRented() - 2) * 1.5;
			break;
		case Movie.NEW_RELEASE:
			result += get_dayRented()  * 3;
			break;
		case Movie.CHILDRENS:
			result += 1.5;
			if(get_dayRented()>3)
				result += (get_dayRented() - 3) * 1.5;
			break;
		}
		return result;
	}
	
	public Movie get_movie() {
		return _movie;
	}

	public void set_movie(Movie _movie) {
		this._movie = _movie;
	}

	public int get_dayRented() {
		return _dayRented;
	}

	public void set_dayRented(int _dayRented) {
		this._dayRented = _dayRented;
	}
}
