/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.shuangze.assetsms.entity;

import javax.sql.rowset.Joinable;

/**
 *
 * @author Administrator
 */
public class Person {
    private String name;
    private String sex;
    private String dept;
    private String job;
    private String other;
    
    public Person() {
		// TODO Auto-generated constructor stub
	}
    
    public  Person(String name,String sex,String dept,String job,String other){
    	this.name=name;
    	this.job=job;
    	this.sex=sex;
    	this.dept=dept;
    	this.other=other;    	
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

    
    
    
    
}
