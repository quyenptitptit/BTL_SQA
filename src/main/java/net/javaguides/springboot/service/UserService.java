package net.javaguides.springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	UserRegistrationDto getUser(String email);
	User update(UserRegistrationDto registrationDto);
	User save(UserRegistrationDto registrationDto);
}
