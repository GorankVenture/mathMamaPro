package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.CourseEntity;

public interface CourseRepo extends JpaRepository <CourseEntity,Long>{

	
	
	List<CourseEntity> findByCourseCode(String courseCode);
}
