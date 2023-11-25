package com.springmvcsecurity.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {


    //add support for JDBC
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        //define query to retrieve user/roles  a user by username

        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_id,pw,active FROM members WHERE user_id=? ");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_id,role FROM roles WHERE user_id=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/systems/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )

                .formLogin(form ->
                        form
                                .loginPage("/showLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                                ).logout(logout->logout.permitAll())
                .exceptionHandling
                        (configure->configure.accessDeniedPage("/access-denied"));

        return http.build();
    }

    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails radoslav = User.builder()
                .username("rado")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build()
                ;

        UserDetails nikoleta = User.builder()
                .username("niki")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();
        UserDetails petya = User.builder()
                .username("petya")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(radoslav, nikoleta, petya);
    }
  */
}
