package com.education.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.entity.ChapterEntity;
import com.education.model.ChapterDTO;

import com.education.model.ResponseWithList;
import com.education.model.ResponseWithObject;
import com.education.services.ChapterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api")
@Tag(name="CHAPTER-API")

public class ChapterController {

	
	@Autowired
	private ChapterService chapterService;
	
	//public String 
	
	@PostMapping(value="/addChapter")
	@Operation(summary="to add Chapter",description="this api is used to add the chapter")
	public ResponseEntity<?> addChapter(@RequestBody ChapterDTO chapterDTO)
	{
		ChapterDTO c=chapterService.saveChapter(chapterDTO);
		if(c!=null)
		{
			return  new ResponseWithObject().generateResponse("Chapter are saved", HttpStatus.OK, "200",c);
		}
		else
		{
			return new  ResponseWithObject().generateResponse("No chapter are saved", HttpStatus.BAD_REQUEST, "400","");
		}
	}
	@PutMapping(value="/updateChapterwise")
	@Operation(summary="to update the chapterwise",description="this api is basically to update the chapterwise")
	public ResponseEntity<Object> updateChapter(@RequestBody ChapterDTO chapterDTO)
	{
		if(chapterDTO.getChapterId()==null)
		{
			new ResponseWithObject().generateResponse("chapterId is missing", HttpStatus.BAD_GATEWAY, "512", chapterDTO);
		}
		 ChapterDTO p=chapterService.saveChapter(chapterDTO);
		 if(p!=null)
		 {
			 return new  ResponseWithObject().generateResponse("Chapter are updated successfully", HttpStatus.OK, "200", p);
		 }
		 else
		 {
			 return new ResponseWithObject().generateResponse("Chapter are not updated successfully", HttpStatus.BAD_REQUEST, "400", "");
		 }
		
	}
	/*@GetMapping(value="/findChapter")
	public ResponseEntity<?> getChapter()
	{
		List<ChapterEntity> ce=chapterService.getAllChapter();
		return new ResponseWithList().generateResponse("Chapters are get displayed",HttpStatus.OK,"200",ce);
	} */
	
	@GetMapping(value="/findChapter")
	@Operation(summary="to find the chapter",description="this api is used to find the chapter")
	public ResponseEntity<?> getChapter()
	{
		List<ChapterEntity> ce=chapterService.getAllChapters();
		return new ResponseWithList().generateResponse("provide", HttpStatus.OK, "200", ce);
	}
}
