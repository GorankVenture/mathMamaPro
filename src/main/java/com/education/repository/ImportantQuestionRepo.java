package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.ImportantQuestionEntity;

public interface ImportantQuestionRepo extends JpaRepository <ImportantQuestionEntity,Long> 
{

	
	List<ImportantQuestionEntity> findByItemCode(String itemCode);
	
}

