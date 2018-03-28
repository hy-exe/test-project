package cn.pattern.demo.test;

import java.text.DateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yin.huang
 * @date 2016年11月11日 下午3:29:15
 */
public class Test5 {

  static ThreadLocal<DateFormat> safeSdf = new ThreadLocal<DateFormat>() {

  };

  public static void main(String[] args) {

//    int[][] a = { { 1, 3, 5, 7, 10 }, { 2, 4, 6, 8, 11 }, { 3, 5, 7, 10, 13 } };
//    int foundNum = 9;
//    boolean found = false;
//    int columns = a[0].length;
//    int rows = a.length;
//    int row = 0;
//    int column = columns - 1;
//    while (row < rows && column >= 0) {
//      if (a[row][column] == foundNum) {
//        found = true;
//        break;
//      } else if (a[row][column] > foundNum) {
//        --column;
//      } else {
//        ++row;
//      }
//    }
//    System.out.println(found + "--> row=" + row + "|column=" + column);
//    
    String phone = null;
    String regExp = "\\d{11}?";
    Pattern p = Pattern.compile(regExp);
    Matcher m = p.matcher(phone);
   System.out.println(m.matches()); 
  }
}
