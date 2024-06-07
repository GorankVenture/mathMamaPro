package com.education.services;




import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.entity.ImportantQuestionEntity;
import com.education.entity.Notes;
import com.education.model.ImportantDTO;
import com.education.repository.ImportantQuestionRepo;


@Service
public class ImportantQuestionService {

	private org.slf4j.Logger log=org.slf4j.LoggerFactory.getLogger(ImportantQuestionService.class);
	
	@Autowired
	private ImportantQuestionRepo importantQuestionRepo;
	public String saveImportantQuestion(ImportantDTO importantDTO)
	{
		try
		{
		ImportantQuestionEntity importantQuestion=new ImportantQuestionEntity();
		importantQuestion.setImportantQuestionId(importantDTO.getImportantQuestionId());
		importantQuestion.setName(importantDTO.getName());
		importantQuestion.setItemCode(importantDTO.getItemCode());
		byte[] fileBytes=importantDTO.getImportantFile().getBytes();
		String baseImportant=Base64.getEncoder().encodeToString(importantDTO.getImportantFile().getBytes());
		importantQuestion.setImportantQuestions(fileBytes);
		importantDTO.setImportantFile(null);
		importantDTO.setBaseImportant(baseImportant);
		//boolean isPaid=importantQuestion.getName().equalsIgnoreCase("important 1");
		//importantQuestion.setPaid(isPaid);
		importantQuestionRepo.save(importantQuestion);
		return "Success";
		} catch(Exception e)
		{
			e.printStackTrace();
			log.error("There is an error for getting an important question : {}",e.getMessage());
			return "Error";
		}
		}
	
	
      public List<ImportantQuestionEntity> getAllImportantQuestion()
       {
	  return  importantQuestionRepo.findAll();
       }
  
     
     public List<ImportantDTO> getAllImportantQuestionByItemCode(String itemCode)
     {
    	 List<ImportantDTO> iList=new ArrayList<>();
    	 List<ImportantQuestionEntity> importantFromRepo=importantQuestionRepo.findByItemCode(itemCode);
    	 for(ImportantQuestionEntity  i:importantFromRepo)
    	 {
    		 ImportantDTO importantDTO=new ImportantDTO();
    		 importantDTO.setImportantQuestionId(i.getImportantQuestionId());
    		// importantDTO.setId(i.getId());
    		 importantDTO.setName(i.getName());
    		 importantDTO.setItemCode(itemCode);
    		 importantDTO.setBaseImportant(new String(i.getImportantQuestions()));
    		 iList.add(importantDTO);
    	 }
    	 return iList;
     }
     public byte[] getImportantQuestionById(Long importantQuestionId)
 	{
 		Optional<ImportantQuestionEntity>  iOptional=importantQuestionRepo.findById(importantQuestionId);
 		ImportantQuestionEntity importantData=iOptional.get();
 		//byte[] decodedPdf=Base64.getDecoder().decode(n.getNotesPdf());
 		return importantData.getImportantQuestions();
 	}
}
    

