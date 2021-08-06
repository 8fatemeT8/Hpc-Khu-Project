/*
package com.example.hpc.config.jwt;

import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		try {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		} catch (ExceptionHandler ex) {
			httpServletResponse.setStatus(ex.getCode());
			httpServletResponse.getWriter().write(convertObjectToJson(ex.getMessage()));
		} catch (Exception e) {
			httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			httpServletResponse.getWriter().write(convertObjectToJson(e.getMessage()));
		}
	}

	private String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
*/
