package com.education.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter


@Entity
@Table(name="video_names")
public class VideoLecture {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="video_id")
	private Long videoId;
	@Column(name="video_name")
	public String videoName;
	@Column(name="title")
	public String title;
	@Column(name="item_code")
	private String itemCode;
	@Column(name="chapter_code")
	private String chapterCode;
	@Column(name="final_code")
	private String finalCode;
	@Column(name="description")
	private String description;
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="video_lecturess_id")
	private VideoLecturess videoLecturess;
	
	
	
}