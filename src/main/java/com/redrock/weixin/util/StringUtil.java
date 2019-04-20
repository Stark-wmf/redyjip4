package com.redrock.weixin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static final String Char = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String getRandomCode(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append((int) (Math.random() * 10));
		}
		return sb.toString();
	}


	/**
	 * 返回一个定长的随机字符串(只包含大小写字母)
	 *
	 * @param length 随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(Char.charAt(random.nextInt(Char.length())));
		}
		return sb.toString();
	}



	/**
	 *
	 * @Title: isEmpty
	 * @Description: 检测字符串是否为null或去掉两端空格后是空串
	 * @param checkStr
	 * @return: boolean 如果是空 则返回true 否则返回false;
	 */
	public static boolean isEmpty(String checkStr) {

		if (checkStr == null || "".equals(checkStr.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 字符串装换成List
	 * @param string	字符串
	 * @param regex		分隔符
	 * @return
	 */
	public static List<String> StringToList(String string,String regex){
		List<String> list = new ArrayList<String>();
		String[] tomps = string.split(regex);
		for (String tomp : tomps){
			list.add(tomp);
		}
		return list;
	}

	/**
	 * 字符集合转换为 string 以,为分隔符
	 * @param list
	 * @return
	 */
	public static String stringListToString(List<String> list){
		if (list == null || list.isEmpty()){
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		for (String temp : list){
			buffer.append(temp + ",");
		}
		return buffer.substring(0, buffer.length()-1);
	}

	/**
	 * set集合转换为 string 以,为分隔符
	 * @param set
	 * @return
	 */
	public static String stringSetToString(Set<String> set){
		if (set == null || set.isEmpty()){
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		for (String temp : set){
			buffer.append(temp + ",");
		}
		return buffer.substring(0, buffer.length()-1);
	}

	/**
	 * 判断源字符串是否以指定key 开头
	 * @param sourceString	源字符串
	 * @param startwithKeys	指定key 数组
	 * @return
	 */
	public static boolean startsWithAny(String sourceString , String[] startwithKeys){
		for (String key : startwithKeys) {
			if (sourceString.startsWith(key)) {
				return true;
			}
		}
			return false;

	}

	public static ArrayList<String> stringsToList(String[] strings){
		ArrayList<String> stringArrayList = new ArrayList<String>();
		if (strings != null && strings.length > 0){
			for (String s : strings)
				stringArrayList.add(s);
		}
		return stringArrayList;
	}


	/**
	 * 判断用户名格式
	 * 中英文，下划线
	 */
	public static boolean checkName(String name){
		String check = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
		Pattern pattern = Pattern.compile(check);
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}

	public static void main(String args[]){
		System.out.println(checkName("asdasdasd是"));
	}

}
