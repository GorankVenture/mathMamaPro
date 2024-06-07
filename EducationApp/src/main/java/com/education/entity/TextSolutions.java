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
@Table(name="text_solutions")
public class TextSolutions {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="text_solution_id")
	private Long textSolutionsId;
	@Column(name="text_solutions_name")
	private String textSolutionsName;
	@Column(name="material_item_code")
	private String materialItemCode;
	
	@Column(name="text_solution_pdf")
	private byte[]  textSolutionsPdf;
	
}
