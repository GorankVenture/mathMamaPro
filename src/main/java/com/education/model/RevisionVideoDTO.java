package com.education.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RevisionVideoDTO {

	
	private Long  videoRevisionId;
	private String videoRevisionName;
	private String videoRevisionDescription;
	private MultipartFile fileVideo;
	private String chapterRevisionFinalCode;
}
