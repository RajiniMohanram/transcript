package com.dmi.sjbu.proj.transcript.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Transcript {
	private String studyCenter;
	private String programme;
	private String regNo;
	private String studName;
	private String gender;
	private LocalDate dob;
	private String doe;
	private double cgpa;
	private String classOfAward;
	private String lastAppear;
	private LocalDate doi;
	private Map<Integer,List<ModuleInfo>> modules;
	public Transcript() {
		super();
		this.modules = new TreeMap<Integer,List<ModuleInfo>>();
	}
	public Transcript(String studyCenter, String programme, String regNo, String studName, String gender, LocalDate dob,
			String doe, double cgpa, String classOfAward, String lastAppear, LocalDate doi) {
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
		this.modules = new TreeMap<Integer,List<ModuleInfo>>();
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
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
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
	public LocalDate getDoi() {
		return doi;
	}
	public void setDoi(LocalDate doi) {
		this.doi = doi;
	}
	public Map<Integer,List<ModuleInfo>> getModules() {
		return modules;
	}
	public void setModules(Map<Integer,List<ModuleInfo>> modules) {
		this.modules = modules;
	}
	
	
}
