package com.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString





public class ChapterDTO {

	
	private Long chapterId;
	private String  chapterName;
	private String chapterDescription;
	private String itemCode;
	
	private String chapterCode;
	private String finalCode;
}
