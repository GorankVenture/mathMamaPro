package com.education.controller;

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

import java.io.ByteArrayInputStream;
import java.util.List;

import com.education.entity.TestSeries;
import com.education.entity.Transaction;
import com.education.exception.TransactionNotFoundException;
import com.education.model.ResponseWithList;
import com.education.model.ResponseWithObject;
import com.education.model.TestSeriesDTO;
import com.education.services.TestSeriesService;
import com.education.services.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@CrossOrigin(origins="*")
@RequestMapping("api")
@Tag(name="TestSeries-API")
public class TestSeriesController {

	@Autowired
	TestSeriesService testSeriesService;
	
	
	
	@Autowired
	private TransactionService transactionService;
	@PostMapping(value="/addTestSeries",consumes= {MediaType.MULTIPART_FORM_DATA_VALUE},produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="to add the test series",description="this api is used to add test series")
	public ResponseEntity<Object> addTestSeries(@ModelAttribute TestSeriesDTO testSeriesDTO)
	{
		
		
		TestSeriesDTO savedDTO=testSeriesService.saveTestSeries(testSeriesDTO);
		if(savedDTO!=null)
		{
			return  new ResponseWithObject().generateResponse("Test Series details are saved", HttpStatus.OK, "200",savedDTO);
			
		}
		
		else
		{
			return  new ResponseWithObject().generateResponse("Test series have some error",HttpStatus.INTERNAL_SERVER_ERROR,"500","");
		}
	}
	@PutMapping(value="/updateTestSeries",consumes= {MediaType.MULTIPART_FORM_DATA_VALUE},produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="to update the test series",description="this api is used to update test series")
	public ResponseEntity<Object> updateTestSeries(@ModelAttribute TestSeriesDTO testSeriesDTO)
	{
		TestSeriesDTO savedDTO=testSeriesService.saveTestSeries(testSeriesDTO);
		if(savedDTO!=null)
		{
			return  new ResponseWithObject().generateResponse("Test Series details are updated successfully", HttpStatus.OK, "200",savedDTO);
			
		}
		
		else
		{
			return  new ResponseWithObject().generateResponse("Test series are not updated successfully",HttpStatus.INTERNAL_SERVER_ERROR,"500","");
		}
	}
	@GetMapping(value="/findTestSeries")
	@Operation(summary="find the test series",description="this api is to find out all the test series")
	public ResponseEntity<Object> getAllTestSeries()
	{
		List<TestSeries> result=testSeriesService.getAllTestSeries();
		return new ResponseWithList().generateResponse("provide",HttpStatus.OK,"200",result);
	}
	
	@GetMapping(value="/findTestSeriesByItemCode")
	@Operation(summary="to find test series by item code",description="this api is used to find out the test series by item code")
	public ResponseEntity<Object> getTestSeriesByItemCode(@RequestParam ("itemCode") String itemCode)
	{
		List<TestSeriesDTO> result=testSeriesService.getAllTestSeriesByItemCode(itemCode);
		return new ResponseWithList().generateResponse("provide", HttpStatus.OK, "200", result);
	}
	

@GetMapping(value="/findTestSeriesById/{testSeriesId}")
@Operation(summary="to find by test series By Id",description="this api is used to find by test series pdf by id")
public ResponseEntity<Object> getTestSeriesById(@PathVariable("testSeriesId") Long testSeriesId) 
{
	    
    	//Transaction transaction=transactionService.getGlobalIdAndEmail(testSeriesId, email);
    	  // if(transaction.isPaid())
    	   //{
    	  byte[] testBytes=testSeriesService.getTestSeriesById(testSeriesId);
    	  HttpHeaders headers=new HttpHeaders();
    	  headers.setContentType(MediaType.APPLICATION_PDF);
    	  headers.setContentDispositionFormData("attachment", "TestSeries.pdf");
    	  ByteArrayInputStream stream=new ByteArrayInputStream(testBytes);
    	  return new ResponseEntity<>(new InputStreamResource(stream),headers,HttpStatus.OK);
    	  //}
    	 //else 
    	 //{
		   // return new ResponseEntity<>("Payment failed!",HttpStatus.BAD_GATEWAY);
		 //}
	    //} catch(TransactionNotFoundException ex)
	    //{
	    	//return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	    //}
  }
}

   

	
	
	       
	
				
				
				
					
	


	
	

