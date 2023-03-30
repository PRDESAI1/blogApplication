package com.code.durgeshg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.code.durgeshg.entity.User;
import com.code.durgeshg.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, int UserId);

	UserDto getUserById(int userid);

	List<UserDto> getAllUsers();

	void deleteUser(int userId);
}
