package Entity;

/**
 * Courserecord entity. @author MyEclipse Persistence Tools
 */

public class Courserecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String time;
	private String week;
	private String location;
	private String teacher;
	private Integer remind;
	private String remark;

	// Constructors

	/** default constructor */
	public Courserecord() {
	}

	/** minimal constructor */
	public Courserecord(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Courserecord(Integer id, String name, String time, String week,
			String location, String teacher, Integer remind, String remark) {
		this.id = id;
		this.name = name;
		this.time = time;
		this.week = week;
		this.location = location;
		this.teacher = teacher;
		this.remind = remind;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getWeek() {
		return this.week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTeacher() {
		return this.teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public Integer getRemind() {
		return this.remind;
	}

	public void setRemind(Integer remind) {
		this.remind = remind;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}