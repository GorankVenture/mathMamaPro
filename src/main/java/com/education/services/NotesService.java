package com.education.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.education.entity.Notes;
import com.education.model.NotesDTO;
import com.education.repository.NotesRepo;
@Service
public class NotesService 
{
   @Autowired
   private  NotesRepo notesRepo;

	
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NotesService.class);
	public NotesDTO saveNote(NotesDTO notesDTO)
	{
		try
		{
		Notes n=notesRepo.findById(notesDTO.getNoteId()).get();
		n.setNotesId(notesDTO.getNoteId());
		n.setNoteNames(notesDTO.getNotesName());
		n.setItemCode(notesDTO.getItemCode());
		byte[] noteBytes=notesDTO.getNotePdf().getBytes();
		n.setNotesPdf(noteBytes);
		String baseNotePdf=Base64.getEncoder().encodeToString(notesDTO.getNotePdf().getBytes());
		notesDTO.setNotePdf(null);
		notesDTO.setBaseNotePdf(baseNotePdf);
		notesRepo.save(n);
		return notesDTO;
		}catch(Exception e)
		{
			log.error("There is error for note {} ",e.getMessage());
			return null;
		}
	}
	public List<Notes> getAllNote()
	{
		return notesRepo.findAll();
	}
	
	
	
//	public List<NotesDTO> getAllNotesByCourseSubCode(String courseSubCode)
//	{
//		List<NotesDTO> nList=new ArrayList<>();
//		List<Notes> m=notesRepo.findByCourseSubCode(courseSubCode);
//		for(Notes n: m)
//		{
//			NotesDTO  notesDTO=new NotesDTO();
//			notesDTO.setNoteId(n.getNotesId());
//			notesDTO.setNotesName(n.getNoteNames());
//			notesDTO.setBaseNotePdf(new String(n.getNotesPdf()));
//			nList.add(notesDTO);
//		}
//		return nList;
//	}
	public List<NotesDTO> getAllNotesByItemCode(String itemCode)
	{
		List<NotesDTO> nL=new ArrayList<>();
		List<Notes> m=notesRepo.findByItemCode(itemCode);
		for(Notes n: m)
		{
			NotesDTO  savedDTO=new NotesDTO();
			savedDTO.setNoteId(n.getNotesId());
			savedDTO.setNotesName(n.getNoteNames());
			savedDTO.setItemCode(itemCode);
			//savedDTO.setPaid(n.isPaid());
			savedDTO.setBaseNotePdf(new String(n.getNotesPdf()));
			nL.add(savedDTO);
		}
		return nL;
	}
	
	
	public byte[] getNotesById(Long notesId)
	{
		Optional<Notes>  nOptional=notesRepo.findById(notesId);
		Notes n=nOptional.get();
		//byte[] decodedPdf=Base64.getDecoder().decode(n.getNotesPdf());
		return n.getNotesPdf();
	}
	
	/*public byte[] getNoteById(Long notesId,String transactionId)
	{
		Optional<Notes> nOptional=notesRepo.findById(notesId,transactionId);
		Notes no=nOptional.get();
		
	}*/
	
	
		
} 

