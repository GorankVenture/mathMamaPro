package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.PreviousQuestionPaperEntity;

public interface PreviousYearQuestionsPaperRepo  extends JpaRepository<PreviousQuestionPaperEntity,Long>
{
	
	List<PreviousQuestionPaperEntity> findByPreviousYearItemCode(String previousYearItemCode);

}
