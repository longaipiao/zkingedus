package com.zking.zkingedu.common.utils;

import java.util.List;

/**
 * String工具 主要是对StringUtils一些方法进行重写,从而达到更好的应用
 *
 * @author ztt
 *
 */
public class StringUtils extends org.springframework.util.StringUtils {

	/**
	 * 一次性判断多个或单个对象为空。
	 *
	 * @param objects
	 * @author ztt
	 * @return 只要有一个元素为Blank，则返回true
	 */
	public static boolean isBlank(Object... objects) {
		Boolean result = false;
		for (Object object : objects) {
			if (null == object || "".equals(object.toString().trim()) || "null".equals(object.toString().trim())) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * 一次性判断多个或单个对象不为空。
	 *
	 * @param objects
	 * @author ztt
	 * @return 只要有一个元素不为Blank，则返回true
	 */
	public static boolean isNotBlank(Object... objects) {
		return !isBlank(objects);
	}

	public static boolean isBlank(String... objects) {
		Object[] object = objects;
		return isBlank(object);
	}

	public static boolean isNotBlank(String... objects) {
		Object[] object = objects;
		return !isBlank(object);
	}

	public static boolean isBlank(String str) {
		Object object = str;
		return isBlank(object);
	}

	public static boolean isNotBlank(String str) {
		Object object = str;
		return !isBlank(object);
	}

	/**
	 * 判断两个集合的值是否一致
	 * @param l0
	 * @param l1
	 * @return
	 */
	public static boolean isListEqual(List l0, List l1){
		if (l0 == l1)
			return true;
		if (l0 == null && l1 == null)
			return true;
		if (l0 == null || l1 == null)
			return false;
		if (l0.size() != l1.size())
			return false;
		for (Object o : l0) {
			if (!l1.contains(o))
				return false;
		}
		for (Object o : l1) {
			if (!l0.contains(o))
				return false;
		}
		return true;
	}


}
