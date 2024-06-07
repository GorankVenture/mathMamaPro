package com.education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.education.entity.CourseEntity;
import com.education.model.CourseDTO;
import com.education.model.ItemDTO;
import com.education.model.ResponseWithList;
import com.education.model.ResponseWithObject;
import com.education.services.CourseService;
import com.education.services.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@CrossOrigin(origins="*")
@RequestMapping("api")
@Tag(name="Course-API")
public class CourseController {
    
	
	@Autowired
	private CourseService courseService;
	
	
	@Autowired
	private TransactionService transactionService;
	@PostMapping(value="/addCourse")
	@Operation(summary="to add the Course details",description="this api is basically to add the course details")
	public ResponseEntity<?> addCourse(@RequestBody CourseDTO courseDTO)
	{
		CourseDTO savedDTO=courseService.saveCourse(courseDTO);
		if(savedDTO!=null)
		{
			return new ResponseWithObject().generateResponse("Course are saved",HttpStatus.OK,"200",savedDTO);
		}
		else
		{
			return new ResponseWithObject().generateResponse("Course are not saved",HttpStatus.BAD_REQUEST,"400","");
		}
	}
	
	@GetMapping(value="/findAllCourseByTransaction")
	@Operation(summary="find all the course by transaction",description="this api is used to find all course by transaction")
	public ResponseEntity<?> getCourseApproved(@RequestParam String transactionId)
	{
		boolean isApproved=transactionService.checkTransactionApproved(transactionId);
		if(isApproved)
		{
			List<CourseDTO> courseList=courseService.getAllCourses();
			return new  ResponseWithList().generateResponse("All course list get displayed by transactions", HttpStatus.OK,"200", courseList);
		}
		else
		{
			return new  ResponseWithList().generateResponse("No course list get displayed by transactions", HttpStatus.BAD_REQUEST,"400", "");
		}
	}
	@GetMapping(value="/findAllCourse")
	@Operation(summary="to find all the courses",description="this api is to find all the course")
	public ResponseEntity<?> findCourse()
	{
		List<CourseEntity> ce=courseService.getAllCourse();
		
		List<String> checkdata = new ArrayList<>();
		List<CourseEntity> responsedata = new ArrayList<>();
		
		for(CourseEntity item :ce) {
			
			
			if(!item.getCourseName().contains("Formula Test") && !checkdata.contains(item.getCourseCode()))
             {
				checkdata.add(item.getCourseCode());
				responsedata.add(item);
			}
		}
		
		return new ResponseWithList().generateResponse("provide",HttpStatus.OK,"200",responsedata);
	}
	
	
	
	/*@GetMapping(value="/getAllItem")
	@Operation(summary="to get all the item course code",description="this api is to get all items course code")
	public List<ItemDTO> getAllItem(@RequestParam ("courseCode") String courseCode)
	{
		
		List<ItemDTO> responseData = new ArrayList<>();
		ItemDTO videoObj = new ItemDTO("Video Lectures",courseCode+"VIDEO LECTURE");
		ItemDTO testObj = new ItemDTO("Test Series",courseCode+"TEST SERIES");
		ItemDTO importanObj = new ItemDTO("Important Question",courseCode+"IMPORTANT QUESTION");
		ItemDTO noteObj = new ItemDTO("Notes",courseCode+"NOTES");	
		responseData.add(videoObj);
		responseData.add(testObj);
		responseData.add(importanObj);
		responseData.add(noteObj);
		
		if("11C".equals(courseCode) ||"12C".equals(courseCode)) {
			ItemDTO materialObj = new ItemDTO("Material Solutions",courseCode+"MATERIAL");
			responseData.add(materialObj);
		}

		return responseData;
		
	}*/
	
	@GetMapping(value="/getAllItemCourseSubCode")
	@Operation(summary="to get all the item course sub code",description="this api is to get all items course sub code")
	public List<ItemDTO> getAllItemCourse(@RequestParam ("courseSubCode") String courseSubCode)
	{
		
		List<ItemDTO> responseData = new ArrayList<>();
		ItemDTO videoObj = new ItemDTO("Video Lectures",courseSubCode+"VIDEOLECTURE");
		ItemDTO testObj = new ItemDTO("Test Series",courseSubCode+"TESTSERIES");
		ItemDTO importanObj = new ItemDTO("Important Question",courseSubCode+"IMPORTANTQUESTION");
		ItemDTO noteObj = new ItemDTO("Notes",courseSubCode+"NOTES");	
		responseData.add(videoObj);
		responseData.add(testObj);
		responseData.add(importanObj);
		responseData.add(noteObj);
		if("11C".equals(courseSubCode) ||"12C".equals(courseSubCode)) {
			ItemDTO materialObj = new ItemDTO("Material Solutions",courseSubCode+"MATERIAL");
			responseData.add(materialObj);
		}
		return responseData;
	}
	
	@GetMapping(value="/getCourseCode")
	@Operation(summary="to get the course Code",description="this api is basically to find out the coursecode nd it will give the list of course sub names")
	public ResponseEntity<?> getCourseDetails(@RequestParam ("courseCode") String courseCode)
	{
		     List<CourseEntity> cE=courseService.getByCourseCode(courseCode);
		     
          List<String> courseSubNames=new ArrayList<>();
          for(CourseEntity d:cE)
          {
        	  courseSubNames.add(d.getCourseSubName());
          }
		     return new ResponseWithList().generateResponse("Courses are added to courseCode",HttpStatus.OK,"200",cE);
		     
	}
	
	
}