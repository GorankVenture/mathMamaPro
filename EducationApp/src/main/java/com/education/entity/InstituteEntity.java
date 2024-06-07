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
@Table(name="institute_details")
public class InstituteEntity {

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="institute_id")
	private Long instituteId;
	@Column(name="institute_name")
	private String instituteName;
	@Column(name="item_code")
	private String itemCode;
	@Column(name="institute_code")
	private String instituteCode;
	@Column(name="institute_final_code")
	private String instituteFinalCode;
	
	
}
