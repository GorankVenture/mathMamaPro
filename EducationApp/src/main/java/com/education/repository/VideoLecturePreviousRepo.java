package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.VideoLecturePrevious;

public interface VideoLecturePreviousRepo  extends JpaRepository <VideoLecturePrevious,Long>
{
  List<VideoLecturePrevious> findByFinalPreviousCode(String finalPreviousCode);
  List<VideoLecturePrevious> findByPreviousYearItemCode(String previousYearItemCode);
}
