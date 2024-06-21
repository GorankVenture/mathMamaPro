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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.entity.ChapterRevisionEntity;
import com.education.entity.CourseEntity;
import com.education.entity.RevisionVideoEntity;
import com.education.entity.Transaction;
import com.education.model.ChapterRevisionDTO;
import com.education.model.Response2;
import com.education.model.ResponseWithList;
import com.education.model.ResponseWithObject;
import com.education.model.RevisionVideoDTO;
import com.education.repository.TransactionRepo;
import com.education.services.CourseService;
import com.education.services.RevisionVideoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("api")
@Tag(name="RevisionVideo-API")
public class RevisionVideoController {

	@Autowired
	private RevisionVideoService revisionVideoService;
	
	
	@Autowired
	private CourseService courseService;
	
	
	@Autowired
	private TransactionRepo transactionRepo;
	
	@GetMapping(value="/findAllRevisionCourse")
	@Operation(summary="to find all the  Revision Courses",description="this api is to find all the Revision Course")
	public ResponseEntity<Object> findRevisionCourse()
	{
		List<CourseEntity> ce=courseService.getAllCourse();
		
		List<String> checkdata = new ArrayList<>();
		List<CourseEntity> responsedata = new ArrayList<>();
		
		for(CourseEntity item :ce) {
			
			if(!item.getCourseName().contains("Formula Test")&& !checkdata.contains(item.getCourseSubCode())) {
				checkdata.add(item.getCourseSubCode());
				responsedata.add(item);
			}
		}
		
		return new ResponseWithList().generateResponse("provide",HttpStatus.OK,"200",responsedata);
	}
	
	@PostMapping(value="/addRevisionChapter")
	@Operation(summary="to add the revision chapter",description="this api is used to add the revision chapter")
	public ResponseEntity<Object> addRevisionChapter(@RequestBody ChapterRevisionDTO chapterRevisionDTO)
	{
		String responseRevision=revisionVideoService.saveRevisionChapter(chapterRevisionDTO);
				if("Success".equals(responseRevision))
				{
					return new ResponseWithObject().generateResponse("Chapter Revision are saved",HttpStatus.OK,"200",chapterRevisionDTO);
				}
				else
				{
					return new ResponseWithObject().generateResponse("No chapter Revision are saved",HttpStatus.INTERNAL_SERVER_ERROR,"500","");
				}
	}
	@PutMapping(value="/addRevisionChapter")
	@Operation(summary="to add the revision chapter",description="this api is used to add the revision chapter")
	public ResponseEntity<Object> updateRevisionChapter(@RequestBody ChapterRevisionDTO chapterRevisionDTO)
	{
		String responseRevision=revisionVideoService.saveRevisionChapter(chapterRevisionDTO);
				if("Success".equals(responseRevision))
				{
					return new ResponseWithObject().generateResponse("Chapter Revision are updated successfully",HttpStatus.OK,"200",chapterRevisionDTO);
				}
				else
				{
					return new ResponseWithObject().generateResponse("No chapter Revision are not updated",HttpStatus.INTERNAL_SERVER_ERROR,"500","");
				}
	}
	@GetMapping(value="/findAllRevisionChapterByCourseSubCode")
	@Operation(summary="find all revision chapter by course sub code",description="this api is used to find all the revision chapters by course sub code")
	public ResponseEntity<Object> getRevisionChapterByCourseSubCode(@RequestParam ("courseSubCode") String courseSubCode)

	{
		List<ChapterRevisionEntity> chapterList=revisionVideoService.getAllRevisionChapterByCourseSubCode(courseSubCode);
		return new ResponseWithList().generateResponse("Revision Chapter are listed",HttpStatus.OK, "200", chapterList);
	}
	
	@PostMapping(value="/uploadRevisionVideo",consumes= {MediaType.MULTIPART_FORM_DATA_VALUE},produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="to upload video lectures",description="this api is used to upload  video lectures")
	public  ResponseEntity<Object> uploadRevisionVideo(@ModelAttribute RevisionVideoDTO revisionVideoDTO)
	{
		String revisionResponse=revisionVideoService.saveRevisionVideoLecture(revisionVideoDTO);
		if("Success".equals(revisionResponse))
		{
			return Response2.generateResponse("Revision Videos are get uploaded", HttpStatus.OK,"200");
		}
		else
		{
			return Response2.generateResponse("No Revision video is there",HttpStatus.INTERNAL_SERVER_ERROR,"500");
		}
	}
	@PutMapping(value="/updateRevisionVideo",consumes= {MediaType.MULTIPART_FORM_DATA_VALUE},produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="to upload video lectures",description="this api is used to upload  video lectures")
	public  ResponseEntity<Object> updateRevisionVideo(@ModelAttribute RevisionVideoDTO revisionVideoDTO)
	{
		String revisionResponse=revisionVideoService.saveRevisionVideoLecture(revisionVideoDTO);
		if("Success".equals(revisionResponse))
		{
			return Response2.generateResponse("Revision Videos are get updated successfully", HttpStatus.OK,"200");
		}
		else
		{
			return Response2.generateResponse("No Revision video is there updated",HttpStatus.INTERNAL_SERVER_ERROR,"500");
		}
	}
	
	@GetMapping(value="/getRevisionVideoList")
	@Operation(summary="to get revision video list",description="this api is used to gedt the revision video list")
	public ResponseEntity<Object> getAllRevisionVideoByChapterRevisionFinalCode(@RequestParam ("chapterRevisionFinalCode") String chapterRevisionFinalCode)
	{
		List<RevisionVideoEntity> revisionFinalCodeResponse=revisionVideoService.getRevisionVideoByChapterRevisionFinalCode(chapterRevisionFinalCode);
		return new ResponseWithList().generateResponse("All Revision Video chapters are get displayed according to its final code", HttpStatus.OK, "200", revisionFinalCodeResponse);
	}
	
	@GetMapping(value="streamRevisionVideo/{videoRevisionId}")
	@Operation(summary="to stream video revision lectures",description="this api is used to stream the revision  video lectures")
	public ResponseEntity<Object> streamRevisionVideo(@PathVariable  Long videoRevisionId) 
	{
		//if("Video 1".equals(title))
		//{
		byte[] videoRevisionBytes=revisionVideoService.getRevisionVideoByVideoRevisionId(videoRevisionId);
		HttpHeaders header=new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayInputStream resource=new ByteArrayInputStream(videoRevisionBytes);
		return new ResponseEntity<>(new InputStreamResource(resource),header,HttpStatus.OK);
		//}
		//else
		//{
			
			//Transaction tr=transactionRepo.findByEmail(email);
			//if(tr!=null && tr.isPaid())
			//{
				//byte[] videoBytes=revisionVideoService.getRevisionVideoByVideoRevisionId(videoRevisionId);
				//HttpHeaders header=new HttpHeaders();
				//header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				//ByteArrayInputStream stream=new ByteArrayInputStream(videoBytes);
				//return new ResponseEntity<>(new InputStreamResource(stream),header,HttpStatus.OK);
			//}
			//else 
			//{
				//return new ResponseEntity<>("Payment failed",HttpStatus.BAD_GATEWAY);
			//}
		//}
	}
}
	

