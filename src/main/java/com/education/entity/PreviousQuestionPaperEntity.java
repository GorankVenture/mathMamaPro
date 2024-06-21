package com.education.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

@Entity
@Table(name="previous_question_paper_details")
public class PreviousQuestionPaperEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="previous_question_paper")
	private Long previousQuestionPaperId;
	@Column(name="previous_question_paper_name")
	private String previousQuestionPaperName;
	@Column(name="previous_year_item_code")
	private String previousYearItemCode;
	@Column(name="previous_question_paper_data")
	private byte[] previousQuestionPaperData;
}
