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
@Table(name="chapter_revision_details")
public class ChapterRevisionEntity {

	
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="chapter_revision_id")
	private Long chapterRevisionId;
	@Column(name="chapter_revision_name")
	private String chapterRevisionName;
	@Column(name="course_sub_code")
	private String courseSubCode;
	@Column(name="chapter_revision_code")
	private String chapterRevisionCode;
	@Column(name="chapterRevisionFinalCode")
	private String chapterRevisionFinalCode;
}
