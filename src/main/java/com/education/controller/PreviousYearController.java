package com.education.controller;

import java.io.ByteArrayInputStream;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.entity.ChapterPreviousEntity;
import com.education.entity.PreviousYearQuestions;
import com.education.entity.Transaction;
import com.education.entity.VideoLecturePrevious;
import com.education.model.ChapterPreviousDTO;

import com.education.model.PreviousQuestionPaperDTO;

import com.education.model.PreviousYearItemDTO;
import com.education.model.PreviousYearQuestionsDTO;
import com.education.model.Response2;
import com.education.model.ResponseWithList;
import com.education.model.ResponseWithObject;
import com.education.model.VideoLecturePreviousDTO;
import com.education.repository.TransactionRepo;
import com.education.services.PreviousYearService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "PREVIOUS YEAR-API")
public class PreviousYearController {

	@Autowired
	private PreviousYearService previousYearService;
	
	
	@Autowired
	private TransactionRepo transactionRepo;

	@PostMapping(value = "/addPreviousYearQuestion")
	@Operation(summary = "to add previous year question paper", description = "this api is used to add previous year question paper")
	public ResponseEntity<?> addPreviousQuestions(@RequestBody PreviousYearQuestionsDTO previousYearQuestionsDTO) {
		String response = previousYearService.savePreviousYearPaper(previousYearQuestionsDTO);
		if ("Success".equals(response)) {
			return new ResponseWithObject().generateResponse("Previous Year Questions are added", HttpStatus.OK, "200",
					previousYearQuestionsDTO);
		} else {
			return new ResponseWithObject().generateResponse("No Previous Year Questions are added",
					HttpStatus.INTERNAL_SERVER_ERROR, "500", "");
		}
	}

	@GetMapping(value = "/findAllPreviousYear")
	@Operation(summary="to find previous paper",description="this api is used to find the previous paper")
	public ResponseEntity<Object> findPreviousYearPaper() {
		List<PreviousYearQuestions> previousList = previousYearService.getAllPreviousYear();
		return new ResponseWithList().generateResponse("All Previous Year Questions are displayed", HttpStatus.OK,
				"200", previousList);
	}

	@GetMapping(value = "/getAllPreviousYearItemCode")
	@Operation(summary = "to get all the item course sub code", description = "this api is to get all items course sub code")
	public List<PreviousYearItemDTO> getAllPreviousItem(@RequestParam("previousYearCode") String previousYearCode) {

		List<PreviousYearItemDTO> responseData = new ArrayList<>();
		PreviousYearItemDTO videoObj = new PreviousYearItemDTO("Video Lectures", previousYearCode + "VIDEOLECTURE");
		PreviousYearItemDTO questionObj = new PreviousYearItemDTO("Previous Year Questions ",
				previousYearCode + "PREVIOUSYEARQUESTIONS");

		responseData.add(videoObj);
		responseData.add(questionObj);
		return responseData;
	}


	@PostMapping(value = "/addChapterPreviousYear")
	@Operation(summary="to add chapter previous year",description="this api is used to add chapter previous year")
	public ResponseEntity<?> addChapterPrevious(@RequestBody ChapterPreviousDTO chapterPreviousDTO) {
		String responseData = previousYearService.saveChapterPrevious(chapterPreviousDTO);
		if ("Success".equals(responseData)) {
			return new ResponseWithObject().generateResponse("Previous year  of chapter list  are added", HttpStatus.OK,
					"200", chapterPreviousDTO);
		} else {
			return new ResponseWithObject().generateResponse("Previous year of chapter list  are not added",
					HttpStatus.BAD_REQUEST, "400", "");
		}

	}
	@GetMapping(value="/findAllChapterPrevious")
	@Operation(summary="to find all chapter previous",description="this api is used to find all the  chapter previous year")
	public ResponseEntity<Object> findAllChapterPrevious()
	{
		List<ChapterPreviousEntity> chapterPrevious=previousYearService.findAllChapterPrevious();
		return new ResponseWithList().generateResponse("All Previous chapter list are displayed", HttpStatus.OK, "200", chapterPrevious);
	}

	@PostMapping(value = "/addPreviousYearVideoLecture",consumes= {MediaType.MULTIPART_FORM_DATA_VALUE},produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="to upload  previous year video lectures",description="this api is used to upload previous year video lectures")
	public ResponseEntity<?> addPreviousVideoLecture(@ModelAttribute VideoLecturePreviousDTO videoLecturePreviousDTO) {
		String responseData = previousYearService.saveVideoLecturePrevious(videoLecturePreviousDTO);
		if ("Success".equals(responseData)) {
			return Response2.generateResponse("Previous year  video lecture are added", HttpStatus.OK,
					"200");
		} else {
			return  Response2.generateResponse("Previous year video lecture are not added",
					HttpStatus.BAD_REQUEST, "400");
		}

	}

