package com.digitadasistemas.gestaogastos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableAuthorizationServer
public class SecurityConfig extends WebSecurityConfigurerAdapter{

//	private static final String[] PUBLIC_MATCHARS = {
//		"/usuarios/**"	
//	};
//	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
//	
	@Autowired
	private UserDetailsService userDetailsService;
//
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
//	
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable();
//		http.authorizeHttpRequests()
//		.antMatchers(PUBLIC_MATCHARS).permitAll()
//		.anyRequest().authenticated();
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	}
//	
//	@Bean
//	CorsConfigurationSource configurationSource() {
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//		return source;
//	}
//	
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
}
