package com.example.demo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Assessment {

	@Id
	@GeneratedValue
	private Long id;
    private String userid;
    private String assignment;
    private Date date;
    private String type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Assessment(Long id, String userid, String assignment, Date date, String type) {
		super();
		this.id = id;
		this.userid = userid;
		this.assignment = assignment;
		this.date = date;
		this.type = type;
	}
	public Assessment() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Assessment [id=" + id + ", userid=" + userid + ", assignment=" + assignment + ", date=" + date + ", type=" + type
				+ "]";
	}
	
	
	
}