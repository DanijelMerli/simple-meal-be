package com.simpletask.simplemeal.security;

import com.simpletask.simplemeal.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
    public UserDetailsService userDetailsService() {return new CustomUserDetailsService(userRepository);}

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Transactional
 	@Bean
 	public DaoAuthenticationProvider authenticationProvider() {
 	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
 	}

 	@Bean
 	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
 	    return authConfig.getAuthenticationManager();
 	}

	@Autowired
	private TokenUtils tokenUtils;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    http.authorizeRequests()
	        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	        .requestMatchers("/api/auth/**").permitAll()
	        .anyRequest().authenticated()
	        .and()
	        .cors().and()
	        .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, userDetailsService()), BasicAuthenticationFilter.class);

	    http.csrf().disable();
	    http.headers().frameOptions().disable();
	    http.authenticationProvider(authenticationProvider());

	    return http.build();
	}

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
    	return (web) -> web.ignoring().requestMatchers(HttpMethod.POST, "/socket/**", "/api/auth/**")
    			.requestMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico",
    			"/*/*.html", "/*/*.css", "/*/*.js", "/socket/**");
//				.requestMatchers(HttpMethod.PUT, "api/meal/**")
//				.requestMatchers(HttpMethod.DELETE, "api/meal/**");
	}
}