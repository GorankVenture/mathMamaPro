package com.education.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ImportantDTO 
{

	private Long importantQuestionId;
	private String name;
	private String itemCode;
	private MultipartFile importantFile;
	private String baseImportant;
	
}