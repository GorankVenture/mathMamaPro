package com.education.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.entity.CourseEntity;
import com.education.model.CourseDTO;
import com.education.repository.CourseRepo;


@Service
public class CourseService {

	
	@Autowired
	private ModelMapper mapper;
	
	//private org.slf4j.LoggerFactory log=org.sl4j.LoggerFactory.getLogger(CourseService.class);
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CourseService.class);
	
	@Autowired
	private CourseRepo courseRepo;
	public CourseDTO saveCourse(CourseDTO courseDTO)
	{
		try
		{
		CourseEntity courseEntity=new CourseEntity();
		String courseCode=generateCourseCode(courseDTO.getCourseName());
		String courseSubCode=generateCourseSubCode(courseDTO.getCourseSubName());
		courseEntity.setCourseName(courseDTO.getCourseName());
		courseEntity.setCourseSubName(courseDTO.getCourseSubName());
		courseEntity.setCourseCode(courseCode);
		courseEntity.setCourseSubCode(courseSubCode);
		courseRepo.save(courseEntity);
		CourseDTO savedDTO=convertToDTO(courseEntity);
		return savedDTO;
		} catch(Exception e)
		{
			log.error ("There is an error  for registering the courde enrolled : {} ",e.getMessage());
			return null;
		}
		  
	}
	
	
//	private String generateCourseCode(String courseName)
//	{
//		
//		    // String cP=courseName.toUpperCase();
//	
//			if(courseName.contains("Foundation Class"))
//			{
//				return "FC";
//			}
//			else if(courseName.contains("11th Class"))
//			{
//				return "11C";
//			}
//			else if(courseName.contains("12th Class"))
//			{
//				return "12C";
//		}
//			else if(courseName.contains("Target Batch"))
//			{
//				return "TB";
//			}
//				
//			else if(courseName.contains("JEE"))
//			{
//				return "JEE";
//			}
//			else if(courseName.contains("NEET"))
//			{
//				return "NEET";
//			}
//			else if(courseName.contains("Formula Test"))
//			{
//				 return "FT";
//			}
//			else
//			{
//				return "Unknown";
//			}
//			
//		}
//		
	
//	private String generateCourseSubCode(String courseSubName)
//	{
//		//String cN=courseSubName.toUpperCase();
//			if(courseSubName.contains("9th Class"))
//			{
//				return "9C";
//			}
//			else if(courseSubName.contains("10th Class"))
//			{
//				return "10C";
//			}
//			else if(courseSubName.contains("JEE Mains"))
//			{
//				
//				return "JM";
//		    }
//			else if(courseSubName.contains("JEE Advance"))
//		    {
//				
//				return "JA";
//			}
//			else if(courseSubName.contains("JEE (Mains+Advance)"))
//			{
//				
//        		return "JMA";
//			}
//			else if(courseSubName.contains("11th Class"))
//			{
//				return "11C";
//			}
//			else if(courseSubName.contains("12th Class"))
//			{
//				return "12C";
//			}
//			else if(courseSubName.contains("Target Batch"))
//			{
//				  return "TB";
//			}
//			else if(courseSubName.contains("NEET"))
//				
//			{
//				return "NEET";
//			}
//			else if(courseSubName.contains("Formula Test"))
//			{
//				return "FT";
//			}
//			else
//			{
//				return "Unknown";
//			}
//		}
//	
	
	   private  String generateCourseCode(String courseName)
	   {
		//   if(courseName==null || courseName.isEmpty())
		  // {
			//   return "";
		  // }
		   
		   
		   if(courseName.equalsIgnoreCase("JEE"))
		   {
			   return "JEE";
		   }
		   if(courseName.equalsIgnoreCase("NEET"))
		   {
			   return "NEET";
		   }
		   String[] words=courseName.split(" ");
		   StringBuilder code=new StringBuilder();
		   for(String word: words)
		   {
			  
				  for(char ch:word.toCharArray())
				  {
					  if(Character.isDigit(ch))
					  {
						  code.append(ch);
					  }
				  } 
					 
					  if(word.length()>0 && Character.isLetter(word.charAt(0)))
					  {
						  code.append(Character.toUpperCase(word.charAt(0)));
					  }
				  
				 
			  }
		   
		    return code.toString(); 
	   }
	   
	   
	   private String generateCourseSubCode(String courseSubName)
	   {
		   
		   if(courseSubName.equalsIgnoreCase("JEE"))
		   {
			   return "JEE";
		   }
		   if(courseSubName.equalsIgnoreCase("NEET"))
		   {
			   return "NEET";
		   }
		   String[] arr=courseSubName.split(" ");
		   StringBuilder str=new StringBuilder();
		   for(String s :arr)
		   {
			   for(char ch:s.toCharArray())
			   {
				   if(Character.isDigit(ch))
				   {
					   str.append(ch);
				   }
			   }
			   if(s.length()>0 && Character.isLetter(s.charAt(0)))
			   {
				   str.append(Character.toUpperCase(s.charAt(0)));
			   }
		   }
		  return  str.toString();
	   }
	
