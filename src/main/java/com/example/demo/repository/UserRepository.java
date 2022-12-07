package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserTable;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserTable, Integer>
{

	@Query(value="select u from UserTable u where u.username= :username and u.password= :password")
	public UserTable validateUser(String username, String password);
}
