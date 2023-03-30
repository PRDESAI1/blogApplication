package com.code.durgeshg.service;

import com.code.durgeshg.entity.Comment;
import com.code.durgeshg.payloads.CommentDto;

public interface CommentService {
	
	CommentDto addComment(CommentDto comment,int postId);
	void deleteComment(int commentId);

}
