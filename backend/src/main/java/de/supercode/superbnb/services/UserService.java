package de.supercode.superbnb.services;

import de.supercode.superbnb.dtos.user.UserListDTO;
import de.supercode.superbnb.dtos.user.UserResponseDTO;
import de.supercode.superbnb.dtos.user.UserUpdateRequestDTO;
import de.supercode.superbnb.entities.Address;
import de.supercode.superbnb.entities.Payment;
import de.supercode.superbnb.entities.person.Role;
import de.supercode.superbnb.entities.person.User;
import de.supercode.superbnb.repositorys.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private AuthentificationService authenticationService;

    private AddressService addressService;

    private PaymentService paymentService;


    public UserService(UserRepository userRepository, AuthentificationService authenticationService, AddressService addressService, PaymentService paymentService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
        this.addressService = addressService;
        this.paymentService = paymentService;
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }


    public UserResponseDTO getUserDetails(String email) {
        User existUser = getUserByEmail(email);
        return new UserResponseDTO(existUser.getId(), existUser.getFirstName(), existUser.getLastName(), existUser.getRole());
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

    public boolean updateUser(UserUpdateRequestDTO dto, String adminDetails) {
        User initiatedUser = userRepository.findByEmail(adminDetails).orElseThrow(() -> new RuntimeException("initiated User not found"));
        User updatedUser = userRepository.findById(dto.id()).orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Address> updatedAddress = Optional.empty();
        if (updatedUser.getAddress() != null && updatedUser.getAddress().getId() != null) {
            updatedAddress = addressService.getAddressById(updatedUser.getAddress().getId());
        }

        Optional<Payment> updatedPayment = Optional.empty();
        if (updatedUser.getPayment() != null && updatedUser.getPayment().getId() != null) {
            updatedPayment = paymentService.getPaymentById(updatedUser.getPayment().getId());
        }

        if (!initiatedUser.getRole().equals(Role.ADMIN) || initiatedUser.getId() != dto.id()) throw new RuntimeException("You are not a Administrator!");

        if (!dto.firstName().isBlank() && updatedUser.getFirstName().equals(dto.firstName())) updatedUser.setFirstName(dto.firstName());
        if (!dto.lastName().isBlank() && updatedUser.getLastName().equals(dto.lastName())) updatedUser.setLastName(dto.lastName());
        if (!dto.email().isBlank() && updatedUser.getEmail().equals(dto.email())) updatedUser.setEmail(dto.email());

        authenticationService.changePassword(updatedUser, dto.password());

        //address update
        if (!dto.street().isBlank() || !dto.houseNumber().isBlank() || !dto.city().isBlank() || !dto.zipCode().isBlank() || !dto.country().isBlank()) updatedUser.setAddress(addressService.updateAddress(updatedAddress, dto));
        //payment update
        if (!dto.cardNumber().isBlank() ||!dto.cvv().isBlank() || dto.expirationDate() != null) updatedUser.setPayment(paymentService.updatePayment(updatedPayment, dto));

        //Roles update
        if (initiatedUser.getRole().equals(Role.ADMIN) && initiatedUser.getId() != updatedUser.getId() && !updatedUser.getRole().equals(dto.role())) {
            updatedUser.setRole(dto.role());
        }

        userRepository.save(updatedUser);
        return true;
    }
}
