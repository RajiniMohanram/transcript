package com.dmi.sjbu.proj.transcript.model;

public class ModuleInfo implements Comparable<ModuleInfo>{
	private int semester;
	private int moduleOrder;
	private String moduleCode;
	private String moduleName;
	private double grade;
	private String appeared;
	public ModuleInfo() {
		super();
	}
	public ModuleInfo(int semester, int moduleOrder, String moduleCode, String moduleName, double grade,
			String appeared) {
		super();
		this.semester = semester;
		this.moduleOrder = moduleOrder;
		this.moduleCode = moduleCode;
		this.moduleName = moduleName;
		this.grade = grade;
		this.appeared = appeared;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public int getModuleOrder() {
		return moduleOrder;
	}
	public void setModuleOrder(int moduleOrder) {
		this.moduleOrder = moduleOrder;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String getAppeared() {
		return appeared;
	}
	public void setAppeared(String appeared) {
		this.appeared = appeared;
	}
	@Override
	public int compareTo(ModuleInfo o) {
		if(this.semester==o.semester) {
			return this.moduleOrder - o.moduleOrder;
		}else {
			return this.semester - o.semester;
		}
	}
	
	public String toString() {
		return String.format("%-3d %-10s %-25s %5.2f %-10s", semester, moduleCode,moduleName,grade, appeared);
	}
}
