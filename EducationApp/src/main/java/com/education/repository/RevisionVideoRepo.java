package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.RevisionVideoEntity;

public interface RevisionVideoRepo extends JpaRepository<RevisionVideoEntity,Long> {

	
	
	List<RevisionVideoEntity> findByChapterRevisionFinalCode(String chapterRevisionFinalCode);
}
