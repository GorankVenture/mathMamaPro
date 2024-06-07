package com.education.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.entity.CourseEntity;
import com.education.entity.QuestionEntity;
//import com.education.entity.QuestionEntity;
import com.education.model.QuestionDTO;
import com.education.model.QuizResult;
import com.education.model.ResponseQuiz;
//import com.education.model.QuizResult;
//import com.education.model.ResponseQuiz;
//import com.education.model.Response;
//import com.education.model.Response2;
import com.education.model.ResponseWithList;
import com.education.model.ResponseWithObject;
import com.education.services.CourseService;
import com.education.services.QuestionService;
//import com.education.services.QuizService;

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
//
//	@PostMapping(value = "/addQuiz")
//	@Operation(summary = "to add the quiz details", description = "this api is used to add the quiz details")
//	public ResponseEntity<?> addQuiz(@RequestBody QuizDTO quizDTO) {
//		String result = quizService.createQuiz(quizDTO);
//		if ("Success".equals(result)) {
//			return new ResponseWithObject().generateResponse("Quiz is created", HttpStatus.OK, "200", quizDTO);
//		} else {
//			return new ResponseWithObject().generateResponse("Quiz is not created", HttpStatus.BAD_REQUEST, "400",
//					quizDTO);
//		}
//	}

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

	/*@PostMapping("submitQuiz")
	public ResponseEntity<Object> submitQuiz(@RequestParam String courseSubCode, @RequestBody List<ResponseQuiz> responses) {
         QuizResult quizResult = questionService.calculateResult(courseSubCode, responses);
		return new ResponseWithObject().generateResponse("Quiz are already submitted", HttpStatus.OK, "200", quizResult);
	}*/
	
	
}