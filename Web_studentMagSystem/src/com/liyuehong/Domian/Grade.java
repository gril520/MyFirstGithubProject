package com.liyuehong.Domian;

public class Grade {
	private int id;
	private String gradeName;
	private String gradeDesc;
	
	
	public Grade() {
		super();
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	public Grade(String gradeName, String gradeDesc) {
		super();
		this.gradeName = gradeName;
		this.gradeDesc = gradeDesc;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the gradeName
	 */
	public String getGradeName() {
		return gradeName;
	}
	/**
	 * @param gradeName the gradeName to set
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	/**
	 * @return the gradeDesc
	 */
	public String getGradeDesc() {
		return gradeDesc;
	}
	/**
	 * @param gradeDesc the gradeDesc to set
	 */
	public void setGradeDesc(String gradeDesc) {
		this.gradeDesc = gradeDesc;
	}
	
}
