package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Transaction;

import factory.HibernateSessionFactory;

public class MySQL {
	// private String Name[] = new String[100];
	private int BookingNum[] = new int[100];
	// private int Covers[] = new int[100];
	// private int Year[] = new int[100];
	// private int Month[] = new int[100];
	// private int Day[] = new int[100];
	// private int Hour[] = new int[100];
	// private int Minute[] = new int[100];
	// private String PhoneNumber[] = new String[100];
	// private int TableNumber[] = new int[100];
	// private int Tv[] = new int[100];
	// private int Air[] = new int[100];
	// private int Places[] = new int[100];

	Connection conn = null;
	// 定义Statement对象，用于操作数据库
	Statement stmt = null;
	// 定义一字符串变量，用于保存SQL语句
	String sql = null;
	// 定义一个结果集以存放检索的结果
	ResultSet r = null;

	public int[] getBookingNum() {
		return BookingNum;
	}

	public void BookingNum(int[] order) {
		BookingNum = order;
	}

	// public String[] getPhoneNumber() {
	// return PhoneNumber;
	// }
	//
	// public void setPhoneNumber(String[] phoneNumber) {
	// PhoneNumber = phoneNumber;
	// }
	//
	// public int[] getPlaces() {
	// return Places;
	// }
	//
	// public void setPlaces(int[] places) {
	// Places = places;
	// }
	//
	// public String[] getName() {
	// return Name;
	// }
	//
	// public void setName(String[] name) {
	// Name = name;
	// }
	//
	// public int[] getCovers() {
	// return Covers;
	// }
	//
	// public void setCovers(int[] covers) {
	// Covers = covers;
	// }
	//
	// public int[] getYear() {
	// return Year;
	// }
	//
	// public void setYear(int[] year) {
	// Year = year;
	// }
	//
	// public int[] getMonth() {
	// return Month;
	// }
	//
	// public void setMonth(int[] month) {
	// Month = month;
	// }
	//
	// public int[] getDay() {
	// return Day;
	// }
	//
	// public void setDay(int[] day) {
	// Day = day;
	// }
	//
	// public int[] getHour() {
	// return Hour;
	// }
	//
	// public void setHour(int[] hour) {
	// Hour = hour;
	// }
	//
	// public int[] getMinute() {
	// return Minute;
	// }
	//
	// public void setMinute(int[] minute) {
	// Minute = minute;
	// }
	//
	// public String[] getPhonenumber() {
	// return PhoneNumber;
	// }
	//
	// public void setPhonenumber(String[] phonenumber) {
	// this.PhoneNumber = phonenumber;
	// }
	//
	// public int[] getTv() {
	// return Tv;
	// }
	//
	// public void setTv(int[] tv) {
	// Tv = tv;
	// }
	//
	// public int[] getAir() {
	// return Air;
	// }
	//
	// public void setAir(int[] air) {
	// Air = air;
	// }

	public MySQL() {
		// 定义数据库驱动程序
		String DBDRIVER = "com.mysql.jdbc.Driver";

		// 定义数据库连接地址
		String DBURL = "jdbc:mysql://localhost:3306/JavaPK";
		// 定义数据库连接对象，属于java.sql包中的接口
		String username = "root";
		String password = "root";

		// 1、加载驱动程序
		try {
			Class.forName(DBDRIVER);
		} catch (Exception e) {
			// 此处使用out.print是处于演示目的，在实际开发中所有的错误消息，
			// 绝对不能够通过System.out.print打印，否则会存在安全问题
			System.out.println("数据库驱动程序加载失败！！！");
		}
		// 2、连接数据库
		try {
			conn = DriverManager.getConnection(DBURL, username, password);
		} catch (Exception e) {
			System.out.println("数据库连接失败！！！");
		}
	}

	// public int[] getTableNumber() {
	// return TableNumber;
	// }
	//
	// public void setTableNumber(int[] tableNumber) {
	// TableNumber = tableNumber;
	// }

	// 显示已经预定了的桌子
	// public int getBookedTable(int year, int month, int day) {
	// int i = 0;
	// try {
	// stmt = conn.createStatement();
	// // 为sql变量赋值
	// // 插入语句
	// sql =
	// "select * from Desk where TableNumber not in ( select TableNumber from TableBooking, Booking where Booking.Torder = TableBooking.Torder and Booking.Year = "
	// + year
	// + " and Booking.Day = "
	// + day
	// + " and Booking.Month = " + month + " )";
	// // System.out.print(sql);
	// r = stmt.executeQuery(sql);
	// while (r.next()) {
	// TableNumber[i] = r.getInt(1);
	// Places[i] = r.getInt(2);
	// i++;
	// }
	// } catch (Exception e) {
	// System.out.println("操作数据库失败！！！");
	// }
	// // 4、关闭数据库
	// // try {
	// // // 关闭操作
	// // stmt.close();
	// // // 关闭连接
	// // conn.close();
	// // } catch (Exception e) {
	// // System.out.println("数据库关闭失败！！！");
	// // }
	// return i;
	// }

