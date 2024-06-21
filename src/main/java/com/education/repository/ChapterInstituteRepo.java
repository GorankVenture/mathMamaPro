package com.education.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.ChapterInstituteEntity;
public interface ChapterInstituteRepo  extends JpaRepository<ChapterInstituteEntity,Long>
{
  List<ChapterInstituteEntity> findByInstituteFinalCode(String instituteFinalCode);
}
