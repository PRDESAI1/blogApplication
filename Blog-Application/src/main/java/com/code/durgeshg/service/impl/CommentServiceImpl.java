package com.code.durgeshg.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.durgeshg.entity.Comment;
import com.code.durgeshg.entity.Post;
import com.code.durgeshg.exception.RsourseNotFoundException;
import com.code.durgeshg.payloads.CommentDto;
import com.code.durgeshg.repo.CommentRepo;
import com.code.durgeshg.repo.PostRepo;
import com.code.durgeshg.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	ModelMapper modeMapper;
	
	@Override
	public CommentDto addComment(CommentDto commentDto, int postId) {
		Comment comment=modeMapper.map(commentDto, Comment.class);
		Post post=postRepo.findById(postId).orElseThrow(()->new RsourseNotFoundException("postId","Id",postId));
	 
		comment.setPost(post);
		
		Comment savedComment=commentRepo.save(comment);
		
		CommentDto commentdt=modeMapper.map(savedComment, CommentDto.class);
		return commentdt;
	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment=commentRepo.findById(commentId).orElseThrow(()->new RsourseNotFoundException("commentId","commentId",commentId));
		commentRepo.delete(comment);
		
	}

}
