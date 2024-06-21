package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.education.entity.QuestionEntity;

public interface QuestionRepo  extends JpaRepository <QuestionEntity,Long> 
{
  
   List<QuestionEntity>  findByCourseSubCode(String courseSubCode);
 
}
