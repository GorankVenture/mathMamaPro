package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.ChapterPreviousEntity;

public interface ChapterPreviousRepo extends JpaRepository<ChapterPreviousEntity,Long> 
{
List<ChapterPreviousEntity> findByPreviousYearItemCode(String previousYearItemCode);
}
