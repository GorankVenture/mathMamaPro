package com.education.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.ChapterEntity;

import com.education.entity.InstituteEntity;
public interface InstituteRepo  extends JpaRepository<InstituteEntity,Long>
{
     
	List<InstituteEntity> findByItemCode(String itemCode);
	//List<ChapterEntity> findByInstituteCode(String instituteCode);
}
