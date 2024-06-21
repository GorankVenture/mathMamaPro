package com.education.services;
//import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.ModelAttribute;

import com.education.entity.ChapterEntity;
import com.education.entity.VideoLecture;
import com.education.entity.VideoLecturess;
import com.education.model.ChapterDTO;
import com.education.model.VideoLectureDTO;
import com.education.repository.ChapterRepo;
import com.education.repository.VideoLectureRepo;
import com.education.repository.VideoLecturessRepo;

@Service
public class VideoLectureService
{
	
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(VideoLectureService.class);
	
	@Autowired
	private VideoLectureRepo videoLectureRepo;
	
	@Autowired
	private ChapterRepo chapterRepo;
	
	
	@Autowired
	private VideoLecturessRepo videoLecturessRepo;
	
	public String saveVideoLecture(VideoLectureDTO videoLectureDTO)
	{
		try
		{
			
//			byte[] videoB=videoLectureDTO.getFile().getBytes();
//			if(videoB.length>=500*1024*1024)
//			{
//				File fileFolder=new File(new ClassPathResource(".").getFile.getPath()+"/Videos");
//				if(!fileFolder.exists())
//				{
//					fileFolder.mkdir();
//					fileFolder.se
//				}
//				
//			}
		VideoLecture videoLecture=videoLectureRepo.findById(videoLectureDTO.videoId).get();
		videoLecture.setVideoName(videoLectureDTO.getFile().getOriginalFilename());
		videoLecture.setTitle(videoLectureDTO.getTitle());
		videoLecture.setDescription(videoLectureDTO.getDescription());
		videoLecture.setItemCode(videoLectureDTO.getItemCode());
		videoLecture.setChapterCode(videoLectureDTO.getChapterCode());
		videoLecture.setFinalCode(videoLectureDTO.getFinalCode());
		VideoLecturess video=new VideoLecturess();
		video.setVideoData(videoLectureDTO.getFile().getBytes());
		videoLecture.setVideoLecturess(video);
		//boolean isPaid=(!videoLecture.getVideoName().equalsIgnoreCase("Video 1") && !videoLecture.getVideoName().equalsIgnoreCase("Video 2"));
		//videoLecture.setPaid(isPaid);
		videoLectureRepo.save(videoLecture);
		return "Success";
		} catch(Exception e)
		{
			log.error("There is error for saving the video name : {}" ,e.getMessage());
			return "Error";
		}
	
		
	}
	public  byte[]  getVideoByVideoId(Long videoLecturessId)
	{
		
		//Optional<VideoLecture> videoOptional=videoLectureRepo.findById(videoId);
		Optional<VideoLecturess> vOptional=videoLecturessRepo.findById(videoLecturessId);
		VideoLecturess videoB=vOptional.get();
		return videoB.getVideoData();
	}
	public List<VideoLecture> getVideosByChapter(String finalCode)
	{
		List<VideoLecture> videoData =  videoLectureRepo.findByFinalCode(finalCode);
		
		return videoData;
		
		
	}
	public List<VideoLecture> getAllVideo()
	{
		return videoLectureRepo.findAll();
	}
	public List<ChapterDTO> getChaptersByItemCode(String itemCode)
	{
		List<ChapterDTO> t=new ArrayList();
		List<ChapterEntity> tList=chapterRepo.findByItemCode(itemCode);
		for(ChapterEntity ce:tList)
		{
			ChapterDTO pDTO=new ChapterDTO();
			pDTO.setChapterId(ce.getChapterId());
			pDTO.setChapterCode(ce.getChapterCode());
			pDTO.setChapterName(ce.getChapterName());
			pDTO.setChapterDescription(ce.getChapterDescription());
			pDTO.setItemCode(itemCode);
			pDTO.setFinalCode(ce.getFinalCode());
			t.add(pDTO);
		}
		return t;
	}
	
}