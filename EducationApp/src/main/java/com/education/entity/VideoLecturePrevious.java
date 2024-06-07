package com.education.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="video_lecture_previous")
public class VideoLecturePrevious {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="video_lecture_previous_id")
	private Long videoLecturePreviousId;
	@Column(name="video_lecture_previous_name")
	private String videoLecturePreviousName;
	@Column(name="previous_year_item_code")
	private String previousYearItemCode;
	@Column(name="chapter_previous_code")
	private String chapterPreviousCode;
	@Column(name="final_previous_code")
	private String finalPreviousCode;
	@JsonIgnore
	@Column(name="video_previous_data")
	private byte[] videoPreviousData;
}
