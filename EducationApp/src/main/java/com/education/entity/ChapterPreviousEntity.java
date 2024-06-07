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
@Table(name="chapter_previous_details")
public class ChapterPreviousEntity {

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="chapter_previous_id")
 	private Long chapterPreviousId;
	@Column(name="chapter_previous_name")
	private String chapterPreviousName;
	@Column(name="previous_year_item_code")
	private String previousYearItemCode;
	@Column(name="chapter_previous_code")
	private String chapterPreviousCode;
	@Column(name="final_previous_code")
	private String finalPreviousCode;
	
	
}
