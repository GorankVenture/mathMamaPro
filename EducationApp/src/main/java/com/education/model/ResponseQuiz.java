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
public class ResponseQuiz {

	
	
	
	private List<Long> questionId;
	private List<String> selectedAnswer;
}
