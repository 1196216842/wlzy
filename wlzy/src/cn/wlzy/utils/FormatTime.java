package cn.wlzy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatTime {
	public static String format(Date date){
		SimpleDateFormat format=new SimpleDateFormat("yy-MM-dd");
		return format.format(date);
	}
	public static void main(String[] args) {
		Date date=new Date();
		String str=format(date);
		System.out.println(str);
	}
}
