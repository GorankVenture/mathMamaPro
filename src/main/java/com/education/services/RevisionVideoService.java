package com.education.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.entity.ChapterEntity;
import com.education.entity.ChapterRevisionEntity;
import com.education.entity.RevisionVideoEntity;
import com.education.entity.VideoLecture;
import com.education.model.ChapterDTO;
import com.education.model.ChapterRevisionDTO;
import com.education.model.RevisionVideoDTO;
import com.education.model.VideoLectureDTO;
import com.education.repository.ChapterRepo;
import com.education.repository.ChapterRevisionRepo;
import com.education.repository.RevisionVideoRepo;



@Service
public class RevisionVideoService {

	//private org.slf4j.LoggerFactory log=org.slf4j.LoggerFactory.getLogger(RevisionVideoService.class);
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RevisionVideoService.class);
	
	
	//@Autowired
	//private ChapterRepo chapterRepo;
	
	
	@Autowired
	private RevisionVideoRepo revisionVideoRepo;
	
	@Autowired
	private ChapterRevisionRepo chapterRevisionRepo;
	public String saveRevisionChapter(ChapterRevisionDTO chapterRevisionDTO)
	{
		try
		{
			
			ChapterRevisionEntity chapterRevisionEntity=chapterRevisionRepo.findById(chapterRevisionDTO.getChapterRevisionId()).get();
			chapterRevisionEntity.setChapterRevisionName(chapterRevisionDTO.getChapterRevisionName());
			chapterRevisionEntity.setCourseSubCode(chapterRevisionDTO.getCourseSubCode());
			String chapterRevisionCode=generateChapterRevisionCode(chapterRevisionDTO.getChapterRevisionName());
			chapterRevisionEntity.setChapterRevisionCode(chapterRevisionCode);
			String chapterRevisionFinalCode=generateRevisionFinalCode(chapterRevisionDTO.getCourseSubCode(),chapterRevisionCode);
			chapterRevisionEntity.setChapterRevisionFinalCode(chapterRevisionFinalCode);
			chapterRevisionRepo.save(chapterRevisionEntity);
			return "Success";
			
		} catch(Exception e)
		{
			e.printStackTrace();
			log.error("There is error for revision for video : {}",e.getMessage());
			return "Error";
		}
	}
	private String generateChapterRevisionCode(String chapterRevisionName)
	{
		StringBuilder chapterRevisionCode=new StringBuilder("CH");
		for(char c:chapterRevisionName.toCharArray())
		{
			if(Character.isDigit(c))
			{
				chapterRevisionCode.append(c);
			}
		}
		return chapterRevisionCode.toString();
		
	} 
	private String generateRevisionFinalCode(String courseSubCode,String chapterRevisionCode)
	{
		return courseSubCode+chapterRevisionCode;
	}
	public List<ChapterRevisionEntity> getAllRevisionChapterByCourseSubCode(String courseSubCode)
	
	{
		List<ChapterRevisionEntity> chapterRevisionList=chapterRevisionRepo.findRevisionChapterByCourseSubCode(courseSubCode);
		return chapterRevisionList;
	}
	public String saveRevisionVideoLecture(RevisionVideoDTO revisionVideoDTO)
	{
		try
		{
		RevisionVideoEntity revisionVideoEntity=revisionVideoRepo.findById(revisionVideoDTO.getVideoRevisionId()).get();
		revisionVideoEntity.setVideoRevisionName(revisionVideoDTO.getVideoRevisionName());
		revisionVideoEntity.setVideoRevisionDescription(revisionVideoDTO.getVideoRevisionDescription());
		revisionVideoEntity.setChapterRevisionFinalCode(revisionVideoDTO.getChapterRevisionFinalCode());
		revisionVideoEntity.setVideoRevisionData(revisionVideoDTO.getFileVideo().getBytes());
		revisionVideoRepo.save(revisionVideoEntity);
		return "Success";
		} catch(Exception e)
		{
			log.error("There is error for saving the video name : {}" ,e.getMessage());
			return "Error";
		}
	
		
	}
	
	
	public List<RevisionVideoEntity> getRevisionVideoByChapterRevisionFinalCode(String chapterRevisionFinalCode)
	{
		List<RevisionVideoEntity> revisionData=revisionVideoRepo.findByChapterRevisionFinalCode(chapterRevisionFinalCode);
		return revisionData;
	}
	
	public byte[] getRevisionVideoByVideoRevisionId(Long videoRevisionId)
	{
		Optional<RevisionVideoEntity> videoRevisionOptional=revisionVideoRepo.findById(videoRevisionId);
		RevisionVideoEntity videoRevisionData=videoRevisionOptional.get();
		return videoRevisionData.getVideoRevisionData();
	}

}
