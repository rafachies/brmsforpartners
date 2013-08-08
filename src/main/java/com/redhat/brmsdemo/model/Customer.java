package com.redhat.brmsdemo.model;

import java.io.Serializable;


public class Customer implements Serializable {
	
	private static final long serialVersionUID = 3393123662798184687L;

	private Integer id;
	private String cpf;
	private Integer scpcScore;
	private Integer monthlyIncome = 0;
	private Integer age;
	
	private Boolean approved = true;
	private Integer creditValue = 0;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Integer getScpcScore() {
		return scpcScore;
	}
	public void setScpcScore(Integer scpcScore) {
		this.scpcScore = scpcScore;
	}
	public Integer getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(Integer monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	public Integer getCreditValue() {
		return creditValue;
	}
	public void setCreditValue(Integer creditValue) {
		this.creditValue = creditValue;
	}
}
