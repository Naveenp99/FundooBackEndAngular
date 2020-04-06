package com.bdlabz.fundoo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	
	@Transactional
	@Modifying
	@Query( value = " update label_table set notes_id = :noteId where id = :labelId", nativeQuery = true)
	void updateNoteId( long labelId, long noteId); 
	
	@Query( value = " select * from label_table where label_title = :title", nativeQuery = true)
	Label getLabelsByTitle( String title);
	
}
