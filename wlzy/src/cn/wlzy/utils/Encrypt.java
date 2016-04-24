package cn.wlzy.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 用SHA加密密码
 * @author Administrator
 *
 */
public class Encrypt {
public static byte[]  encrypt(String msg){
		try {
			//根据SHA算法生成MessageDigest对象
			MessageDigest sha=MessageDigest.getInstance("SHA");
			byte[] srcBytes=msg.getBytes();
			//使用srcBytes更新摘要
			sha.update(srcBytes);
			//完成hash计算，得到result
			byte[] resultBytes=sha.digest();
			return resultBytes;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		String str3=new String(encrypt("admin"));
		String str4=new String(encrypt("admin"));
		System.out.println(str3);
		System.out.println(str4);
		System.out.println(str4.equals(str3));
		
	}
}
