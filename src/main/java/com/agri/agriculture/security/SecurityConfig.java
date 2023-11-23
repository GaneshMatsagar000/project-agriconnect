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
                .antMatchers("/api/**").authenticated()
                .antMatchers("/api/farmers/register").permitAll()
                .antMatchers("/api/farmers/{id}").permitAll()
                .antMatchers("/api/workrequests/all").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/api/drivers/register").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/public/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/tractorowners/register").permitAll()
                .antMatchers("/css/**", "/js/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/api/location").permitAll()
                .antMatchers("/api/send-otp").permitAll()
                .antMatchers("/api/verify-otp").permitAll()
                .antMatchers("/api/farmers/all").permitAll()
                .antMatchers("/api/farmers/profile/{username}").permitAll()
                .anyRequest().permitAll()
                .and()
            .httpBasic()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
            .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
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
