package com.education.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VideoSolutionsDTO {

	
	
	private Long videoSolutionsId;
	private String videoSolutionsName;
	private String materialItemCode;
	private MultipartFile videoSolutionsFile;
}
