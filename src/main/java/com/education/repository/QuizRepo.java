//package com.education.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
////import org.springframework.data.jpa.repository.Query;
//
//import com.education.entity.QuizEntity;
//
//public interface QuizRepo extends JpaRepository <QuizEntity,Long> 
//{
//	
////@Query("SELECT q FROM QuizEntity q LEFT JOIN FETCH q.q WHERE q.courseSubCode = :courseSubCode")
////Optional<QuizEntity>  findByCourseSubCode(String courseSubCode);
//List<QuizEntity> findByCourseSubCode(String courseSubCode);
//}