//	public String generateCourseCode(String courseName)
//	{
//		if(courseName==null || courseName.isEmpty())
//		{
//			return "";
//		}
//		String[] words=courseName.split(" ");
//		StringBuilder code=new StringBuilder();
//		for(String word:words)
//		{
//			code.append(word.charAt(0));
//		}
//		return code.toString().toUpperCase();
//		
//	}
//	public String generateCourseSubCode(String courseSubName)
//	{
//		if(courseSubName==null || courseSubName.isEmpty())
//		{
//			return "";
//		}
//		String[] words=courseSubName.split(" ");
//		StringBuilder code=new StringBuilder();
//		for(String word:words)
//		{
//			
//			
//			boolean foundDigits=false;
//			while(foundDigits)
//				for(char ch:word.toCharArray())
//				{
//					if(!foundDigits && Character.isDigit(ch))
//					{
//					code.append(ch);
//					}
//					else
//					{
//						break;
//					}
//				
//			   foundDigits=true;
//			   if(foundDigits && Character.isLetter(word.charAt(0)))
//			   {
//				   code.append(Character.toUpperCase(word.charAt(0)))
//				   
//			   }
//			
////		     if(Character.isDigit(word.charAt(0)))
////		     {
////				 int i=0;
////				 while(i<word.length() && Character.isDigit(i))
////				 {
////					 code.append(word.charAt(i));
////					 i++;
////				 }
////				 if(i<word.length())
////				 {
////					 code.append(word.charAt(i));
////				 }
////				 else
////				 {
////					 code.append(word.charAt(i));
////				 }
////			}
//				
//			//code.append(word.charAt(0));
//		}
//		return code.toString().toUpperCase();
//		
//	}
	
	
    private CourseDTO convertToDTO(CourseEntity courseEntity)
    {
    	return mapper.map(courseEntity, CourseDTO.class);
    }
    
    public List<CourseDTO> getAllCourses()
    {
    	List<CourseEntity> responseList=courseRepo.findAll();
    	List<CourseDTO> courseList=new ArrayList<>();
    	for(CourseEntity c : responseList)
    	{
    		CourseDTO cE=new CourseDTO();
    		cE.setCourseId(c.getCourseId());
    		cE.setCourseName(c.getCourseName());
    		cE.setCourseSubName(c.getCourseSubName());
    		cE.setCourseCode(c.getCourseCode());
    		cE.setCourseSubCode(c.getCourseSubCode());
    		courseList.add(cE);
         }
    	return courseList;
    	
    }
	
	public List<CourseEntity> getAllCourse()
	{
		return courseRepo.findAll();
	}
		
	public List<CourseEntity>  getByCourseCode(String courseCode)
	{
		List<CourseEntity> tList=courseRepo.findByCourseCode(courseCode);
		return tList;
	}
}
