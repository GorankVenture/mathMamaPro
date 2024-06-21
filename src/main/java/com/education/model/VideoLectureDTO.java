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
public class VideoLectureDTO {

	
	public Long videoId;
	public String videoName;
	public String title;
	public String description;
	public String itemCode;
	public String chapterCode;
	public String finalCode;
	public MultipartFile file;
	
}
