package com.education.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="questions_details")
public class QuestionEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="question_id")
	private Long questionId;
	@Column(name="question")
	private String question;
	@Column(name="option_1")
	private String option1;
	@Column(name="option_2")
	private String option2;
	@Column(name="option_3")
	private String option3;
	@Column(name="option_4")
	private String option4;
	@ElementCollection(fetch=FetchType.EAGER)
	@Column(name="options")
	private List<String> options;
    @Column(name="right_answer")
	private String rightAnswer;
    @Column(name="incorrect_answer")
    private List<String> incorrectAnswer;
    @Column(name="type")
    private String type;
    @Column(name="difficulty")
    private String difficulty;
    @Column(name="category")
    private String category;
    @Column(name="course_sub__code")
    private String courseSubCode;
    @Column(name="time_period")
    private String timePeriod;

}
