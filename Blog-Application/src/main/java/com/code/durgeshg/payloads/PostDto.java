package com.code.durgeshg.payloads;



import java.util.Date;
import java.util.List;

import com.code.durgeshg.entity.Category;
import com.code.durgeshg.entity.Comment;
import com.code.durgeshg.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	
	private int PostId;
	private String title;
	
	private String content;
	
	private String image;
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private List<CommentDto> comment;
	
}
