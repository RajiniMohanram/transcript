package com.dmi.sjbu.proj.transcript.model;

public class TranscriptLineObject {
	private String studyCenter;
	private String programme;
	private String regNo;
	private String studName;
	private String gender;
	private String dob;
	private String doe;
	private double cgpa;
	private String classOfAward;
	private String lastAppear;
	private String doi;
	private int semester;
	private int moduleOrder;
	private String moduleCode;
	private String moduleName;
	private double grade;
	private String appeared;
	public TranscriptLineObject() {
		
	}
	public TranscriptLineObject(String studyCenter, String programme, String regNo, String studName, String gender, String dob,
			String doe, double cgpa, String classOfAward, String lastAppear, String doi, int semester, int moduleOrder,
			String moduleCode, String moduleName, double grade, String appeared) {
		super();
		this.studyCenter = studyCenter;
		this.programme = programme;
		this.regNo = regNo;
		this.studName = studName;
		this.gender = gender;
		this.dob = dob;
		this.doe = doe;
		this.cgpa = cgpa;
		this.classOfAward = classOfAward;
		this.lastAppear = lastAppear;
		this.doi = doi;
		this.semester = semester;
		this.moduleOrder = moduleOrder;
		this.moduleCode = moduleCode;
		this.moduleName = moduleName;
		this.grade = grade;
		this.appeared = appeared;
	}
	public String getStudyCenter() {
		return studyCenter;
	}
	public void setStudyCenter(String studyCenter) {
		this.studyCenter = studyCenter;
	}
	public String getProgramme() {
		return programme;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getStudName() {
		return studName;
	}
	public void setStudName(String studName) {
		this.studName = studName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDoe() {
		return doe;
	}
	public void setDoe(String doe) {
		this.doe = doe;
	}
	public double getCgpa() {
		return cgpa;
	}
	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}
	public String getClassOfAward() {
		return classOfAward;
	}
	public void setClassOfAward(String classOfAward) {
		this.classOfAward = classOfAward;
	}
	public String getLastAppear() {
		return lastAppear;
	}
	public void setLastAppear(String lastAppear) {
		this.lastAppear = lastAppear;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
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
	
}
