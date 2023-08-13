package com.jaguars.core.configuration;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.codec.CodecConfigurer.CustomCodecs;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
    
	@Autowired
	private OAuth2UserService oauth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
        .csrf().disable()
        .cors().configurationSource(corsConfigurationSource()).and()

        // Authorize requests
        .authorizeRequests()
            .antMatchers("/authenticate", "/authorize", "/refeshToken").permitAll()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .anyRequest().authenticated()
            .and()

        // JWT authentication
        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)

        // OAuth2 login
        .oauth2Login()
        .userInfoEndpoint()
        .userService(oauth2UserService)  // Your implementation of OAuth2UserService
        .and()
        .defaultSuccessUrl("/defaultOAuth2SuccessURL", true); // Redirect URL after successful OAuth2 authentication
        
        http.logout()
            .logoutUrl("/api/logout")
            .logoutSuccessHandler((request, response, authentication) -> {
                log.info("logout: request={}, response={}, authentication={}", request, response, authentication);
                response.setStatus(HttpServletResponse.SC_OK);
            }).permitAll();
    return http.build();
        
        // http
        //     .csrf().disable()
        //     .cors().configurationSource(corsConfigurationSource()).and()
        //     .httpBasic().and()
        //     .authorizeRequests()
        //         .antMatchers("/authenticate", "/authorize", "/refeshToken").permitAll()
        //         .antMatchers(HttpMethod.OPTIONS).permitAll()
        //         .anyRequest().authenticated()
        //         .and()
        //         .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
        //         .and()
        //         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //         .and()
        //         .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
        //     .logout()
        //         .logoutUrl("/api/logout")
        //         .logoutSuccessHandler((request, response, authentication) -> {

        //             log.info("request={}, response={}, authentication={}", request, response, authentication);
        //             response.setStatus(HttpServletResponse.SC_OK);
        //         }).permitAll();
        // return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://jaguars-web.netlify.app"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "*"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
