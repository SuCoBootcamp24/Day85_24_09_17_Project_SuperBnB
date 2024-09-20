package de.supercode.superbnb.services;


import de.supercode.superbnb.dtos.auth.AuthRegDTO;
import de.supercode.superbnb.entities.Address;
import de.supercode.superbnb.entities.person.User;
import de.supercode.superbnb.repositorys.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AuthentificationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User UserRegister(AuthRegDTO dto) {
        User newUser = new User();
        newUser.setFirstName(dto.firstName());
        newUser.setLastName(dto.lastName());
        newUser.setEmail(dto.email());
        newUser.setPassword(passwordEncoder.encode(dto.password()));
        if (dto.phone() != null) newUser.setPhone(dto.phone());
        if (dto.street() != null) {
            Address newAddress = new Address();
            newAddress.setStreet(dto.street());
            newAddress.setHouseNumber(dto.houseNumber());
            newAddress.setZipCode(dto.postalCode());
            newAddress.setCity(dto.city());
            newAddress.setCountry(dto.country());
            newUser.setAddress(newAddress);
        }
        return userRepository.save(newUser);
    }
}
