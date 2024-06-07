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
public class PreviousQuestionPaperDTO {

	
	
	private Long previousQuestionId;
	private String previousQuestionsPaperName;
	private String previousYearItemCode;
	private MultipartFile previousQuestionFile;
	private String basePrevious;
}
