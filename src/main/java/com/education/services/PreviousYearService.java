package com.education.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.entity.ChapterPreviousEntity;

import com.education.entity.PreviousQuestionPaperEntity;
import com.education.entity.PreviousYearQuestions;

import com.education.entity.VideoLecturePrevious;
import com.education.model.ChapterPreviousDTO;

import com.education.model.PreviousQuestionPaperDTO;
import com.education.model.PreviousYearQuestionsDTO;
import com.education.model.VideoLecturePreviousDTO;
import com.education.repository.ChapterPreviousRepo;
import com.education.repository.PreviousYearQuestionsPaperRepo;
import com.education.repository.PreviousYearRepo;
import com.education.repository.VideoLecturePreviousRepo;


@Service
public class PreviousYearService {
	
	
	private org.slf4j.Logger log=org.slf4j.LoggerFactory.getLogger(PreviousYearService.class);
    @Autowired
	private PreviousYearRepo previousYearRepo;
    
    @Autowired
    private PreviousYearQuestionsPaperRepo previousYearQuestionsPaperRepo;
    @Autowired
    private VideoLecturePreviousRepo videoLecturePreviousRepo;
    
    @Autowired
    private ChapterPreviousRepo chapterPreviousRepo;
	
	public String savePreviousYearPaper(PreviousYearQuestionsDTO previousYearQuestionsDTO)
	{
		try
		{
			PreviousYearQuestions previousData=new PreviousYearQuestions();
			previousData.setPreviousYearName(previousYearQuestionsDTO.getPreviousYearName());
			String previousYearCode=generatePreviousYearCode(previousYearQuestionsDTO.getPreviousYearName());
			previousData.setPreviousYearCode(previousYearCode);
			previousYearRepo.save(previousData);
			return "Success";
		} catch(Exception e)
		{
			log.error("There is an error for saving the previous year question paper : {}",e.getMessage());
			return "Error";
		}
	}
	
	private String generatePreviousYearCode(String previousYearName)
	{
		if(previousYearName.contains("Mains"))
		{
			return "PM";
		}
		else if(previousYearName.contains("Advance"))
		{
			return "PA";
		}
		else
		{
			return null;
		}
	}
	
	public List<PreviousYearQuestions> getAllPreviousYear()
	{
		return previousYearRepo.findAll();
	}
	
	public String saveChapterPrevious(ChapterPreviousDTO chapterPreviousDTO)
	{
		try
		{
		ChapterPreviousEntity chapterPreviousEntity=new ChapterPreviousEntity();
		chapterPreviousEntity.setChapterPreviousName(chapterPreviousDTO.getChapterPreviousName());
		chapterPreviousEntity.setPreviousYearItemCode(chapterPreviousDTO.getPreviousYearItemCode());
		String chapterPreviousCode=generateChapterPreviousCode(chapterPreviousDTO.getChapterPreviousName());
		chapterPreviousEntity.setChapterPreviousCode(chapterPreviousCode);
		String finalPreviousCode=generateFinalPreviousCode(chapterPreviousDTO.getPreviousYearItemCode(),chapterPreviousCode);
		chapterPreviousEntity.setFinalPreviousCode(finalPreviousCode);
		chapterPreviousRepo.save(chapterPreviousEntity);
		return "Success";
		} catch(Exception e)
		{
			log.error("There is an error for  saving the chapter previous : {}",e.getMessage());
			return "Error";
		}
		
	}
	 