	@GetMapping(value = "/findPreviousVideoLecture")
	@Operation(summary="to find previous video lecture",description="this api is used to find the previous video lecture")
	public ResponseEntity<?> getPreviousVideoLecture(@RequestParam("finalPreviousCode") String finalPreviousCode) {
		List<VideoLecturePrevious> previousList = previousYearService.getVideosByPreviousChapter(finalPreviousCode);
		return new ResponseWithList().generateResponse("Previous Video Lecture are get displayed", HttpStatus.OK, "200",
				previousList);
	}

	@GetMapping(value = "streamPreviousVideoLecture/{videoLecturePreviousId}")
	@Operation(summary = "to stream previous  video lectures", description = "this api is used to stream the previous video lectures")
	public ResponseEntity<Object> streamPreviousVideo(@PathVariable("videoLecturePreviousId") Long videoLecturPreviousId)
		 {
		
		//if("Video 1".equals(title))
		//{
		byte[] videoPreviousBytes = previousYearService.getVideoByVideoLecturePreviousId(videoLecturPreviousId);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayInputStream resource = new ByteArrayInputStream(videoPreviousBytes);
		return new ResponseEntity<>(new InputStreamResource(resource), header, HttpStatus.OK);
		//}
		//else
		//{
			//Transaction tr=transactionRepo.findByEmail(email);
			//if(tr!=null && tr.isPaid())
			//{
				//byte[] videoPreviousBytes = previousYearService.getVideoByVideoLecturePreviousId(videoLecturPreviousId);
				//HttpHeaders header = new HttpHeaders();
				//header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				//ByteArrayInputStream resource = new ByteArrayInputStream(videoPreviousBytes);
				//return new ResponseEntity<>(new InputStreamResource(resource), header, HttpStatus.OK);	
			//}
			//else
			//{
				//return new ResponseEntity<>("Payment failed!",HttpStatus.BAD_GATEWAY);
			//}
		}
	
	@PostMapping(value="/addPreviousQuestionPaper",consumes=MediaType.MULTIPART_FORM_DATA_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="to add the previous question paper",description="this api is used to add the previous year question paper")
	public ResponseEntity<?>  addNote(@ModelAttribute PreviousQuestionPaperDTO previousQuestionPaperDTO)
	{
		String  response=previousYearService.savePreviousQuestionPaper(previousQuestionPaperDTO);
		if("Success".equals(response))
		{
			return new ResponseWithObject().generateResponse("Previous Year Question paper are saved successfully",HttpStatus.OK,"200",previousQuestionPaperDTO);
			
		}
		else
		{
			return new ResponseWithObject().generateResponse("Notes are not saved successfully",HttpStatus.INTERNAL_SERVER_ERROR,"500","");
		}
		
	}

	@GetMapping(value = "/findPreviousQuestionPaperByPreviousItemCode")
	@Operation(summary="to find previous question paper by previous  item code",description="this api is used to find out the previous question paper by  previous item code")
	public ResponseEntity<?> getPreviosQuestionPaperByPreviousItemCode(@RequestParam("previousYearItemCode") String previousYearItemCode) 
	{
	    
	    List<PreviousQuestionPaperDTO> preList = previousYearService.getAllPreviousYearQuestionPaperByPreviousYearItemCode(previousYearItemCode);
	    return new ResponseWithList().generateResponse("provide", HttpStatus.OK, "200", preList);
	}
	@GetMapping(value = "/findPreviousChapterListByPreviousItemCode")
	@Operation(summary="to find previous chapter list by previous  item code",description="this api is used to find out the previous chapter list by  previous item code")
	public ResponseEntity<?> getPreviousChapterByPreviousItemCode(@RequestParam("previousYearItemCode") String previousYearItemCode) 
	{
	    
	    List<ChapterPreviousEntity> previousChapterList = previousYearService.getPreviousChapterByPreviousYearItemCode(previousYearItemCode);
	    return new ResponseWithList().generateResponse("provide", HttpStatus.OK, "200", previousChapterList);
	}
	@GetMapping(value="/findPreviousQuestionPaperByPreviousQuestionId/{previousQuestionPaperId}")
	@Operation(summary="to find by previous question paper By Id",description="this api is used to find by single previous question paper pdf by id")
	public ResponseEntity<InputStreamResource> getPreviousQuestionPaperById(@PathVariable("previousQuestionPaperId") Long previousQuestionPaperId) 
	{
	    byte[] questionBytes=previousYearService.getPreviousQuestionPaperById(previousQuestionPaperId);
	    if(questionBytes!=null)
	    {
	    	HttpHeaders headers=new HttpHeaders();
	    	headers.setContentType(MediaType.APPLICATION_PDF);
	    	ByteArrayInputStream stream = new ByteArrayInputStream(questionBytes);
         return new ResponseEntity<>(new InputStreamResource(stream), headers, HttpStatus.OK);
	    }
	    else
	    {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}

