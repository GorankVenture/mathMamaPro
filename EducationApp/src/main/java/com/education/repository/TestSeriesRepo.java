package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.entity.TestSeries;

public interface  TestSeriesRepo  extends JpaRepository  <TestSeries,Long>
{
   List<TestSeries> findByItemCode(String itemCode);
}
