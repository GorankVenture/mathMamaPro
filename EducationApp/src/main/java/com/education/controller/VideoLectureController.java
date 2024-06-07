package com.education.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.entity.VideoLecture;
import com.education.model.ChapterDTO;
import com.education.model.Response2;
import com.education.model.ResponseWithList;
import com.education.model.VideoLectureDTO;
import com.education.services.VideoLectureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@CrossOrigin(origins="*")
@RequestMapping("api")
@Tag(name="VideoLecture-API")
public class VideoLectureController {

	@Autowired
	private VideoLectureService videoLectureService;
	
	@PostMapping(value="/uploadVideo",consumes= {MediaType.MULTIPART_FORM_DATA_VALUE},produces=MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary="to upload video lectures",description="this api is used to upload  video lectures")
	public  ResponseEntity<?> uploadVideo(@ModelAttribute VideoLectureDTO videoLectureDTO)
	{
		String r=videoLectureService.saveVideoLecture(videoLectureDTO);
		if("Success".equals(r))
		{
			return Response2.generateResponse("Videos are get uploaded", HttpStatus.OK,"200");
		}
		else
		{
			return Response2.generateResponse("No video is there",HttpStatus.INTERNAL_SERVER_ERROR,"500");
		}
	}
	
	@PutMapping(value="/updateVideo")
	@Operation(summary="to update Video lectures",description="this api is used to update the video lectures")
	public ResponseEntity<?> updateVideo(@ModelAttribute VideoLectureDTO videoLectureDTO)
	{
		String r=videoLectureService.saveVideoLecture(videoLectureDTO);
		if("Success".equals(r))
		{
			return Response2.generateResponse("Video updated successfully", HttpStatus.OK, "200");
		}
		else
		{
			return Response2.generateResponse("Video are not updated successfully", HttpStatus.BAD_REQUEST, "400");
		}
	}
	
	@GetMapping(value="stream/{videoLecturessId}")
	@Operation(summary="to stream video lectures",description="this api is used to stream the video lectures")
	public ResponseEntity<InputStreamResource> streamVideo(@PathVariable  Long videoLecturessId) throws IllegalStateException, IOException
	{
		byte[] videoBytes=videoLectureService.getVideoByVideoId(videoLecturessId);
		HttpHeaders header=new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ByteArrayInputStream stream=new ByteArrayInputStream(videoBytes);
		return new ResponseEntity<>(new InputStreamResource(stream),header,HttpStatus.OK);
	}
   
    @GetMapping(value="/findChapterByItemCode")
    @Operation(summary="find chapter by item code",description="this api is used to find chapter by item code")
   public ResponseEntity<?> findChapterByCourseCode(@RequestParam ("itemcode") String itemCode)
   {
	List<ChapterDTO> c=videoLectureService.getChaptersByItemCode(itemCode);
	return new ResponseWithList().generateResponse("Chapters are displayed according to coursecode",HttpStatus.OK,"200",c);
  }

	@GetMapping(value="/chapterFinalCode")
	@Operation(summary="to fetch the videos by chapterwise",description="this api is fetch the videos by chapterwise")
	public ResponseEntity<Object> getVideosByChapter(@RequestParam ("finalCode")  String finalCode)
	{
		
	    List<VideoLecture> v=videoLectureService.getVideosByChapter(finalCode);
		return new ResponseWithList().generateResponse("provide", HttpStatus.OK, "200", v);
	}
	
	@GetMapping(value="findVideo")
	@Operation(summary="find video details",description="this api is used to find the video details")
	public ResponseEntity<Object> getAllVideo()
	{
		List<VideoLecture> v=videoLectureService.getAllVideo();
		return new ResponseWithList().generateResponse("provide",HttpStatus.OK,"200",v);
	}
	
}
