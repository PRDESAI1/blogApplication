package com.code.durgeshg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.durgeshg.entity.User;
@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

	Optional<User> findByEmail(String email);
}
