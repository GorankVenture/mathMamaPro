package com.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;




@Getter
@Setter


@Entity
@Table(name="course_details")
public class CourseEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="course_id")
	private Long courseId;
	@Column(name="course_name")
	private String courseName;
	@Column(name="course_sub_name")
	private String courseSubName;
	@Column(name="course_code")
	private String courseCode;
	@Column(name="course_sub_code")
	private String courseSubCode;
	//@Column(name="course")
}
