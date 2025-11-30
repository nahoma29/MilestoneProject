package com.gcu.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gcu.data.UsersRepositoryInterface;
import com.gcu.models.UserEntity;

@Service
public class RegisterService implements RegisterServiceInterface {

    private final UsersRepositoryInterface usersRepository;
    private final PasswordEncoder encoder;

    public RegisterService(UsersRepositoryInterface usersRepository,
                           PasswordEncoder encoder) {
        this.usersRepository = usersRepository;
        this.encoder = encoder;
    }

    @Override
    public boolean register(String username, String password) {

        System.out.println("RegisterService.register called: " + username);

        // Check if username already exists
        if (usersRepository.findByUsernameIgnoreCase(username) != null) {
            System.out.println("Username already exists: " + username);
            return false;
        }

        // Build the entity
        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        entity.setPassword(encoder.encode(password)); // BCrypt hash
        entity.setRole("USER");                       // <-- important

        // Save to DB
        usersRepository.save(entity);
        System.out.println("User saved: " + entity);

        return true;
    }
}
