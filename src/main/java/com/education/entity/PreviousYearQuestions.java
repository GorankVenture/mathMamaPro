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
@Table(name="previous_year")
public class PreviousYearQuestions {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="previous_year_id")
	private Long previousYearId;
	@Column(name="previous_year_name")
	private String previousYearName;
	@Column(name="previous_year_code")
	private String previousYearCode;
	
}
