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
@Table(name="test_series")
public class TestSeries {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="test_series_id")
	private Long testSeriesId;
	@Column(name="test_series_name")
	private String testSeriesName;
	@Column(name="item_code")
	private String itemCode;
	@Column(name="test_pdf")
	private byte[] testPdf;
	
	
	//@Column(name="is_paid")
	//private boolean isPaid;

}
