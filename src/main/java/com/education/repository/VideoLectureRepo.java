package com.education.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.VideoLecture;

public interface VideoLectureRepo extends JpaRepository <VideoLecture,Long>
{
	List<VideoLecture>  findByFinalCode(String finalCode);
}