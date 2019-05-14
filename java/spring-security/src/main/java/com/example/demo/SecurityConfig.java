package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("Dai").password(passwordEncoder().encode("daiNoPassword")).roles("USER")
				.and()
				.withUser("Jojo").password(passwordEncoder().encode("jojoNoPassword")).roles("USER")
				.and()
				.withUser("Erai").password(passwordEncoder().encode("eraiNoPassword")).roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/anonymous*").anonymous()
				.antMatchers("/login*").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login.html")
				.loginProcessingUrl("perform_login")
				.defaultSuccessUrl("/home.html", true)
//				.failureHandler(authenticationFailureHandler())
				.and()
				.logout()
				.logoutUrl("/logout")
				.deleteCookies("JSESSIONID");
//				.logoutSuccessHandler(LogoutSuccessHandler())
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
