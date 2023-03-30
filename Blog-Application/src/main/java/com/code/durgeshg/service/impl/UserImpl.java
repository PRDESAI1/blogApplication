package com.code.durgeshg.service.impl;
import com.code.durgeshg.repo.UserRepo;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.durgeshg.exception.*;
import com.code.durgeshg.entity.User;
import com.code.durgeshg.payloads.UserDto;
import com.code.durgeshg.service.UserService;
@Service
public class UserImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoUser(userDto);
		User savedUser=userRepo.save(user);
		return userToDto (savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int UserId) {
		User user = userRepo.findById(UserId)
				            .orElseThrow(() -> new RsourseNotFoundException("User", "id", UserId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User savedUser = userRepo.save(user);

		return userToDto(savedUser);
	}

	@Override
	public UserDto getUserById(int userId) {
		User user=userRepo.findById(userId).orElseThrow(()->new RsourseNotFoundException("user","id",userId) );
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUser=userRepo.findAll();
		List<UserDto> userDto=allUser.stream().map(e ->userToDto(e)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(int userId) {
		User user =userRepo.findById(userId).orElseThrow(()->new RsourseNotFoundException("use","id",userId));
        userRepo.delete(user);
	}
	
	public User dtoUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;
		
	}
	public UserDto userToDto(User user) {
		UserDto userDto =this.modelMapper.map(user, UserDto.class);

//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
		
	}

}
