package com.code.durgeshg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.durgeshg.payloads.CommentDto;
import com.code.durgeshg.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}")
	public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto comment,@PathVariable int postId){
	CommentDto commentDto=	commentService.addComment(comment, postId);
		
		
		
		return new ResponseEntity<>(commentDto,HttpStatus.CREATED) ;}
	
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable int commentId){
		commentService.deleteComment( commentId);
		
		
		
		return new ResponseEntity<>("deleted",HttpStatus.OK) ;}
	

}
