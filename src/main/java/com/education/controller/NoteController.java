package com.education.controller;


import java.io.ByteArrayInputStream;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.entity.Transaction;
import com.education.model.NotesDTO;
import com.education.model.Response2;
import com.education.model.ResponseWithList;
import com.education.model.ResponseWithObject;
import com.education.services.NotesService;
import com.education.services.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api")
@Tag(name="Note-API")
public class NoteController {
	
	@Autowired
    private TransactionService transactionService;
	@Autowired
	private NotesService notesService;
	@PostMapping(value="/addNotes",consumes=MediaType.MULTIPART_FORM_DATA_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="to add the notes",description="this api is used to add the notes")
	public ResponseEntity<Object>  addNote(@ModelAttribute NotesDTO noteDTO)
	{
		NotesDTO sDTO=notesService.saveNote(noteDTO);
		if(sDTO!=null)
		{
			return new ResponseWithObject().generateResponse("Notes are saved successfully",HttpStatus.OK,"200",sDTO);
			
		}
		else
		{
			return new ResponseWithObject().generateResponse("Notes are not saved successfully",HttpStatus.INTERNAL_SERVER_ERROR,"500","");
		}
		
	}
	@PutMapping(value="/updateNotes",consumes=MediaType.MULTIPART_FORM_DATA_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="to update the notes",description="this api is used to update the notes")
	public ResponseEntity<Object>  updateNote(@ModelAttribute NotesDTO noteDTO)
	{
		NotesDTO sDTO=notesService.saveNote(noteDTO);
		if(sDTO!=null)
		{
			return new ResponseWithObject().generateResponse("Notes are updated successfully",HttpStatus.OK,"200",sDTO);
			
		}
		else
		{
			return new ResponseWithObject().generateResponse("Notes are not updated successfully",HttpStatus.INTERNAL_SERVER_ERROR,"500","");
		}
		
	}

	@GetMapping(value = "/findNoteByItemCode")
	@Operation(summary="to find note by item code",description="this api is used to find out the notes by item code")
	public ResponseEntity<Object> getNotesByItemCode(@RequestParam("itemCode") String itemCode) 
	{
	    
	    List<NotesDTO> notesDTOList = notesService.getAllNotesByItemCode(itemCode);
	    return new ResponseWithList().generateResponse("provide", HttpStatus.OK, "200", notesDTOList);
	}
	@GetMapping(value="/findNoteById/{noteId}")
	@Operation(summary="to find by notes By Id",description="this api is used to find by single note pdf by id")
	public ResponseEntity<Object> getNotesById(@PathVariable("noteId") Long noteId)
	{
		
		 //Transaction transResponseData=transactionService.getGlobalIdAndEmail(noteId, email);
		 //if(transResponseData.isPaid())
		 //{
	      byte[] tBytes=notesService.getNotesById(noteId);
	      HttpHeaders headers=new HttpHeaders();
	     headers.setContentType(MediaType.APPLICATION_PDF);
	     ByteArrayInputStream stream = new ByteArrayInputStream(tBytes);
          return new ResponseEntity<>(new InputStreamResource(stream), headers, HttpStatus.OK);
		 //}
		 //else
		 //{
			// return Response2.generateResponse("Payment failed!", HttpStatus.BAD_REQUEST, "400");
		 //}
	 }
}


	  
