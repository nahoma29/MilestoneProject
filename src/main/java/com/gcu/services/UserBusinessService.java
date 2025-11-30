package com.gcu.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gcu.data.UsersRepositoryInterface;
import com.gcu.models.UserEntity;
import org.springframework.security.core.userdetails.User;

@Service
public class UserBusinessService implements UserDetailsService {

    private final UsersRepositoryInterface usersRepository;

    public UserBusinessService(UsersRepositoryInterface usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Look up user in the database
        UserEntity entity = usersRepository.findByUsernameIgnoreCase(username);

        if (entity == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Tell Spring Security about this user
        // password = BCrypt hash stored in DB
        // everyone just has ROLE_USER for this project
        return User.withUsername(entity.getUsername())
                   .password(entity.getPassword())
                   .roles("USER")
                   .build();
    }
}