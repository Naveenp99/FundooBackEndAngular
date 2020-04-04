package com.bdlabz.fundoo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bdlabz.fundoo.entitymodel.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long>{

	Label findByid(long id);
	
	@Query(value = " select * from label_table", nativeQuery = true)
	List<Label> getAllLabels();
	
	@Query(value = " select * from label_table where id = :lid", nativeQuery = true)
	Label getAllLabelById(long lid);
	
}
