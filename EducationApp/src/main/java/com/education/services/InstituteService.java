package com.education.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.education.entity.ChapterInstituteEntity;

import com.education.entity.InstituteEntity;

import com.education.model.ChapterInstituteDTO;
//import com.education.model.ChapterInstituteDTO;
import com.education.model.InstituteDTO;
import com.education.repository.ChapterInstituteRepo;

import com.education.repository.InstituteRepo;


@Service
public class InstituteService {

	
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(InstituteService.class);
	
	@Autowired
	private InstituteRepo instituteRepo;
	
	
	@Autowired
	private ChapterInstituteRepo chapterInstituteRepo;
	
	//@Autowired
	//private ChapterInstituteEntity chapterInstituteEntity;
	
	public InstituteDTO saveInstitute(InstituteDTO instituteDTO)
	{
		try
		{
			InstituteEntity instituteEntity=new InstituteEntity();
			String instituteCode=generateInstituteCode(instituteDTO.getInstituteName());
			instituteEntity.setInstituteName(instituteDTO.getInstituteName());
			instituteEntity.setItemCode(instituteDTO.getItemCode());
			instituteEntity.setInstituteCode(instituteCode);
			String instituteFinalCode=generateInstituteFinalCode(instituteDTO.getItemCode(),instituteCode);
			instituteEntity.setInstituteFinalCode(instituteFinalCode);
			instituteRepo.save(instituteEntity);
			return instituteDTO;
			
		} catch(Exception e)
		{
			e.getStackTrace();
			log.error("There is an error for saving institute details : {}",e.getMessage());
			return null;
		}
	}
	public List<InstituteEntity> getAllInstitute()
	{
		return instituteRepo.findAll();
	}
	
	
	/*public String generateInstituteCode(String instituteName)
	{
		//String iP=instituteName.toLowerCase();
		if(instituteName.contains("Alien Solutions"))
		{
			return "AS";
		}
		else if(instituteName.contains("Narayun Solutions"))
		{
			return "NS";
		}
		else if(instituteName.contains("Resouce Solutions"))
		{
			return "RS";
		}
		else if(instituteName.contains("Fit JEE Solutions"))
		{
			return "FJS";
		}
		else if(instituteName.contains("Sky Solutions"))
		{
			return "SS";
		}
		else if(instituteName.contains("Vidya Mandir Solutions"))
		{
			return "VMS";
		}
		else if(instituteName.contains("Bansal Classes Solutions"))
		{
			return "BCS";
		}
		else
		{
			return "Unknown";
		}
	}*/
	public String generateInstituteCode(String instituteName)
	{
		String words[]=instituteName.split(" ");
		StringBuilder str=new StringBuilder();
		for(String word: words)
		{
			
			if(!word.isEmpty())
			{
			str.append(Character.toUpperCase(word.charAt(0)));
			}
		}
		
		return str.toString();
	}
	/*public List<ChapterDTO> getChapterByInstituteCode(String instituteCode)
	{
		
		List<ChapterDTO>  cList=new ArrayList<>();
		//List<ChapterInstituteDTO> cList=new ArrayList<>();
		List<ChapterEntity> f=chapterRepo.findByInstituteCode(instituteCode);
		for(ChapterEntity i:f)
		{
			ChapterDTO lDTO=new ChapterDTO();
			lDTO.setChapterId(i.getChapterId());
			lDTO.setChapterName(i.getChapterName());
			lDTO.setChapterDescription(i.getChapterDescription());
			lDTO.setChapterCode(i.getChapterCode());
			lDTO.setInstituteCode(i.getInstituteCode());
			cList.add(lDTO);
		}
		return cList;
		
	}*/
	public ChapterInstituteDTO saveChapterInstitute(ChapterInstituteDTO chapterInstituteDTO)
	{
		try
		{
		ChapterInstituteEntity chapterInstituteEntity=new ChapterInstituteEntity();
		chapterInstituteEntity.setChapterInstituteName(chapterInstituteDTO.getChapterInstituteName());
		chapterInstituteEntity.setChapterDescription(chapterInstituteDTO.getChapterDescription());
		chapterInstituteEntity.setInstituteFinalCode(chapterInstituteDTO.getInstituteFinalCode());
		String chapterInstituteCode=generateChapterInstituteCode(chapterInstituteDTO.getChapterInstituteName());
		chapterInstituteEntity.setChapterInstituteCode(chapterInstituteCode);
		String chapterInstituteFinalCode=generateChapterInstituteFinalCode(chapterInstituteDTO.getInstituteFinalCode(),chapterInstituteCode);
		chapterInstituteEntity.setChapterInstituteFinalCode(chapterInstituteFinalCode);
		chapterInstituteRepo.save(chapterInstituteEntity);
		return chapterInstituteDTO;
		} catch(Exception e)
		{
			log.error("There is an error for saviing the chapter institute information : {}",e.getMessage());
			return null;
		}
	}
	public List<InstituteEntity> getInstituteByItemCode(String itemCode)
	{
		List<InstituteEntity> iE=instituteRepo.findByItemCode(itemCode);
		return iE;
	}
	public String generateInstituteFinalCode(String itemCode,String instituteCode)
	
	{
		 return itemCode+instituteCode;
	}
	private String generateChapterInstituteFinalCode(String instituteFinalCode,String chapterInstituteCode)
	{
		return instituteFinalCode+chapterInstituteCode;
	}
	public List<ChapterInstituteEntity> getChapterInstituteByInstituteFinalCode(String instituteFinalCode)
	{
		List<ChapterInstituteEntity> t=chapterInstituteRepo.findByInstituteFinalCode(instituteFinalCode);
		return t;
	}
	private String generateChapterInstituteCode(String chapterInstituteName)
	{
		StringBuilder chapterInstituteCode=new StringBuilder("CH");
		for(char c:chapterInstituteName.toCharArray())
		{
			if(Character.isDigit(c))
			{
				chapterInstituteCode.append(c);
			}
		}
		return chapterInstituteCode.toString();
		
	} 
	
}
