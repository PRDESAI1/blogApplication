package com.code.durgeshg.payloads;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	
	private int id;
    
   @NotEmpty
   @Size(min=4,message="Username must be Min 4 character")
	private String name;
   @Email(message="email is not correct")
	private String email;
   @NotEmpty
   @Size(min=3,max=10,message="password must be min of 3 char nad max 10 char")
	private String password;
   @NotEmpty
	private  String about;
	


}