	public List<ChapterPreviousEntity> findAllChapterPrevious()
	{
		return chapterPreviousRepo.findAll();
	}
	private String generateChapterPreviousCode(String chapterPreviousName)
	{
		StringBuilder chapterPreviousCode=new StringBuilder("CH");
		for(char c:chapterPreviousName.toCharArray())
		{
			if(Character.isDigit(c))
			{
				chapterPreviousCode.append(c);
			}
		}
		return chapterPreviousCode.toString();
		
	}
	public String saveVideoLecturePrevious(VideoLecturePreviousDTO videoLecturePreviousDTO)
	{
		try
		{
			VideoLecturePrevious videoData=new VideoLecturePrevious();
			videoData.setVideoLecturePreviousName(videoLecturePreviousDTO.getVideoLecturePreviousName());
			videoData.setPreviousYearItemCode(videoLecturePreviousDTO.getPreviousYearItemCode());
			videoData.setChapterPreviousCode(videoLecturePreviousDTO.getChapterPreviousCode());
			// String finalPreviousCode=generateFinalPreviousCode(videoLecturePreviousDTO.getPreviousYearItemCode(),videoLecturePreviousDTO.getChapterPreviousCode());
			 videoData.setFinalPreviousCode(videoLecturePreviousDTO.getFinalPreviousCode());
			videoData.setVideoPreviousData(videoLecturePreviousDTO.getVideoFile().getBytes());
			videoLecturePreviousRepo.save(videoData);
			return "Success";
		} catch(Exception e)
		{
			log.error("There is an error for saving the video lecture previous : {}",e.getMessage());
			return "Error";
		}
	}
	public  byte[]  getVideoByVideoLecturePreviousId(Long videoLecturePreviousId)
	{
		
		Optional<VideoLecturePrevious> videoPreviousOptional=videoLecturePreviousRepo.findById(videoLecturePreviousId);
		VideoLecturePrevious videoPrevious=videoPreviousOptional.get();
		return videoPrevious.getVideoPreviousData();
	}
	public List<VideoLecturePrevious> getVideosByPreviousChapter(String finalPreviousCode)
	{
		List<VideoLecturePrevious> videoPreviousData =  videoLecturePreviousRepo.findByFinalPreviousCode(finalPreviousCode);
		
		return videoPreviousData;
		
		
	}
	private String generateFinalPreviousCode(String previousYearItemCode,String chapterPreviousCode)
	{
		String finalPreviousCode=previousYearItemCode+chapterPreviousCode;
		return finalPreviousCode;
	}
	
	public String savePreviousQuestionPaper(PreviousQuestionPaperDTO previousQuestionPaperDTO)
	{
		try
		{
			PreviousQuestionPaperEntity previousEntity=new PreviousQuestionPaperEntity();
			previousEntity.setPreviousQuestionPaperId(previousQuestionPaperDTO.getPreviousQuestionId());
			previousEntity.setPreviousQuestionPaperName(previousQuestionPaperDTO.getPreviousQuestionsPaperName());
			previousEntity.setPreviousYearItemCode(previousQuestionPaperDTO.getPreviousYearItemCode());
			byte[] previousBytes=previousQuestionPaperDTO.getPreviousQuestionFile().getBytes();
			previousEntity.setPreviousQuestionPaperData(previousBytes);
			String basePrevious=Base64.getEncoder().encodeToString(previousQuestionPaperDTO.getPreviousQuestionFile().getBytes());
			previousQuestionPaperDTO.setPreviousQuestionFile(null);
		   previousQuestionPaperDTO.setBasePrevious(basePrevious);
		   previousYearQuestionsPaperRepo.save(previousEntity);
		   return "Success"; 
		} catch(Exception e)
		{
			log.error("There is an error for saving the previous year question paper : {}",e.getMessage());
			return "Error";
		}
		
	}
	public List<PreviousQuestionPaperDTO> getAllPreviousYearQuestionPaperByPreviousYearItemCode(String previousYearItemCode)
	{
		List<PreviousQuestionPaperDTO> questionData=new ArrayList<>();
		List<PreviousQuestionPaperEntity> questionPaperData=previousYearQuestionsPaperRepo.findByPreviousYearItemCode(previousYearItemCode);
		for(PreviousQuestionPaperEntity p: questionPaperData)
		{
			PreviousQuestionPaperDTO  savedPreviousDTO=new PreviousQuestionPaperDTO();
			savedPreviousDTO.setPreviousQuestionId(p.getPreviousQuestionPaperId());
			savedPreviousDTO.setPreviousQuestionsPaperName(p.getPreviousQuestionPaperName());
			savedPreviousDTO.setPreviousYearItemCode(previousYearItemCode);
			savedPreviousDTO.setBasePrevious(new String(p.getPreviousQuestionPaperData()));
		      questionData.add(savedPreviousDTO);
		}
		return questionData;
	}
	 public List<ChapterPreviousEntity> getPreviousChapterByPreviousYearItemCode(String previousYearItemCode)
	 {
		 List<ChapterPreviousEntity> videoList=chapterPreviousRepo.findByPreviousYearItemCode(previousYearItemCode);
		 return videoList;
	 }
	
	
	public byte[] getPreviousQuestionPaperById(Long previousQuestionPaperId)
	{
		Optional<PreviousQuestionPaperEntity>  previousOptional=previousYearQuestionsPaperRepo.findById(previousQuestionPaperId);
		PreviousQuestionPaperEntity previous=previousOptional.get();
		
		return previous.getPreviousQuestionPaperData();
	}
}