	// public int inquireBooking(int year, int month, int day) {
	// int i = 0;
	// try {
	// stmt = conn.createStatement();
	// // 为sql变量赋值
	// // 插入语句
	// sql = "select * from Booking, TableBooking, Desk where Year = "
	// + year + " and Day = " + day + " and Month = " + month;
	//
	// // System.out.print(sql);
	// r = stmt.executeQuery(sql);
	// while (r.next()) {
	// Name[i] = r.getString(1);
	// BookingNum[i] = r.getInt(2);
	// Covers[i] = r.getInt(3);
	// Year[i] = r.getInt(4);
	// Month[i] = r.getInt(5);
	// Day[i] = r.getInt(6);
	// Hour[i] = r.getInt(7);
	// // 少了minute？
	// PhoneNumber[i] = r.getString(8);
	// TableNumber[i] = r.getInt(9);
	// Places[i] = r.getInt(10);
	// i++;
	// }
	// } catch (Exception e) {
	// System.out.println("操作数据库失败！！！");
	// }
	// // 4、关闭数据库
	// // try {
	// // // 关闭操作
	// // stmt.close();
	// // // 关闭连接
	// // conn.close();
	// // } catch (Exception e) {
	// // System.out.println("数据库关闭失败！！！");
	// // }
	// return i;
	// }

	// public void add(Booking booking) {
	//
	// try {
	// stmt = conn.createStatement();
	// // 为sql变量赋值
	// // 插入语句
	// sql = "insert into Booking values('"
	// + booking.getCustomer().getName() + "', "
	// + booking.getOrder() + ", " + booking.getCovers() + ", "
	// + booking.getYear() + ", " + booking.getMonth() + ", "
	// + booking.getDay() + ", " + booking.getHour() + ", '"
	// + booking.getCustomer().getPhoneNumber() + "')";
	//
	// // System.out.print(sql);
	// stmt.executeUpdate(sql);
	// int TableCount = booking.getTableArrayList().size();
	// for (int i = 0; i < TableCount; i++) {
	// sql = "insert into TableBooking values("
	// + booking.getTableArrayList().get(i).getTableNumber()
	// + ", " + booking.getTableArrayList().get(i).getAir()
	// + ", " + booking.getTableArrayList().get(i).getTV()
	// + ", " + booking.getOrder() + ")";
	// stmt.executeUpdate(sql);
	// }
	//
	// } catch (Exception e) {
	// System.out.println("操作数据库失败！！！");
	// }
	// // 4、关闭数据库
	// // try {
	// // // 关闭操作
	// // stmt.close();
	// // // 关闭连接
	// // conn.close();
	// // } catch (Exception e) {
	// // System.out.println("数据库关闭失败！！！");
	// // }
	//
	// }

	// public int cancel(int Torder) {
	// int i = 0;
	// try {
	// stmt = conn.createStatement();
	// // 为sql变量赋值
	// // 插入语句
	// sql = "delete from Booking where Torder = " + Torder;
	//
	// // System.out.print(sql);
	// stmt.executeUpdate(sql);
	// sql = "delete from TableBooking where Torder = " + Torder;
	// stmt.executeUpdate(sql);
	//
	// } catch (Exception e) {
	// System.out.println("操作数据库失败！！！");
	// }
	//
	// // 提示删除成功？
	// return i;
	// }

	public int getTestIDFromDB() {
		int i = 0;
		// 3、操作数据库
		// 通过Connection对象实例化Statement对象
		try {

			stmt = conn.createStatement();
			// 为sql变量赋值
			// 插入语句
			sql = "select ID from TestRecord";
			r = stmt.executeQuery(sql);
			while (r.next()) {
				BookingNum[i] = r.getInt(1);
				i++;
			}
		} catch (Exception e) {
			System.out.println("操作数据库失败！！！");
		}

		if (i == 0) {
			return 1;
		}
		// 先排序
		int min, temp;
		for (int x = 0; x < i - 1; x++) {
			min = x;
			for (int y = x + 1; y < i; y++) {
				if (BookingNum[min] > BookingNum[y]) {
					min = y;
				}
			}
			temp = BookingNum[min];
			BookingNum[min] = BookingNum[x];
			BookingNum[x] = temp;
		}
		// 已排序

		if (i == BookingNum[i - 1]) {
			return i + 1;
		} else {

			int j = 1;
			while (j == BookingNum[j - 1]) {
				j++;
			}
			return j;
		}

	}

	public int getCourseIDFromDB() {
		int i = 0;
		// 3、操作数据库
		// 通过Connection对象实例化Statement对象
		try {

			stmt = conn.createStatement();
			// 为sql变量赋值
			// 插入语句
			sql = "select ID from CourseRecord";
			r = stmt.executeQuery(sql);
			while (r.next()) {
				BookingNum[i] = r.getInt(1);
				i++;
			}
		} catch (Exception e) {
			System.out.println("操作数据库失败！！！");
		}

		if (i == 0) {
			return 1;
		}
		// 先排序
		int min, temp;
		for (int x = 0; x < i - 1; x++) {
			min = x;
			for (int y = x + 1; y < i; y++) {
				if (BookingNum[min] > BookingNum[y]) {
					min = y;
				}
			}
			temp = BookingNum[min];
			BookingNum[min] = BookingNum[x];
			BookingNum[x] = temp;
		}
		// 已排序

		if (i == BookingNum[i - 1]) {
			return i + 1;
		} else {

			int j = 1;
			while (j == BookingNum[j - 1]) {
				j++;
			}
			return j;
		}

	}
	

	public void close() {
		try {
			// 关闭操作
			stmt.close();
			// 关闭连接
			conn.close();
		} catch (Exception e) {
			System.out.println("数据库关闭失败！！！");
		}
	}

	

	

	

}
