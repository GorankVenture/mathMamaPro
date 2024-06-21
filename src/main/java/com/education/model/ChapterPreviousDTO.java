package com.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ChapterPreviousDTO {

	private Long chapterPreviousId;
	private String chapterPreviousName;
	private String previousYearItemCode;
	private String chapterPreviousCode;
	private String finalPreviousCode;
}
