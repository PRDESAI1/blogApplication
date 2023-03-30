package com.code.durgeshg.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.durgeshg.entity.Comment;
@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer>{

}
