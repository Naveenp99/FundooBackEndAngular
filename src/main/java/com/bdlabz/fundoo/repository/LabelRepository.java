package com.bdlabz.fundoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bdlabz.fundoo.entitymodel.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long>{

	Label findByid(long id);
	
}
