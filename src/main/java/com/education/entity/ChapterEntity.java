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
@Table(name="chapter_details")
public class ChapterEntity {

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="class_id")
	private Long chapterId;
	@Column(name="chapter_name")
	private String chapterName;
	@Column(name="chapter_description")
	private String chapterDescription;
	//@Column(name="course_code")
	@Column(name="item_code")
	private String itemCode;
	@Column(name="chapter_code")
	private String chapterCode;
	@Column(name="final_code")
	private String finalCode;
	
}