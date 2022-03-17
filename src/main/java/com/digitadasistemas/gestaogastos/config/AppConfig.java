package com.digitadasistemas.gestaogastos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public JwtAccessTokenConverter accessTokenConverter() {
//		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
//		tokenConverter.setSigningKey("MY-SECRET-KEY");
//		return tokenConverter;
//	}
	
//	@Bean
//	public JwtTokenStore jwtTokenStore() {
//		return new JwtTokenStore(accessTokenConverter());
//	}
	
}
