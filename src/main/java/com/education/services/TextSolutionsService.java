package com.education.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.repository.TextSolutionsRepo;
import com.education.entity.TextSolutions;
import com.education.model.TextSolutionsDTO;


@Service
public class TextSolutionsService {
    
	
	
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TextSolutionsService.class);
	
	
	@Autowired
	private TextSolutionsRepo textSolutionRepo;
	
	public TextSolutionsDTO saveTextSolution(TextSolutionsDTO tDTO)
	{
		
	   try
	   {
		TextSolutions t=textSolutionRepo.findById(tDTO.getTextSolutionsId()).get();
		t.setTextSolutionsName(tDTO.getTextSolutionsName());
		t.setMaterialItemCode(tDTO.getMaterialItemCode());
		//t.setChapterInstituteFinalCode(tDTO.getChapterInstituteFinalCode());
		byte[] tBytes=tDTO.getTextFile().getBytes();
		String baseT=Base64.getEncoder().encodeToString(tDTO.getTextFile().getBytes());
		  t.setTextSolutionsPdf(tBytes);
		  textSolutionRepo.save(t);
		  
		 tDTO.setBaseText(baseT);
		 tDTO.setTextFile(null);
	 return tDTO;
	   } catch(Exception e)
	    {
	      e.printStackTrace();
		   log.error("There is an error for saving the text solution : {}",e.getMessage());
		   return null;
	    }
	}
	public List<TextSolutionsDTO> getTextSolutionsByMaterialItemCode(String materialItemCode)
	{
		List<TextSolutionsDTO> fList=new ArrayList<>();
		List<TextSolutions> v=textSolutionRepo.findByMaterialItemCode(materialItemCode);
		for(TextSolutions t:v)
		{
			TextSolutionsDTO response=new TextSolutionsDTO();
			response.setTextSolutionsId(t.getTextSolutionsId());
			//response.setChapterInstituteFinalCode(chapterInstituteFinalCode);
			response.setTextSolutionsName(t.getTextSolutionsName());
			response.setMaterialItemCode(materialItemCode);
			response.setBaseText(new String(t.getTextSolutionsPdf()));
		    fList.add(response);
		}
		return fList;
	}
	public byte[] getTextSolutionsById(Long textSolutionsId)
	{
		Optional<TextSolutions> textOptional=textSolutionRepo.findById(textSolutionsId);
		TextSolutions textData=textOptional.get();
		return textData.getTextSolutionsPdf();
	}
}
	
	
	
