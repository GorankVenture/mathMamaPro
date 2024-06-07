package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.ChapterRevisionEntity;

public interface ChapterRevisionRepo  extends JpaRepository<ChapterRevisionEntity,Long>{

	
	List<ChapterRevisionEntity> findRevisionChapterByCourseSubCode(String courseSubCode);
}
