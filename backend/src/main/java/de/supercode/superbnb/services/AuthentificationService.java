package de.supercode.superbnb.services;


import de.supercode.superbnb.dtos.auth.AuthAdminRegDTO;
import de.supercode.superbnb.dtos.auth.AuthRegDTO;
import de.supercode.superbnb.entities.Address;
import de.supercode.superbnb.entities.Payment;
import de.supercode.superbnb.entities.person.Role;
import de.supercode.superbnb.entities.person.User;
import de.supercode.superbnb.repositorys.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthentificationService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private TokenService tokenService;


    public AuthentificationService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public User userRegister(AuthRegDTO dto) {
        User newUser = creatNewUser(dto);
        if (dto.phone() != null) newUser.setPhone(dto.phone());
        if (dto.street() != null) {
            newUser.setAddress(creatNewAddress(dto));
        }
        newUser.setPayment(creatNewPayment(dto));
        return userRepository.save(newUser);
    }






    public boolean userRegisterByAdmin(AuthAdminRegDTO dto) {
        Optional<User> existUser = userRepository.findByEmail(dto.email());
        if (existUser.isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        User newUser = new User(dto.firstName(), dto.lastName(), dto.email(), passwordEncoder.encode(dto.password()));
        if (dto.phone() != null) newUser.setPhone(dto.phone());
        newUser.setRole(dto.role());
        userRepository.save(newUser);
        return true;

    }

    private User creatNewUser(AuthRegDTO dto) {
       return new User(
            dto.firstName(),
            dto.lastName(),
            dto.email(),
            passwordEncoder.encode(dto.password())
       );
    }

    private Payment creatNewPayment(AuthRegDTO dto) {
        return new Payment(
            dto.cardNumber(),
            dto.cvv(),
            dto.expirationDate()
        );
    }

    private Address creatNewAddress(AuthRegDTO dto) {
        return new Address(
            dto.street(),
            dto.houseNumber(),
            dto.city(),
            dto.zipCode(),
            dto.country()
        );
    }


    public void changePassword(User existUser, String newPassword) {
        newPassword = passwordEncoder.encode(newPassword);
        if (existUser.getPassword().equals(newPassword)) return;
        else {
            existUser.setPassword(newPassword);
            userRepository.save(existUser);
        }
    }

    public boolean hasAdminRights(Authentication authentication) {
        Optional<User> user = userRepository.findByEmail(authentication.getName());
        if (user.isPresent()){
            if (user.get().getRole().equals(Role.ADMIN)) return true;
            else return false;
        } else return false;
}

    public String getToken(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }
}
