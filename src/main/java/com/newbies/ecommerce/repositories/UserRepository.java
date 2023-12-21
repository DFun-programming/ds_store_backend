package com.newbies.ecommerce.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.newbies.ecommerce.entities.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

	List<Users> findAllByEmail(String email);
	
}
