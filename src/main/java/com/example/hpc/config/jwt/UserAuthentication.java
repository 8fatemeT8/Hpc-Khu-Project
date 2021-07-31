package com.example.hpc.config.jwt;

import com.example.hpc.utils.exceptions.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserAuthentication {

	private AuthenticationManager authenticationManager;
	private JwtTokenUtil jwtTokenUtil;
	private UserDetailsService userDetailsService;

	public UserAuthentication(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,UserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
	}

	public JwtResponse authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			final String token = jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(username),null);

			return new JwtResponse(token);
		} catch (DisabledException e) {
			throw new ExceptionHandler("Unauthorized - DisabledException ",HttpStatus.UNAUTHORIZED.value());
		} catch (BadCredentialsException e) {
			throw new ExceptionHandler ("Unauthorized - BadCredentialsException ",HttpStatus.UNAUTHORIZED.value());
		}
	}
}
