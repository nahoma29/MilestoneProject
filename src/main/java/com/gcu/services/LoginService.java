package com.gcu.services;

import org.springframework.stereotype.Service;

import com.gcu.data.UsersRepositoryInterface;
import com.gcu.models.UserEntity;

@Service
public class LoginService implements LoginServiceInterface {

    private final UsersRepositoryInterface usersRepository;

    public LoginService(UsersRepositoryInterface usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean authenticate(String username, String password) {
        UserEntity user = usersRepository.findByUsernameIgnoreCase(username);
        if (user == null) return false;
        // Milestone 4: plain text compare (hashing comes later in M6)
        return password != null && password.equals(user.getPassword());
    }
}
