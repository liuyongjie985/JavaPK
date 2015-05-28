package Logic;

import java.util.List;

import Entity.Courserecord;


public class CourseInf {
	static final private CourseInf instance = new CourseInf();
	static private DBAccess acc = DBAccess.getInstance();

	public static DBAccess getAcc() {
		return acc;
	}

	public static void setAcc(DBAccess acc) {
		CourseInf.acc = acc;
	}

	private CourseInf() {
	}

	public static CourseInf getInstance() {
		return instance;
	}

	public int getCourseID() {
		return acc.getMy().getCourseIDFromDB();
	}

	public int[] getCourseState(int[] array) {// int[] array = new int[80];

		// if there is no item ,list will be null
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
		// HibernateSessionFactory.getSession().clear();
		List list = acc.getCouD().findAll();

		int CourseNum;
		for (int i = 0; i < list.size(); i++) {
			CourseNum = ((Courserecord) (list.get(i))).getId();
			
			array[CourseNum-1] = 1;
		}
		return array;
	}
}
