package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.Notes;

public interface NotesRepo extends JpaRepository <Notes,Long> 
{
//List<Notes> findByCourseCode(String courseCode);
//List<Notes> findByCourseSubCode(String courseSubCode);
	List<Notes> findByItemCode(String itemCode);
//Notes findByNotesId(Long notesId);
}
