package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 字符串工具类
 * 
 * @author zhangpeng
 * 
 */
public class StringUtils {

	/**
	 * md5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		return DigestUtils.md5Hex(str);
	}

	/**
	 * 获得用户激活码
	 * 
	 * @return
	 */
	public static String get_verifycode() {
		return RandomStringUtils.randomAlphabetic(10);
	}

	/**
	 * Meng-CMS专用系统唯一标识码
	 * 
	 * @return
	 */
	public static String getMengCode() {
		// TODO根据此码查询系统信息
		return RandomStringUtils.randomNumeric(32);
	}

	/**
	 * 获取标准时间
	 * 
	 * @return
	 */
	public static String getStanderDate() {
		return DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss");
	}

	public static void main(String[] args) {
		System.out.println(getStanderDate());
	}

	public static String getDateString(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd");
	}
	
	public static String getTimeString(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd hh:mm:ss");
	}

	public static String getCurrentDateString() {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		return simple.format(date);
	}

	/**
	 * @param date1
	 *            需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param date2
	 *            被比较的时间 为空(null)则为当前时间
	 * @param stype
	 *            返回值类型 0为多少天，1为多少个月，2为多少年
	 * @return
	 */
	public static int compareDate(String date1, String date2, int stype) {
		int n = 0;

//		String[] u = { "天", "月", "年" };
		String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

		date2 = date2 == null ? getCurrentDateString() : date2;

		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
			System.out.println("wrong occured");
		}
		// List list = new ArrayList();
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			// list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}

		n = n - 1;

		if (stype == 2) {
			n = (int) n / 365;
		}

		//System.out.println(date1 + " -- " + date2 + " 相差多少" + u[stype] + ":"
//				+ n);
		return n;
	}
}
