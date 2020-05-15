package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

	@Repository
	public interface EmpRepository extends CrudRepository<Assessment, Long>
	{
		
//		@Query("from Employee where name=?1")
		//Assessment findByUserid(String userid);

	}
	
