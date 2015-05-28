package Logic;

public class TestInf {
	static final private TestInf instance = new TestInf();
	static private DBAccess acc = DBAccess.getInstance();

	public static DBAccess getAcc() {
		return acc;
	}

	public static void setAcc(DBAccess acc) {
		TestInf.acc = acc;
	}

	private TestInf() {
	}

	static public TestInf getInstance() {
		return instance;
	}

	public int getTestID() {
		return acc.getMy().getTestIDFromDB();
	}
}
