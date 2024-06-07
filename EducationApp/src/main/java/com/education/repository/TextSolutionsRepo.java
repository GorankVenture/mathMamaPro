package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.TextSolutions;

public interface TextSolutionsRepo extends JpaRepository <TextSolutions,Long> 
{
//List<TextSolutions> findByChapterCode(String chapterCode);
List<TextSolutions> findByMaterialItemCode(String materialItemCode);
}
