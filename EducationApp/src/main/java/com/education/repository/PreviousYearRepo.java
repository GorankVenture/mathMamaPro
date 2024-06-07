package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.PreviousYearQuestions;

//import com.education.entity.PreviousYearQuestions;

public interface PreviousYearRepo extends JpaRepository <PreviousYearQuestions,Long> 
{
//List<PreviousYearQuestions> findByPreviousYearItemCode(String previousYearItemCode);
}
