package net.javaguides.springboot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.HoKhau;
import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.HoKhauRepository;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HoKhauRepository hoKhauRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserRegistrationDto getUser(String maBHYT) {
		User user = userRepository.findByMaBHYT(maBHYT);

		return new UserRegistrationDto(
				user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getMaBHYT(),
				user.getBirthDay(), user.getCmnd(), user.getAddress(), user.getPhoneNumber(), user.getCoquanBHYT());
	}

	@Override
	public User update(UserRegistrationDto registrationDto) {
		User user = userRepository.findByMaBHYT(registrationDto.getMaBHYT());

		user.setFirstName(registrationDto.getFirstName());
		user.setLastName(registrationDto.getLastName());
		user.setEmail(registrationDto.getEmail());
		user.setBirthDay(registrationDto.getBirthDay());
		user.setCmnd(registrationDto.getCmnd());
		user.setAddress(registrationDto.getAddress());
		user.setPhoneNumber(registrationDto.getPhoneNumber());
		user.setCoquanBHYT(registrationDto.getCoquanBHYT());

		return userRepository.save(user);
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(
				registrationDto.getFirstName(), registrationDto.getLastName(), registrationDto.getEmail(),
				passwordEncoder.encode(registrationDto.getPassword()), registrationDto.getMaBHYT(),
				registrationDto.getBirthDay(), registrationDto.getCmnd(), registrationDto.getAddress(),
				registrationDto.getPhoneNumber(), registrationDto.getCoquanBHYT(),
				Arrays.asList(new Role("ROLE_USER")), "1");

		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByMaBHYT(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		username = user.getMaBHYT();
		return new org.springframework.security.core.userdetails.User(user.getMaBHYT(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public boolean checkHoKhau(String cmnd, String username) {
		User user = userRepository.findByMaBHYT(username);
		HoKhau hokhau = hoKhauRepository.findByCmnd(cmnd);

		if (hokhau == null) {
			return false;
		} else if (user == null) {
			return false;
		} else if (hokhau.getRelateID() == user.getIdHoKhau()) {
			return false;
		} else {
			return true;
		}
	}

}
