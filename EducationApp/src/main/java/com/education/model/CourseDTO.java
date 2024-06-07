package com.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseDTO {

	
	private Long courseId;
	private String courseName;
	private String courseCode;
	private String courseSubCode;
	private String courseSubName;
}
