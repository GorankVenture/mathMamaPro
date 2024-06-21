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
import com.education.model.Response2;
import com.education.model.ResponseWithList;

import com.education.model.VideoSolutionsDTO;
import com.education.repository.TransactionRepo;
import com.education.services.VideoSolutionsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("api")
@Tag(name="VideoSolutions-API")
public class VideoSolutionsController {

	
	
	
	@Autowired
	private TransactionRepo  transactionRepo;
	@Autowired
	private VideoSolutionsService videoSolutionsService;
	@PostMapping(value="/uploadVideoSolutions",consumes= {MediaType.MULTIPART_FORM_DATA_VALUE},produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="to upload the video lecturers",description="this api is used to upload the video solutions")
	public  ResponseEntity<Object> uploadVideo(@ModelAttribute VideoSolutionsDTO videoSolutionsDTO)
	{
		String r=videoSolutionsService.saveVideoSolutions(videoSolutionsDTO);
		if("Success".equals(r))
		{
			return Response2.generateResponse("Videos are get uploaded", HttpStatus.OK,"200");
		}
		else
		{
			return Response2.generateResponse("No video is there",HttpStatus.INTERNAL_SERVER_ERROR,"500");
		}
	}
	@PutMapping(value="/updateVideoSolutions",consumes= {MediaType.MULTIPART_FORM_DATA_VALUE},produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="to update the video lecturers",description="this api is used to update the video solutions")
	public  ResponseEntity<Object> updateVideoSolutions(@ModelAttribute VideoSolutionsDTO videoSolutionsDTO)
	{
		String r=videoSolutionsService.saveVideoSolutions(videoSolutionsDTO);
		if("Success".equals(r))
		{
			return Response2.generateResponse("Videos are get updated successfully", HttpStatus.OK,"200");
		}
		else
		{
			return Response2.generateResponse("No video is there and giving error",HttpStatus.INTERNAL_SERVER_ERROR,"500");
		}
	}
	
	@GetMapping(value="streamVideoSolutions/{videoSolutionsId}")
	@Operation(summary="to stream video solutions",description="this api is used to stream video solutions")
	public ResponseEntity<Object> streamVideo(@PathVariable  Long videoSolutionsId)
	{
		
		
		
		//if("Video 1".equals(title) ) {
			byte[] videoBytes=videoSolutionsService.getVideoById(videoSolutionsId);
			HttpHeaders header=new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			ByteArrayInputStream stream=new ByteArrayInputStream(videoBytes);
			return new ResponseEntity<>(new InputStreamResource(stream),header,HttpStatus.OK);
		//}
		
		    //Transaction transaction=transactionRepo.findByEmail(email);
			//if(transaction!=null && transaction.isPaid())
			//{
				//byte[] videoBytes=videoSolutionsService.getVideoById(videoSolutionsId);
				//HttpHeaders header=new HttpHeaders();
				//header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				//ByteArrayInputStream stream=new ByteArrayInputStream(videoBytes);
				//return new ResponseEntity<>(new InputStreamResource(stream),header,HttpStatus.OK);
			//}
			//else {
				//return new ResponseEntity<>("please pay first",HttpStatus.BAD_GATEWAY);
			//}
		
	}
	

@GetMapping(value="/getAllVideoSolutionsByMaterialItemCode")
@Operation(summary="to get all video solutions by material item code",description="this api is used to get all the video solutions by material item code")
public ResponseEntity<Object> getAllVideoSolutionsByMaterialItemCode(@RequestParam ("materialItemCode") String materialItemCode)
{
	List<VideoSolutionsDTO> tList =videoSolutionsService.getVideoSolutionsByMaterialItemCode(materialItemCode);
	return new ResponseWithList().generateResponse("provide",HttpStatus.OK,"200",tList);
}
	
	
	
}
