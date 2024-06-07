package com.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ChapterRevisionDTO {

	
	private Long chapterRevisionId;
	private String chapterRevisionName;
    private String courseSubCode;
	private String chapterRevisionCode;
	private String chapterRevisionFinalCode;
}
