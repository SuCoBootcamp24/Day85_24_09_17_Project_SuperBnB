package de.supercode.superbnb.services;

import de.supercode.superbnb.entities.person.User;
import de.supercode.superbnb.repositorys.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;



    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
