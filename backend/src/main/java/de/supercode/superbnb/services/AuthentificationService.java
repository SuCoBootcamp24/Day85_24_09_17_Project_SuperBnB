package de.supercode.superbnb.services;


import de.supercode.superbnb.dtos.auth.AuthRegDTO;
import de.supercode.superbnb.entities.Address;
import de.supercode.superbnb.entities.Payment;
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
        User newUser = creatNewUser(dto);
        if (dto.phone() != null) newUser.setPhone(dto.phone());
        if (dto.street() != null) {
            newUser.setAddress(creatNewAddress(dto));
        }
        newUser.setPayment(creatNewPayment(dto));
        return userRepository.save(newUser);
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
            dto.postalCode(),
            dto.country()
        );
    }
}
