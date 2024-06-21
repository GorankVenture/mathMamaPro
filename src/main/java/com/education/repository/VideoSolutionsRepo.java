package com.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.VideoSolutionsEntity;
import java.util.List;

public interface VideoSolutionsRepo extends JpaRepository <VideoSolutionsEntity,Long> {

	
	//List<VideoSolutionsEntity> findByChapterCode(String chapterCode);
	List<VideoSolutionsEntity> findByMaterialItemCode(String materialItemCode);
}
