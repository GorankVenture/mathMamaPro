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
public class VideoLecturePreviousDTO {

	
	private Long videoLecturePreviousId;
	private String videoLecturePreviousName;
	private String previousYearItemCode;
	private String chapterPreviousCode;
	private String finalPreviousCode;
	private MultipartFile videoFile;
}
