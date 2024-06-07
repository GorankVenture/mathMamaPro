package com.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionDetail {

	private Long questionId;
	private String questionTitle;
	private String selectedAnswer;
	private String rightAnswer;
	private boolean isCorrect;
	
	  public QuestionDetail(Long questionId, String questionTitle, String rightAnswer, String selectedAnswer) {
	        this.questionId = questionId;
	        this.questionTitle = questionTitle;
	        this.rightAnswer = rightAnswer;
	  }
}
