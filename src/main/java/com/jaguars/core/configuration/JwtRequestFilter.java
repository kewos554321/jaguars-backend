package com.jaguars.core.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jaguars.core.configuration.impl.JwtUserDetailsService;
import com.jaguars.core.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
		
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
		log.info("request-url={}", request.getRequestURL());
				
		// Get authorization header
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		log.info("Start to get authorization header and validate..., header={}", header);
		if (!StringUtils.hasText(header) || !header.startsWith("Bearer")) {
			log.info(!StringUtils.hasText(header) ? "!StringUtils.hasText(header)" : "!header.startsWith('Bearer')");
			chain.doFilter(request, response);
			return;
		}

		// Extract jwt token from header
		final String jwt = header.substring(7);

		// Validate token
		String email = null;
		try {
			email = jwtUtil.extractEmail(jwt);
		} catch (ExpiredJwtException e) {
			log.info("JWT Token has expired");
		}

		if (email == null || jwtUtil.isTokenExpired(jwt)) {
			log.info(email == null ? "email == null" : "jwtUtil.isTokenExpired(jwt)");
			chain.doFilter(request, response);
			return;
		}

		UserDetails userDetails = jwtUserDetailsService.loadUserByEmail(email);
		if (userDetails == null || !jwtUtil.validateToken(jwt, userDetails)) {
			log.info(userDetails == null ? "userDetails == null" : "!jwtUtil.validateToken(jwt, userDetails)");
			chain.doFilter(request, response);
			return;
		}

		// Set user identity on the spring security context
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// Generate refresh token
		String refreshToken = jwtUtil.generateRefreshToken(userDetails);
		// response.setHeader("Access-Control-Expose-Headers", "RefreshToken");
		response.setHeader("RefreshToken", refreshToken);
		log.info("RefreshToken has been created...");

		chain.doFilter(request, response);
    }

}
