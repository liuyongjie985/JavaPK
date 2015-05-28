package Entity;

/**
 * Testrecord entity. @author MyEclipse Persistence Tools
 */

public class Testrecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String time;
	private String location;
	private Integer remind;
	private String remark;

	// Constructors

	/** default constructor */
	public Testrecord() {
	}

	/** minimal constructor */
	public Testrecord(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Testrecord(Integer id, String name, String time, String location,
			Integer remind, String remark) {
		this.id = id;
		this.name = name;
		this.time = time;
		this.location = location;
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

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
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