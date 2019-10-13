package br.edu.gazin.envers.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
				.antMatchers(HttpMethod.GET, "/v1/books/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/v1/books").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/v1/books**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PATCH, "/v1/books/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/v1/books/**").hasAnyRole("USER", "ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("user").roles("USER").build());
        manager.createUser(users.username("admin").password("admin").roles("USER", "ADMIN").build());
        return manager;

    }
}
