package com.education.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.education.entity.VideoLecture;
import com.education.entity.VideoSolutionsEntity;
import com.education.model.VideoSolutionsDTO;
import com.education.repository.VideoSolutionsRepo;


@Service
public class VideoSolutionsService {

	
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(VideoSolutionsService.class);
	@Autowired
	private VideoSolutionsRepo videoSolutionsRepo;
	
	
	public String saveVideoSolutions(VideoSolutionsDTO videoSolutionsDTO)
	{
		try
		{
			VideoSolutionsEntity videoSolutions=videoSolutionsRepo.findById(videoSolutionsDTO.getVideoSolutionsId()).get();
			videoSolutions.setVideoSolutionsName(videoSolutionsDTO.getVideoSolutionsName());
			videoSolutions.setMaterialItemCode(videoSolutionsDTO.getMaterialItemCode());
			videoSolutions.setVideoSolutionsData(videoSolutionsDTO.getVideoSolutionsFile().getBytes());
			videoSolutionsRepo.save(videoSolutions);
			return "Success";
		} catch(Exception e)
		{
			log.error("There is an error  for post the video solutions : {}",e.getMessage());
			return "Error";
		}
	} 
	
    public  byte[]  getVideoById(Long videoSolutionsId)
   {
	
	Optional<VideoSolutionsEntity> videoOptional=videoSolutionsRepo.findById(videoSolutionsId);
	VideoSolutionsEntity v=videoOptional.get();
	return v.getVideoSolutionsData();
   }
    
    public List<VideoSolutionsDTO>  getVideoSolutionsByMaterialItemCode(String materialItemCode)
    {
    	List<VideoSolutionsDTO> vS=new ArrayList<>();
    	List<VideoSolutionsEntity> videoList=videoSolutionsRepo.findByMaterialItemCode(materialItemCode);
    	for(VideoSolutionsEntity v:videoList)
    	{
    		VideoSolutionsDTO vDTO=new VideoSolutionsDTO();
    		vDTO.setVideoSolutionsId(v.getVideoSolutionsId());
    		vDTO.setVideoSolutionsName(v.getVideoSolutionsName());
    		vDTO.setMaterialItemCode(materialItemCode);
    	   vS.add(vDTO);    	}
    	return vS;
    }
}
