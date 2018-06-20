package com.skilldistillery.jpadesolatemidterm.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private String id;
	@Column(name="start_date")
	private Date startDate;
	private int status;
	private String location;
	private int visibility;
	
	
}
