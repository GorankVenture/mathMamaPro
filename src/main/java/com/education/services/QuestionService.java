package com.education.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.repository.QuestionRepo;

import com.education.entity.QuestionEntity;
import com.education.model.QuestionDTO;

@Service
public class QuestionService {

	
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(QuestionService.class);
	@Autowired
	private QuestionRepo questionRepo;
	@Autowired
	private ModelMapper mapper;
	public List<QuestionEntity> getAllQuestions()
	{
		return questionRepo.findAll();
	}
	
	
	
	public QuestionDTO saveQuestion(QuestionDTO questionDTO)
	{
		try
		{

			QuestionEntity questionEntity1=new QuestionEntity();
			questionEntity1.setQuestion(questionDTO.getQuestion());
		       // questionEntity.setOption1(questionDTO.getOption1());
			    String option1=questionDTO.getOption1();
			    String option2=questionDTO.getOption2();
			    String option3=questionDTO.getOption3();
			    String option4=questionDTO.getOption4();
			    questionEntity1.setOption1(option1);
			    questionEntity1.setOption2(option2);
			    questionEntity1.setOption3(option3);
			    questionEntity1.setOption4(option4);
			    List<String> options=Arrays.asList(option1,option2,option3,option4);
			    questionEntity1.setOptions(options);
			    questionDTO.setOptions(options);
		        questionEntity1.setRightAnswer(questionDTO.getRightAnswer());
		        questionEntity1.setIncorrectAnswer(questionDTO.getIncorrectAnswer());
		        questionEntity1.setType(questionDTO.getType());
		        questionEntity1.setDifficulty(questionDTO.getDifficulty());
		        questionEntity1.setCategory(questionDTO.getCategory());
		        questionEntity1.setCourseSubCode(questionDTO.getCourseSubCode());
		        questionEntity1.setTimePeriod(questionDTO.getTimePeriod());
		        questionRepo.save(questionEntity1);
		        return questionDTO;
//		}
//		else
//		{
//	    questionEntity.setQuestion(questionDTO.getQuestion());
//       // questionEntity.setOption1(questionDTO.getOption1());
//	    String option1=questionDTO.getOption1();
//	    String option2=questionDTO.getOption2();
//	    String option3=questionDTO.getOption3();
//	    String option4=questionDTO.getOption4();
//	    questionEntity.setOption1(option1);
//	    questionEntity.setOption2(option2);
//	    questionEntity.setOption3(option3);
//	    questionEntity.setOption4(option4);
//	    List<String> options=Arrays.asList(option1,option2,option3,option4);
//	    questionEntity.setOptions(options);
//	    questionDTO.setOptions(options);
//        questionEntity.setRightAnswer(questionDTO.getRightAnswer());
//        questionEntity.setIncorrectAnswer(questionDTO.getIncorrectAnswer());
//        questionEntity.setType(questionDTO.getType());
//        questionEntity.setDifficulty(questionDTO.getDifficulty());
//        questionEntity.setCategory(questionDTO.getCategory());
//        questionEntity.setCourseSubCode(questionDTO.getCourseSubCode());
//        questionEntity.setTimePeriod(questionDTO.getTimePeriod());
//        questionRepo.save(questionEntity);
//        return questionDTO;
//		}
		}catch(Exception e)
		 {
		  e.printStackTrace();
		 log.error("There is an error for questions : {}",e.getMessage());
		 return null;
		 }
	   } 
	
	public List<QuestionDTO> getQuestionsByCourseSubCode(String courseSubCode)
	{
		List<QuestionEntity> qList=questionRepo.findByCourseSubCode(courseSubCode);
		List<QuestionDTO> qDTO=new ArrayList<>();
		
		for(QuestionEntity qE: qList)
		{
			QuestionDTO questionDTOs=new QuestionDTO();
			questionDTOs.setQuestionId(qE.getQuestionId());
			questionDTOs.setQuestion(qE.getQuestion());
			questionDTOs.setOption1(qE.getOption1());
			questionDTOs.setOption2(qE.getOption2());
			questionDTOs.setOption3(qE.getOption3());
			questionDTOs.setOption4(qE.getOption4());
		     questionDTOs.setOptions(qE.getOptions());
			questionDTOs.setRightAnswer(qE.getRightAnswer());
			questionDTOs.setIncorrectAnswer(qE.getIncorrectAnswer());
			questionDTOs.setType(qE.getType());
			questionDTOs.setDifficulty(qE.getDifficulty());
			questionDTOs.setCategory(qE.getCategory());
			questionDTOs.setTimePeriod(qE.getTimePeriod());
			//questionDTOs.setQuestionCode(qE.getQuestionCode());
			questionDTOs.setCourseSubCode(courseSubCode);
			qDTO.add(questionDTOs);
		}
		return qDTO;
	}
    
}
	
	


