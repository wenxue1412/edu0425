package edu0425.spring.vo;


import edu0425.spring.interfaces.NoChinese;
import edu0425.spring.interfaces.NotEmpty;
import edu0425.spring.interfaces.NotNull2;

public class DeptInfo {
	
	private Integer deptno;
	@NotEmpty(msg = "部门名称不能为空")
	//@NotNull2(message = "部门名称不能为空", value = "1")
	private String dname;
	@NotNull2(message = "所在地不能为空", value = "1")
	@NoChinese(message = "所在地不能有中文")
	private String loc;

	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public DeptInfo(Integer deptno, String dname, String loc) {
		super();
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}
    //鏃犲弬鏋勯�犲嚱鏁�
	public DeptInfo() {
	}

	@Override
	public String toString() {
		return "DeptInfo [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "]";
	}

}
