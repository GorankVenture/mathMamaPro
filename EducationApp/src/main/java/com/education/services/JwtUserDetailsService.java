package com.education.services;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.education.entity.SignEntity;
import com.education.repository.UserRepo;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	//@Autowired
	//UserEntranceRepo entranceRepo;

	@Autowired
	private UserRepo userRepo;

	Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		try {
			Optional<SignEntity> user = userRepo.findByEmail(email);
			if (user == null || !user.isPresent()) {
				throw new UsernameNotFoundException("User not found with username: " + email);

			} else {
				return new User(user.get().getEmail(), user.get().getPassword(), new ArrayList<>());
			}

		} catch (Exception e) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}

	}
}

