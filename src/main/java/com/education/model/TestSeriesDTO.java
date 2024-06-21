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
public class TestSeriesDTO {
	
	private Long testSeriesId;
	private String testSeriesName;
	private String itemCode;
	private MultipartFile testSeriesFile;
	private String baseTestSeries;
	
	

}
