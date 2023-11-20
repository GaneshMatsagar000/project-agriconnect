package com.agri.agriculture.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            	.antMatchers("/api/farmers/register").permitAll()  
            	.antMatchers("/api/farmers/{id}").permitAll()
            	.antMatchers("/api/workrequests/all").permitAll()
            	.antMatchers("/register").permitAll()
            	.antMatchers("/api/drivers/register").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/public/**").permitAll() // Allow public access to resources under /public/**
                .antMatchers(HttpMethod.POST, "/api/tractorowners/register").permitAll() // Allow registration without authentication
                .antMatchers("/css/**", "/js/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/api/location").permitAll()
                .antMatchers("/api/send-otp").permitAll() // Allow unauthenticated access to /api/send-otp
                .antMatchers("/api/verify-otp").permitAll()
                .antMatchers("/api/farmers/all").permitAll()
                .antMatchers("/api/farmers/profile/{username}").permitAll()
                
                .antMatchers("/api/**").authenticated()
                
                .anyRequest().permitAll() 
                
                .and()
            .httpBasic()
        .and()
        .formLogin()
            .loginPage("/login") // Customize the login page URL if needed
            .permitAll() // Allow access to the login page
            .and()
        .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login") // Customize the logout success URL if needed
            .and()
        .csrf().disable(); // Disable CSRF protection for simplicity
        }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

  
    

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("ganesh").password(passwordEncoder().encode("ganesh")).roles("USER");
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("ganesh")
            .password(passwordEncoder().encode("ganesh"))
            .roles("USER") // Add roles as needed
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
