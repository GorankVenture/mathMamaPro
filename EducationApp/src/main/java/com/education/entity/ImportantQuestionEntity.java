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
@Table(name="important_questions")
public class ImportantQuestionEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="important_id")
	private Long importantQuestionId;
	@Column(name="name")
	private String name;
	@Column(name="item_code")
	private String itemCode;
	@Column(name="important_questions")
	private byte[] importantQuestions;
	//@Column(name="is_paid")
	//private boolean isPaid;
	
	
}
