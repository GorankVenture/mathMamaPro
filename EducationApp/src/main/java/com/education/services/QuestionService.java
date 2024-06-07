package com.education.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.repository.QuestionRepo;

import com.education.entity.QuestionEntity;
import com.education.model.QuestionDTO;
import com.education.model.QuestionDetail;
import com.education.model.QuizResult;
import com.education.model.ResponseQuiz;
@Service
public class QuestionService {

	
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(QuestionService.class);
	@Autowired
	private QuestionRepo questionRepo;
	
	public List<QuestionEntity> getAllQuestions()
	{
		return questionRepo.findAll();
	}
	
	
	
	public QuestionDTO saveQuestion(QuestionDTO questionDTO)
	{
		try
		{
		QuestionEntity questionEntity=new QuestionEntity();
	    questionEntity.setQuestion(questionDTO.getQuestion());
       // questionEntity.setOption1(questionDTO.getOption1());
	    String option1=questionDTO.getOption1();
	    String option2=questionDTO.getOption2();
	    String option3=questionDTO.getOption3();
	    String option4=questionDTO.getOption4();
	    questionEntity.setOption1(option1);
	    questionEntity.setOption2(option2);
	    questionEntity.setOption3(option3);
	    questionEntity.setOption4(option4);
	    List<String> options=Arrays.asList(option1,option2,option3,option4);
	    questionEntity.setOptions(options);
	    questionDTO.setOptions(options);
        questionEntity.setRightAnswer(questionDTO.getRightAnswer());
        questionEntity.setIncorrectAnswer(questionDTO.getIncorrectAnswer());
        questionEntity.setType(questionDTO.getType());
        questionEntity.setDifficulty(questionDTO.getDifficulty());
        questionEntity.setCategory(questionDTO.getCategory());
        questionEntity.setCourseSubCode(questionDTO.getCourseSubCode());
        questionEntity.setTimePeriod(questionDTO.getTimePeriod());
        questionRepo.save(questionEntity);
        return questionDTO;
		} catch(Exception e)
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
    public QuizResult calculateResult(String courseSubCode,List<ResponseQuiz> responses)
    {
    	List<QuestionEntity> questionsEntity=questionRepo.findByCourseSubCode(courseSubCode);
    	
    	int correct=0;
    	int wrong=0;
    	int totalQuestions=questionsEntity.size();
    	List<QuestionDetail> questionDetails=new ArrayList<>();
    	
    
    	for(QuestionEntity questionData:questionsEntity)
    	{
    	//	QuestionEntity questionData=questionsEntity.get(i);
    		
    		Long questionId=questionData.getQuestionId();
    		String selectedAnswer=null;
    		
    		for(ResponseQuiz r:responses)
    			
    			
    		{
    			
    			//List<Long> questionId=r.getQuestionId();
    			//List<String> selectedAnswer=r.getSelectedAnswer();
    			int index=r.getQuestionId().indexOf(questionId);
 			      if(index!=-1)
    			{
    				 selectedAnswer=r.getSelectedAnswer().get(index);
    				break;
    			}
    		
    		}
    				
    		//ResponseQuiz response=responses.get(i).getSelectedAnswer();
    		 QuestionDetail questionDetail=new QuestionDetail(questionData.getQuestionId(),
    		            questionData.getQuestion(),
    		            questionData.getRightAnswer(),
    		            selectedAnswer
    		        );
    		

    		    
    		if(questionData.getRightAnswer().equals(selectedAnswer))
    		{
    			correct++;
    			questionDetail.setCorrect(true);
    		}
    		else
    		{
    			wrong++;
    			questionDetail.setCorrect(false);
    		}
    	questionDetails.add(questionDetail);		
    	}
    	 QuizResult result = new QuizResult();
    	    result.setCorrect(correct);
    	    result.setWrong(wrong);
    	    result.setTotalQuestion(totalQuestions);
    	    result.setQuestionDetails(questionDetails);
    	    
    	    return result;
    	}
}
	
	


