package com.education.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.entity.ChapterInstituteEntity;
import com.education.entity.InstituteEntity;

import com.education.model.ChapterInstituteDTO;
import com.education.model.InstituteDTO;

import com.education.model.MaterialItemDTO;
import com.education.model.ResponseWithList;
import com.education.model.ResponseWithObject;
import com.education.services.InstituteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("api")
@Tag(name="MaterialSolutions-API")
public class InstituteController {

	@Autowired
	private InstituteService instituteService;
	
	
	@PostMapping(value="/addInstitute")
	@Operation(summary="to add institute details",description="this api is used to add  the institute details")
	public ResponseEntity<Object>  addInstitute(@RequestBody InstituteDTO instituteDTO)
	{
		
		InstituteDTO response=instituteService.saveInstitute(instituteDTO);
		if(response!=null)
		{
			return new ResponseWithObject().generateResponse("Institute are saved",HttpStatus.OK,"200",response);
		}
		else
		{
			return new ResponseWithObject().generateResponse("Institute are  not saved",HttpStatus.BAD_REQUEST,"400","");
		}
	}
	@PutMapping(value="/updateInstitute")
	@Operation(summary="to update institute details",description="this api is used to update the institute details")
	public ResponseEntity<Object>  updateInstitute(@RequestBody InstituteDTO instituteDTO)
	{
		
		InstituteDTO response=instituteService.saveInstitute(instituteDTO);
		if(response!=null)
		{
			return new ResponseWithObject().generateResponse("Institute are saved",HttpStatus.OK,"200",response);
		}
		else
		{
			return new ResponseWithObject().generateResponse("Institute are  not saved",HttpStatus.BAD_REQUEST,"400","");
		}
	}
	@GetMapping(value="/findAllInstitute")
	@Operation(summary="to find all the institute",description="this api is used to find out all the institutes")
	public ResponseEntity<?> findAllInstitute()
	{
		List<InstituteEntity> tList=instituteService.getAllInstitute();
		return new ResponseWithList().generateResponse("Institute list get displayed",HttpStatus.OK,"200",tList);
	}
	@GetMapping(value="/findInstituteByItemCode")
	@Operation(summary="to find all the institute by item code",description="this api is find out the institute by item code")
	public ResponseEntity<?> findInstituteByItemCode(@RequestParam ("itemCode") String itemCode)
	{
		List<InstituteEntity> p=instituteService.getInstituteByItemCode(itemCode);
		return new ResponseWithList().generateResponse("All institute are displayed by item code",HttpStatus.OK,"200", p);
	}
	@PostMapping(value="/addChapterInstitute")
	@Operation(summary="to add chapter institute",description="this api is used to add chapter Institute ")
	 public ResponseEntity<?> addChapterInstitute(@RequestBody ChapterInstituteDTO chapterInstituteDTO)
	 {
		//String res=instituteService.saveChapterInstitute(chapterInstituteDTO);
		ChapterInstituteDTO resDTO=instituteService.saveChapterInstitute(chapterInstituteDTO);
		if(resDTO!=null)
		{
			return new ResponseWithObject().generateResponse("Chapter  are saved according to institute list", HttpStatus.OK, "200", resDTO);
		}
		else
		{
			return new ResponseWithObject().generateResponse("Chapter are not saved to institute list", HttpStatus.BAD_REQUEST,"400","");
		}
	 }
	@PutMapping(value="/updateChapterInstitute")
	@Operation(summary="to update chapter institute",description="this api is used to update chapter Institute ")
	 public ResponseEntity<Object> updateChapterInstitute(@RequestBody ChapterInstituteDTO chapterInstituteDTO)
	 {
		//String res=instituteService.saveChapterInstitute(chapterInstituteDTO);
		ChapterInstituteDTO resDTO=instituteService.saveChapterInstitute(chapterInstituteDTO);
		if(resDTO!=null)
		{
			return new ResponseWithObject().generateResponse("Chapter  are saved according to institute list", HttpStatus.OK, "200", resDTO);
		}
		else
		{
			return new ResponseWithObject().generateResponse("Chapter are not saved to institute list", HttpStatus.BAD_REQUEST,"400","");
		}
	 }
//	@GetMapping(value="/getChapterInstitute")
//	@Operation(summary="to get chapter isntitute",description="this api is used to get the chapter institute")
//	public  ResponseEntity<?> getChapterInstitute()
//	{
//		List<ChapterInstituteEntity> v=instituteService.getAllChapterInstitute();
//		return new ResponseWithList().generateResponse("Chapter institute are get listed get displayed", HttpStatus.OK,"200", v);
//	}
	@GetMapping(value="/getInstituteFinalCode")
	@Operation(summary="to get institute final code",description="this api is used to get institute final code")
	public ResponseEntity<Object> getInstituteFinalCode(@RequestParam ("instituteFinalCode") String instituteFinalCode)
	{
		List<ChapterInstituteEntity> rList=instituteService.getChapterInstituteByInstituteFinalCode(instituteFinalCode);
		return new ResponseWithList().generateResponse("Chapters are get displayed according to institute final code", HttpStatus.OK, "200", rList);
	}

	
	@GetMapping(value="/getAllChapterInstituteFinalCode")
	@Operation(summary="to get all the chapter institute final code",description="this api is to get all the chapters institute final code")
	public List<MaterialItemDTO> getAllMaterialItems(@RequestParam ("chapterInstituteFinalCode") String chapterInstituteFinalCode)
	{
		
		List<MaterialItemDTO> responseData = new ArrayList<>();
		MaterialItemDTO textSolutionsObj = new MaterialItemDTO("Text Solutions",chapterInstituteFinalCode+"TEXTSOLUTIONS");
	 	MaterialItemDTO videoSolutionsObj = new MaterialItemDTO("Video Solutions",chapterInstituteFinalCode+"VIDEOSOLUTIONS");
		responseData.add(textSolutionsObj);
		responseData.add(videoSolutionsObj);
		
		return responseData;
	}
	
}

