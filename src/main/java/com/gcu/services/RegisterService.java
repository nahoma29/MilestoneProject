package com.gcu.services;

import org.springframework.stereotype.Service;

import com.gcu.data.UsersRepositoryInterface;
import com.gcu.models.UserEntity;

@Service
public class RegisterService implements RegisterServiceInterface {

    private final UsersRepositoryInterface usersRepository;

    public RegisterService(UsersRepositoryInterface usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean register(String firstName, String lastName, String username, String password) {
        // Simple duplicate check (optional)
        if (usersRepository.findByUsernameIgnoreCase(username) != null) return false;

        // Save with a default role
        UserEntity entity = new UserEntity(null, username, password, "USER");
        usersRepository.save(entity);
        return true;
    }
}
