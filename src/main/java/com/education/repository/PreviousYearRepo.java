package com.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.education.entity.PreviousYearQuestions;
public interface PreviousYearRepo extends JpaRepository <PreviousYearQuestions,Long> 
{

}
