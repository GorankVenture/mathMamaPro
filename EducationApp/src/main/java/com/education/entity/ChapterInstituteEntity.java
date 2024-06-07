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
@Table(name="chapter_institute_details")
public class ChapterInstituteEntity {
   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="chapter_institute_id")
	private Long chapterInstituteId;
	@Column(name="chapter_institute_name")
	private String chapterInstituteName;
	@Column(name="chapter_description")
	private String chapterDescription;
	@Column(name="institute_final_code")
	private String instituteFinalCode;
	@Column(name="chapter_institute_code")
	private String chapterInstituteCode;
	@Column(name="chapter_institute_final_code")
	private String chapterInstituteFinalCode;
}
