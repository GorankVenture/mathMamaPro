package com.education.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.entity.CourseEntity;

import com.education.model.QuestionDTO;
import com.education.model.ResponseWithList;
import com.education.model.ResponseWithObject;
import com.education.services.CourseService;
import com.education.services.QuestionService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "Quiz-API")
public class QuizController {

	

	@Autowired
	private QuestionService questionService;
	@Autowired
	private CourseService courseService;


	@PostMapping(value = "/addQuestion")
	@Operation(summary = "to add the question details", description = "this api is used to add the question details")
	public ResponseEntity<Object> addQuestion(@RequestBody QuestionDTO questionDTO) {
		QuestionDTO savedDTO = questionService.saveQuestion(questionDTO);
		if (savedDTO!=null) {
			return new ResponseWithObject().generateResponse("Questions are saved", HttpStatus.OK, "200", savedDTO);
		} else {
			return new ResponseWithObject().generateResponse("Questions are not saved", HttpStatus.BAD_REQUEST, "400",
					"");
		}
	}

	@PutMapping(value = "/updateQuestion")
	@Operation(summary = "to update the question details", description = "this api is used to update the question details")
	public ResponseEntity<Object> updateQuestion(@RequestBody QuestionDTO questionDTO) {
		QuestionDTO savedDTO = questionService.saveQuestion(questionDTO);
		if (savedDTO!=null) {
			return new ResponseWithObject().generateResponse("Questions are updated successfully", HttpStatus.OK, "200", savedDTO);
		} else {
			return new ResponseWithObject().generateResponse("Questions are not updated successfully", HttpStatus.BAD_REQUEST, "400",
					"");
		}
	}
	@GetMapping(value="/getAllQuestionsByCourses")
	@Operation(summary="get all questions by courses",description="this api is basically to find all questions by courses")
	public ResponseEntity<Object> findQuestionsByCourses()
	{
	  List<CourseEntity> courseData=courseService.getAllCourse();
	  return new ResponseWithList().generateResponse("Quiz are displayed according to course wise", HttpStatus.OK, "200", courseData);
	}
	@GetMapping(value = "/getAllQuizByCourseSubCode")
	@Operation(summary = "to get all quiz by course sub code", description = "this api is usually to get all the quiz by course code")
	public ResponseEntity<Object> getAllQuizByCourseSubCode(@RequestParam("courseSubCode") String courseSubCode) 
	{
		List<QuestionDTO> tr = questionService.getQuestionsByCourseSubCode(courseSubCode);
		return new ResponseWithList().generateResponse("Quiz will displayed according to courseCode", HttpStatus.OK,
				"200", tr);
	}
}