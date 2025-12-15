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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF (OK for class project)
            .csrf(csrf -> csrf.disable())

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                // REST APIs require authentication (Basic Auth)
                .requestMatchers("/api/**").authenticated()

                // Public pages
                .requestMatchers("/", "/login", "/register",
                                 "/css/**", "/images/**", "/js/**")
                .permitAll()

                // Everything else
                .anyRequest().authenticated()
            )

            .httpBasic(basic -> {})

            // Form login for MVC pages
            .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )

            // Logout
            .logout(logout -> logout
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