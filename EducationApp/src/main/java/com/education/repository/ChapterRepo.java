package com.education.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.ChapterEntity;
public interface ChapterRepo  extends JpaRepository <ChapterEntity,Long>
{

	
	List<ChapterEntity> findByItemCode(String itemCode);
}
