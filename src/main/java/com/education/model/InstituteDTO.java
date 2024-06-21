package com.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstituteDTO {

	
	
	private Long instituteId;
	private String instituteName;
	private String itemCode;
	private String instituteCode;
	private String instituteFinalCode;
	
}
