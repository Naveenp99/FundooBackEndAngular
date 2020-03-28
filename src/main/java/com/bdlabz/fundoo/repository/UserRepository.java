package com.bdlabz.fundoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bdlabz.fundoo.entitymodel.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findOneById(Object obj);
	
	User findOneByemail(Object obj);
	
}
