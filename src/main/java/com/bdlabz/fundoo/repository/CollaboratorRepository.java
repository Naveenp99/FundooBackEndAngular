package com.bdlabz.fundoo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bdlabz.fundoo.entitymodel.Collaborator;


@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {

	Collaborator deleteByid(long id);
	
	Collaborator findById(long id);
	
	@Query( value = "select * from collaborator_table where notes_id = :id", nativeQuery = true)
	List<Collaborator> getallCollaborator( long id);
	
}

