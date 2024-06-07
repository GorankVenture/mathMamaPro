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
public class QuizResult {

	
	private int correct;
	private int wrong;
	private int totalQuestion;
	private List<QuestionDetail> questionDetails;
	//private List<String> rightAnswer;
	//private List<String> wrongAnswer;
}
