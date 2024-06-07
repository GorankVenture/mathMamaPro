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
@Table(name="revision_video_details")
public class RevisionVideoEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="video_revision_id")
	private Long  videoRevisionId;
	@Column(name="video_revision_name")
	private String videoRevisionName;
	@JsonIgnore
	@Column(name="video_revision_data")
	private byte[] videoRevisionData;
	@Column(name="revision_description")	
	private String videoRevisionDescription;
	@Column(name="chapter_revision_final_code")
	private String chapterRevisionFinalCode;
	
	
}
