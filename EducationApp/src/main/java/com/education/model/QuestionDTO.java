package com.education.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class QuestionDTO {

	private Long questionId;
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private List<String> options;
	private String rightAnswer;
	private List<String> incorrectAnswer;
	private String type;
	private String difficulty;
	private String category;
	private String courseSubCode;
	private String timePeriod;

}

