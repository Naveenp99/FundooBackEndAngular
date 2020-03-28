package com.bdlabz.fundoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bdlabz.fundoo.entitymodel.Collaborator;

@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long> {

	Collaborator deleteByid(long id);
	
	Collaborator findById(long id);
}

/*
 
  install typescript
 $npm install typescript --save-dev     // As dev dependency
 
 $npm install typescript -g             //  Install as global module
 
 $npm install typescript@latest -g      //  Install latest if you have older version

 * 
 */
