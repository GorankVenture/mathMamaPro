//package com.education.services;
//
//
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.education.entity.QuestionEntity;
//import com.education.entity.QuizEntity;
//import com.education.exception.QuizNotFoundException;
//import com.education.model.QuestionDTO;
//import com.education.model.QuizDTO;
//import com.education.model.QuizResult;
//import com.education.model.ResponseQuiz;
//import com.education.repository.QuestionRepo;
//import com.education.repository.QuizRepo;
//
//import jakarta.persistence.EntityNotFoundException;
//@Service
//public class QuizService {
//
//	
//	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(QuizService.class);
//	@Autowired
//	private QuestionRepo questionRepo;
//	@Autowired
//     private QuizRepo quizRepo;
//	 public String createQuiz(QuizDTO quizDTO) {
//
//		 try
//		 {
//	        
//              List<QuestionEntity> questions=questionRepo.findAll();
////			 }
//	        QuizEntity quiz = new QuizEntity();
//	        quiz.setTitle(quizDTO.getTitle());
//	       
//	        quiz.setQ(questions);
//	        quiz.setCourseSubCode(quizDTO.getCourseSubCode());
//	       // quiz.setQ(questions);
//	        quizRepo.save(quiz);
//	        return "Success";
//		 } catch(Exception e)
//		 {
//			 log.error("There is an error for creating a quiz : {} ",e.getMessage());
//			 return "Error";
//		 }
//		 }
//
//	    public List<QuestionDTO> getQuizQuestions(Long quizId) {
//	        Optional<QuizEntity> quizOptional = quizRepo.findById(quizId);
//	        if(quizOptional.isPresent())
//	        {
//	        List<QuestionEntity> questionsFromDB = quizOptional.get().getQ();
//	        List<QuestionDTO> questionsForUser = new ArrayList<>();
//	        
//	        for(QuestionEntity q : questionsFromDB)
//	        {
//	            QuestionDTO qw = new QuestionDTO();
//	            qw.setQuestionId(q.getQuestionId());
//	            qw.setQuestionTitle(q.getQuestionTitle());
//	            qw.setOption1(q.getOption1());
//	            qw.setOption2(q.getOption2());
//	            qw.setOption3(q.getOption3());
//	            qw.setOption4(q.getOption4());
//	            qw.setRightAnswer(q.getRightAnswer());
//	            //qw.setWrongAnswer(q.getWrongAnswer());
//	            qw.setCategory(q.getCategory());
//	            questionsForUser.add(qw);
//	        }
//	        return questionsForUser;
//	        }
//	        else
//	        {
//	        	return Collections.emptyList();
//	        }
//	    }
//	     
//	    
//	    
//	    public List<QuestionDTO> getQuizzesByCourseSubCode(String courseSubCode)
//	    {
//	    	
//	    	List<QuizEntity> quizzes=quizRepo.findByCourseSubCode(courseSubCode);
//		        List<QuestionDTO> qUser = new ArrayList<>();
//		        for(QuizEntity quizEntity: quizzes)
//		        {
//		        for(QuestionEntity qS : quizEntity.getQ())
//		        {
//		            QuestionDTO quDTO = new QuestionDTO();
//		            quDTO.setQuestionId(qS.getQuestionId());
//		            quDTO.setQuestionTitle(qS.getQuestionTitle());
//		            quDTO.setOption1(qS.getOption1());
//		            quDTO.setOption2(qS.getOption2());
//		            quDTO.setOption3(qS.getOption3());
//		            quDTO.setOption4(qS.getOption4());
//		            quDTO.setRightAnswer(qS.getRightAnswer());
//		            qUser.add(quDTO);
//		        }
//		        }
//		        return qUser;
//	    	}
//	    	
//	    
//
//	   public QuizResult calculateResult(Long quizId, List<ResponseQuiz> responses) {
//	        QuizEntity quizEntity = quizRepo.findById(quizId).orElseThrow(() -> new EntityNotFoundException("Quiz With QuizId : "+quizId+ "Not Found"));
//	        int correct=0;
//	        int wrong=0;
//	        int totalQuestion=quizEntity.getQ().size();
//	        List<String> correctAnswer=new ArrayList<>();
//	        List<String> wrongAnswer=new ArrayList<>();
//	        
//	        for(int i=0;i<totalQuestion;i++)
//	        {
//	        	QuestionEntity  q=quizEntity.getQ().get(i);
//	        	List<String> select=responses.get(i).getSelectedOption();
//	        	if(q.getRightAnswer().equals(select))
//	        	{
//	        		correct++;
//	        		correctAnswer.add(q.getRightAnswer());
//	        	}
//	        	else
//	        	{
//	        		wrong++;
//	        		wrongAnswer.add(q.getWrongAnswer());
//	        	}
//	        	
//	        }
//	        return  new QuizResult(correct,wrong,totalQuestion,correctAnswer,wrongAnswer);
//	       
//	    }
//	}
//
