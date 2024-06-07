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
public class TextSolutionsDTO {

	
	
	
	private Long textSolutionsId;
	private String textSolutionsName;
	private String materialItemCode;
	//private String instituteChapterCode;
	private MultipartFile textFile;
	private String baseText;
}
