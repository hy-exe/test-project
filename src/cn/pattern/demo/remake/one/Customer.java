package cn.pattern.demo.remake.one;

import java.util.Enumeration;
import java.util.Vector;

/** 
 * 顾客
* @author yin.huang 
* @date 2016年10月11日 下午4:03:45 
*/
public class Customer {
	private String _name;
	private Vector _rentals = new Vector();
	
	public Customer(String name){
		this._name = name;
	}
	public void addRental(Rental arg){
		_rentals.addElement(arg);
	}
	public String getName(){
		return _name;
	}
	
	public String statement(){
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration rentals = _rentals.elements();
		String result = "Rental Record for "+getName()+"\n";
		while(rentals.hasMoreElements()){
			Rental each = (Rental) rentals.nextElement();
			
			frequentRenterPoints ++;
			if(each.get_movie().get_priceCode() == Movie.NEW_RELEASE && each.get_dayRented()>1)
				frequentRenterPoints ++;
			result += "\t" + each.get_movie().get_title() + "\t" + String.valueOf(each.getCharge()) + "\n";
			totalAmount += each.getCharge();
		}
		result += "Amount owed id" + String.valueOf(totalAmount)+"\n";
		result += "You earned" + String.valueOf(frequentRenterPoints)+"frequent renter points";
		return result;
	}
}
