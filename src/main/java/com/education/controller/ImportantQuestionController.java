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
import com.education.model.ImportantDTO;
import com.education.model.Response2;
import com.education.model.ResponseWithList;
import com.education.model.ResponseWithObject;
import com.education.services.ImportantQuestionService;
import com.education.services.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api")
@Tag(name="ImportantQuestion-API")
public class ImportantQuestionController {
    
	
	@Autowired
	private ImportantQuestionService importantQuestionService;
	
	@Autowired
	private TransactionService transactionService;
	@PostMapping(value="/addImportantQuestion",consumes=MediaType.MULTIPART_FORM_DATA_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="to add Important Question",description="this api is used to add all the important question")
	public ResponseEntity<Object> addImportantQuestion(@ModelAttribute ImportantDTO importantDTO)
	{
		String t=importantQuestionService.saveImportantQuestion(importantDTO);
		if("Success".equals(t))
		{
			return new ResponseWithObject().generateResponse("Important Questions are saved",HttpStatus.OK,"200",importantDTO);
		}
		else
		{
			return new ResponseWithObject().generateResponse("Im[portant Questions are not saved",HttpStatus.BAD_REQUEST,"400","");
		}
	}
	@PutMapping(value="/updateImportantQuestion",consumes=MediaType.MULTIPART_FORM_DATA_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="to update Important Question",description="this api is used to update all the important question")
	public ResponseEntity<Object> updateImportantQuestion(@ModelAttribute ImportantDTO importantDTO)
	{
		String t=importantQuestionService.saveImportantQuestion(importantDTO);
		if("Success".equals(t))
		{
			return new ResponseWithObject().generateResponse("Important Questions are updated successfully",HttpStatus.OK,"200",importantDTO);
		}
		else
		{
			return new ResponseWithObject().generateResponse("Im[portant Questions are not updated successfully",HttpStatus.BAD_REQUEST,"400","");
		}
	}
	
	@GetMapping(value="/findImportantQuestionByItemCode")
	@Operation(summary="to find Important questions by course code",description="this api is basically to find out the important question by course code")
	public ResponseEntity<Object> findImportantQuestionByCourseCode(@RequestParam ("itemCode") String itemCode)
	{
		List<ImportantDTO> p=importantQuestionService.getAllImportantQuestionByItemCode(itemCode);
		return new ResponseWithList().generateResponse("provide", HttpStatus.OK, "200", p);
	}
	@GetMapping(value="/findImportantQuestionById/{importantQuestionId}",produces=MediaType.APPLICATION_PDF_VALUE)
	@Operation(summary="to find by important question by id",description="this api is used to find by single important question pdf by id")
	public ResponseEntity<Object> getImportantQuestionById(@PathVariable("importantQuestionId") Long importantQuestionId) 
	{
		
		//Transaction transaction=transactionService.getGlobalIdAndEmail(importantQuestionId, email);
		//if(transaction!=null && transaction.isPaid())
		//{
	    byte[] importantBytes=importantQuestionService.getImportantQuestionById(importantQuestionId);
	    HttpHeaders headers=new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_PDF);
	    headers.setContentDispositionFormData("attachment", "importantquestion.pdf");
	    ByteArrayInputStream stream = new ByteArrayInputStream(importantBytes);
         return new ResponseEntity<>(new InputStreamResource(stream), headers, HttpStatus.OK);
	    }
	    //else
	    //{
	    	//return 	Response2.generateResponse("Payment is failed",HttpStatus.BAD_REQUEST, "400");
	    //}
	   
	//}
	
	
}

