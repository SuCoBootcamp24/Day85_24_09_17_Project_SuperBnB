package de.supercode.superbnb.services;


import de.supercode.superbnb.dtos.auth.AuthDTO;
import de.supercode.superbnb.dtos.auth.AuthRegDTO;
import de.supercode.superbnb.entities.Address;
import de.supercode.superbnb.entities.person.User;
import de.supercode.superbnb.repositorys.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private SecurityContextRepository securityContextRepository;

    public AuthentificationService(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, SecurityContextRepository securityContextRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.securityContextRepository = securityContextRepository;
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


    public void login(AuthDTO dto, HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password()));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        securityContextRepository.saveContext(context, request, response);
    }
}
