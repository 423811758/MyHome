package com.wzd.common.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;

public class CommonStringUtil {

	/**
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s) {
		return s != null && !"".equals(s.trim());
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s.trim());
	}

	/**
	 * 判断某个字符串是否是以某个后缀名数组结尾的，可用于判断文件名的类型
	 * 
	 * @param name
	 * @param strs
	 * @return
	 */
	public static boolean isEndWithStringArray(String name, String[] strs) {
		for (String suffix : strs) {
			if (name.endsWith(suffix)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 不区分大小写查找字符串
	 * 
	 * @param subject
	 * @param search
	 * @param soffset
	 * @return
	 */
	public static int ignoreIndexOf(String subject, String search, int soffset) {
		// 当被查找字符串或查找子字符串为空时，抛出空指针异常。
		if (subject == null || search == null) {
			throw new NullPointerException("输入的参数为空");
		}
		if (soffset >= subject.length() && search.equals("")) {
			return subject.length();
		}
		for (int i = soffset; i < subject.length(); i++) {
			if (subject.regionMatches(true, i, search, 0, search.length())) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 混合字符串比较（中英文混合判断，中文用拼音字母排序）
	 * 
	 * @param firstStr
	 *            第一个字符串
	 * @param secondStr
	 *            第二个字符串
	 * @return 结果：-1，表示第一个字符串在第二个字符串之前; 0，表示两个字符串相同； 1，表示第一个字符串在第二个字符串之后。
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int compareFixedString(String firstStr, String secondStr) {
		if (firstStr.equals(secondStr))
			return 0;
		Comparator cmp_ch = Collator.getInstance(java.util.Locale.CHINA);
		String[] array_ch = { firstStr, secondStr };
		Arrays.sort(array_ch, cmp_ch);
		if (firstStr.equals(array_ch[0])) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * 得到字符串分割后数组的长度，去掉空格成员
	 * 
	 * @param strArray
	 * @return
	 */
	public static int getSplitStrArrayLen(String[] strArray) {
		int len = 0;
		for (int i = 0; i < strArray.length; i++) {
			if (strArray[i] != null && !strArray[i].trim().equals("")) {
				len++;
			}
		}
		return len;
	}

	/**
	 * 插入SQL转义符，使用转义符'/'
	 */
	public static String insertSQLEscapeChar(String key) {
		String result = "";
		result = key.replaceAll("/", "//");
		result = result.replace("%", "/%");
		result = result.replaceAll("_", "/_");
		return result;
	}

	/**
	 * 插入SQL转义符，把"'"转换为"''"
	 */
	public static String changeSQLChar(String key) {
		String result = "";
		if (key != null && !key.equals("")) {
			result = key.replaceAll("'", "''");
		}
		return result;
	}

	/**
	 * 获得字符串的字节输入流
	 * 
	 * @param s
	 * @return 输入流对象
	 */
	public static InputStream getStringInputStream(String s) {
		if (s != null && !s.equals("")) {
			ByteArrayInputStream stringInputStream = new ByteArrayInputStream(
					s.getBytes());
			return stringInputStream;
		}
		return null;
	}
}
