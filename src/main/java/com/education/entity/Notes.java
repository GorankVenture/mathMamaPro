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
@Table(name="notes")
public class Notes {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="note_id")
	private Long notesId;
	@Column(name="note_names")
	private String noteNames;
	@Column(name="item_code")
	private String itemCode;
    @Column(name="note_pdf")
	private byte[] notesPdf;
   // @Column(name="is_paid")
   // private  boolean isPaid;
	
	
}




