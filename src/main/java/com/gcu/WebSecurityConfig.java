package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gcu.services.UserBusinessService;

@Configuration
public class WebSecurityConfig {

    private final UserBusinessService userService;

    public WebSecurityConfig(UserBusinessService userService) {
        this.userService = userService;
    }

    // BCrypt password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Main security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Disable CSRF for simplicity (OK for class project)
        http.csrf(csrf -> csrf.disable());

        // What URLs are public vs protected
        http.authorizeHttpRequests(auth -> auth
                // Public pages
                .requestMatchers("/", "/login", "/register",
                                 "/css/**", "/images/**", "/js/**")
                .permitAll()
                // Everything else requires authentication
                .anyRequest().authenticated()
        );

        // Form login configuration
        http.formLogin(form -> form
                .loginPage("/login")              // our custom login page
                .usernameParameter("username")    // matches login.html
                .passwordParameter("password")    // matches login.html
                .defaultSuccessUrl("/", true)     // redirect here after login
                .permitAll()
        );

        // Logout configuration
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
        );

        return http.build();
    }

    // Hook AuthenticationManager to our UserBusinessService + BCrypt
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        auth.userDetailsService(userService)
            .passwordEncoder(passwordEncoder());

        return auth.build();
    }
}
