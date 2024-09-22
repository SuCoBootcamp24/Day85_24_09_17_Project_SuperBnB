package de.supercode.superbnb.services;

import de.supercode.superbnb.dtos.user.UserListDTO;
import de.supercode.superbnb.dtos.user.UserResponseDTO;
import de.supercode.superbnb.entities.person.Role;
import de.supercode.superbnb.entities.person.User;
import de.supercode.superbnb.repositorys.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }


    public UserResponseDTO getUserDetails(String email) {
        User existUser = getUserByEmail(email);
        return new UserResponseDTO(existUser.getId(), existUser.getFirstName(), existUser.getLastName());
    }


    public List<UserListDTO> getUserList(String email) {
        User existUser = getUserByEmail(email);
        if (!existUser.getRole().equals(Role.ADMIN)) throw new RuntimeException("You are not a Administrator!");
        return userRepository.findAll().stream()
                .map(user -> new UserListDTO(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRole().toString()
                ))
                .toList();
    }
}
