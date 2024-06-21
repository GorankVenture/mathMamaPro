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
@Table(name="video_solution_details")
public class VideoSolutionsEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="video_solution_id")
	private Long videoSolutionsId;
	@Column(name="video_solutions_name")
	private String videoSolutionsName;
	@Column(name="material_item_code")
	private String materialItemCode;
	@Column(name="video_solutions_data")
	private byte[] videoSolutionsData;
}
