package com.education.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class NotesDTO {

	private Long noteId;
	private String notesName;
	private String itemCode;
	private MultipartFile notePdf;
	private String baseNotePdf;
}