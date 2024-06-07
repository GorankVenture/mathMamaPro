package com.education.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.entity.ChapterEntity;
import com.education.model.ChapterDTO;
import com.education.repository.ChapterRepo;
@Service
public class ChapterService {

	
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ChapterService.class);
	
	@Autowired
	private ChapterRepo chapterRepo;
	public ChapterDTO saveChapter(ChapterDTO chapterDTO)
	{
		try
		{
		ChapterEntity chapterEntity=new ChapterEntity();
		chapterEntity.setChapterName(chapterDTO.getChapterName());
		String chapterCode=generateChapterCode(chapterDTO.getChapterName());
		chapterEntity.setItemCode(chapterDTO.getItemCode());
		
		chapterEntity.setChapterCode(chapterCode);
		String finalCode=generateFinalCode(chapterDTO.getItemCode(),chapterCode);
		chapterEntity.setFinalCode(finalCode);
		chapterEntity.setChapterDescription(chapterDTO.getChapterDescription());
		//chapterEntity.setClassCode(chapterDTO.getClassCode());
		chapterRepo.save(chapterEntity);
		return chapterDTO;
		} catch(Exception e)
		  {
			log.error("There is an error of saving the chapter : {}" ,e.getMessage());
			return null;
		  }
		
	}
	
	
//	public String setInstituteCode(String institude) {
//		if() {}
//	}
	private String generateChapterCode(String chapterName)
	{
		StringBuilder chapterCode=new StringBuilder("CH");
		for(char c:chapterName.toCharArray())
		{
			if(Character.isDigit(c))
			{
				chapterCode.append(c);
			}
		}
		return chapterCode.toString();
		
	} 
	//public List<ChapterEntity> 
	public List<ChapterEntity> getAllChapters()
	{
		return chapterRepo.findAll();
	}
	private String  generateFinalCode(String itemCode,String chapterCode)
	{
		String finalCode=itemCode+chapterCode;
		return finalCode;
	}
}
