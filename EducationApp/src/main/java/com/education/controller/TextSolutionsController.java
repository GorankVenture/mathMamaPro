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
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.model.ResponseWithList;
import com.education.model.ResponseWithObject;
import com.education.model.TextSolutionsDTO;
import com.education.services.TextSolutionsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "TEXT SOLUTIONS-API")
public class TextSolutionsController {
   
	
	@Autowired
	private TextSolutionsService textSolutionsService;
	
	@PostMapping(value="/addTextSolutions",consumes= {MediaType.MULTIPART_FORM_DATA_VALUE},produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="to add Text Solutions",description="this api is used to add the text solutions")
	public ResponseEntity<?> addTextSolutions(@ModelAttribute TextSolutionsDTO tDTO)
	{
		TextSolutionsDTO f=textSolutionsService.saveTextSolution(tDTO);
		if(f!=null)
		{
			return new ResponseWithObject().generateResponse("Text Solutions are displayed",HttpStatus.OK,"200",f);
		}
		else
		{
			return new ResponseWithObject().generateResponse("Text Solutions are not displayed",HttpStatus.INTERNAL_SERVER_ERROR,"500","");
		}
	}
	@GetMapping(value="/getAllTextSolutionsByMaterialItemCode")
	@Operation(summary="to get all the text solutions by chapter code",description="this api is used to get all the text solutions by chapter code")
	public ResponseEntity<?> getAllTextSolutionsByMaterialItemCode(@RequestParam ("materialItemCode") String materialItemCode)
	{
		List<TextSolutionsDTO> tList =textSolutionsService.getTextSolutionsByMaterialItemCode(materialItemCode);
		return new ResponseWithList().generateResponse("provide",HttpStatus.OK,"200",tList);
				
	}
	@GetMapping(value="/findTextSolutionsById/{textSolutionsId}")
	@Operation(summary="to find by text solutions by Id",description="this api is used to find by text solutions  by id")
	public ResponseEntity<InputStreamResource> getTestSeriesById(@PathVariable("textSolutionsId") Long textSolutionsId) 
	{
	    byte[] textBytes=textSolutionsService.getTextSolutionsById(textSolutionsId);
	    if(textBytes!=null)
	    {
	    	HttpHeaders headers=new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_PDF);
	    	headers.setContentDispositionFormData("attachment", "testSolutions.pdf");
	    	ByteArrayInputStream stream = new ByteArrayInputStream(textBytes);
	     return new ResponseEntity<>(new InputStreamResource(stream), headers, HttpStatus.OK);
	    }
	    else
	    {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}


