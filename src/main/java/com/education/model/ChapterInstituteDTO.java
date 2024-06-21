package com.education.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChapterInstituteDTO {

	
	
	private Long chapterInstituteId;
	private String chapterInstituteName;
	private String chapterDescription;
	private String instituteFinalCode;
	private String chapterInstituteCode;
	private String chapterInstituteFinalCode;
	
}
