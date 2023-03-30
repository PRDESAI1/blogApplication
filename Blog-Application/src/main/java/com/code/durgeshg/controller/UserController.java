package com.code.durgeshg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.durgeshg.payloads.UserDto;
import com.code.durgeshg.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
	private UserService userService;
    
	//post
    @PostMapping("/")
	public ResponseEntity<UserDto> postUser(@Valid @RequestBody UserDto userDto){
    	UserDto createdUserDto=userService.createUser(userDto);
    	
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
		
	}
    
    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable int userId ,@RequestBody UserDto userDto){
    	UserDto savedUserDto=userService.updateUser(userDto, userId);
		return new ResponseEntity<>(savedUserDto,HttpStatus.OK);
    	
    }
	
	//delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
    	userService.deleteUser(userId);
		return new ResponseEntity<>("delete",HttpStatus.OK);
    	
    }
    
	//get
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {
    	List<UserDto> allUser=userService.getAllUsers();
		return new ResponseEntity<>(allUser,HttpStatus.OK);
    	
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable int userId) {
    	UserDto User=userService.getUserById(userId);
		return new ResponseEntity<>(User,HttpStatus.OK);
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
	
}
