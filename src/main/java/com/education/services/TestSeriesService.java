package com.education.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.education.entity.TestSeries;
import com.education.model.TestSeriesDTO;
import com.education.repository.TestSeriesRepo;
@Service
public class TestSeriesService {

	
	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TestSeriesService.class);
	@Autowired
	private TestSeriesRepo testSeriesRepo;
	
	public TestSeriesDTO saveTestSeries(TestSeriesDTO testSeriesDTO)
	{
		
		try
		{
			TestSeries testSeries=testSeriesRepo.findById(testSeriesDTO.getTestSeriesId()).get();
			testSeries.setTestSeriesId(testSeriesDTO.getTestSeriesId());
			testSeries.setTestSeriesName(testSeriesDTO.getTestSeriesName());
			testSeries.setItemCode(testSeriesDTO.getItemCode());
			byte[] fileBytes=testSeriesDTO.getTestSeriesFile().getBytes();
			String baseTestSeries=Base64.getEncoder().encodeToString(testSeriesDTO.getTestSeriesFile().getBytes());
			testSeries.setTestPdf(fileBytes);
			  testSeriesRepo.save(testSeries);
			  testSeriesDTO.setBaseTestSeries(baseTestSeries);
			  testSeriesDTO.setTestSeriesFile(null);
			 return testSeriesDTO;
		} catch(Exception e)
		{
			log.error("There is an exception for test series {} ",e.getMessage());
			return testSeriesDTO;
		}
	}
	public List<TestSeries> getAllTestSeries()
	{
		return testSeriesRepo.findAll();
	}
	
	
	public List<TestSeriesDTO> getAllTestSeriesByItemCode(String itemCode)
	{
		List<TestSeriesDTO> tList=new ArrayList<>();
		List<TestSeries> b=testSeriesRepo.findByItemCode(itemCode);
		for(TestSeries t:b)
		{
			TestSeriesDTO testSeriesDTO=new TestSeriesDTO();
			testSeriesDTO.setTestSeriesId(t.getTestSeriesId());
			testSeriesDTO.setTestSeriesName(t.getTestSeriesName());
			testSeriesDTO.setItemCode(itemCode);
			testSeriesDTO.setBaseTestSeries(new String(t.getTestPdf()));
			tList.add(testSeriesDTO);
		}
		return tList;
	}
	
	public byte[] getTestSeriesById(Long testSeriesId)
	 {
		 Optional<TestSeries> tOptional=testSeriesRepo.findById(testSeriesId);
		 TestSeries testSeries=tOptional.get();
		 return testSeries.getTestPdf();
	 }
}
