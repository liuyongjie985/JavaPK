package Logic;

import DAO.*;

public class DBAccess {
	static final private DBAccess instance = new DBAccess();
	public CourserecordDAO getCouD() {
		return CouD;
	}

	public void setCouD(CourserecordDAO couD) {
		CouD = couD;
	}

	public TestrecordDAO getTesD() {
		return TesD;
	}

	public void setTesD(TestrecordDAO tesD) {
		TesD = tesD;
	}

	public MySQL getMy() {
		return my;
	}

	public void setMy(MySQL my) {
		this.my = my;
	}

	private CourserecordDAO CouD;
	private TestrecordDAO TesD;
	private MySQL my;

	private DBAccess() {
		CouD = new CourserecordDAO();
		TesD = new TestrecordDAO();
		my = new MySQL();
	}

	static public DBAccess getInstance() {
		return instance;
	}
}
