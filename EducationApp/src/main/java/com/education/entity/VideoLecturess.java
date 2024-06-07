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
@Table(name="video_lecturess")
public class VideoLecturess {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="video_lecturess_id")
	private Long videoLecturessId;
	@JsonIgnore
	@Column(name="video_data")
	private byte[] videoData;
}
