package com.bdlabz.fundoo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bdlabz.fundoo.entitymodel.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findOneById(Object obj);
	
	User findOneByemail(Object obj);
	
	@Query( value = "select * from user_table where user_email  = :email", nativeQuery = true)
	User findOneByuserEmail( String email);
	
	@Query( value = "select * from user_table", nativeQuery = true)
	List<User> getall();
	
	@Transactional
	@Modifying
	@Query( value = "update user_table set user_picname = :Image where user_id = :userId", nativeQuery = true)
	void uploadImage(String Image, long userId);
	
}
