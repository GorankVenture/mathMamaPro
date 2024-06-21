package com.education.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PreviousYearQuestionsDTO {
	
	private Long previousYearId;
	private String previousYearName;
	private String previousYearCode;

}
