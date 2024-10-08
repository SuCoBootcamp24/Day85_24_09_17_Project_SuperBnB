package de.supercode.superbnb.services;

import de.supercode.superbnb.dtos.user.UserDetailsResponseDTO;
import de.supercode.superbnb.dtos.user.UserListDTO;
import de.supercode.superbnb.dtos.user.UserShortResponseDTO;
import de.supercode.superbnb.dtos.user.UserUpdateRequestDTO;
import de.supercode.superbnb.entities.Address;
import de.supercode.superbnb.entities.ImagesUser;
import de.supercode.superbnb.entities.person.Payment;
import de.supercode.superbnb.entities.person.Role;
import de.supercode.superbnb.entities.person.User;
import de.supercode.superbnb.mappers.UserMapper;
import de.supercode.superbnb.repositorys.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserMapper userMapper;
    private UserRepository userRepository;

    private AuthentificationService authenticationService;

    private AddressService addressService;

    private PaymentService paymentService;


    public UserService(UserMapper userMapper, UserRepository userRepository, AuthentificationService authenticationService, AddressService addressService, PaymentService paymentService) {
        this.userMapper = userMapper;
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


    public UserShortResponseDTO getUserDetailsByLogin(Authentication authentication) {
        User existUser = getUserByEmail(authentication.getName());
        String token = authenticationService.getToken(authentication);
        return new UserShortResponseDTO(existUser.getId(), existUser.getFirstName(), existUser.getLastName(), existUser.getRole(), token);
    }


    public List<UserListDTO> getUserList(Authentication authentication) {
        if (!authenticationService.hasAdminRights(authentication)) return null;
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

    public boolean updateUser(UserUpdateRequestDTO dto, Authentication authentication) {
        User initiatedUser = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new RuntimeException("initiated User not found"));
        User updatedUser = userRepository.findById(dto.id()).orElseThrow(() -> new RuntimeException("User not found"));

        Optional<Address> updatedAddress = Optional.empty();
        if (updatedUser.getAddress() != null && updatedUser.getAddress().getId() != null) {
            updatedAddress = addressService.getAddressById(updatedUser.getAddress().getId());
        }

        Optional<Payment> updatedPayment = Optional.empty();
        if (updatedUser.getPayment() != null && updatedUser.getPayment().getId() != null) {
            updatedPayment = paymentService.getPaymentById(updatedUser.getPayment().getId());
        }

        if (!authenticationService.hasAdminRights(authentication) && initiatedUser.getId() != dto.id()) return false;

        if (!dto.firstName().isBlank() && !updatedUser.getFirstName().equals(dto.firstName())) updatedUser.setFirstName(dto.firstName());
        if (!dto.lastName().isBlank() && !updatedUser.getLastName().equals(dto.lastName())) updatedUser.setLastName(dto.lastName());
        if (!dto.email().isBlank() && !updatedUser.getEmail().equals(dto.email())) updatedUser.setEmail(dto.email());
        if (!dto.phone().isBlank() && !updatedUser.getPhone().equals(dto.phone())) updatedUser.setPhone(dto.phone());

        //password update
        if (!dto.password().isBlank()) authenticationService.changePassword(updatedUser, dto.password());

        //address update
        if (!dto.street().isBlank() || !dto.houseNumber().isBlank() || !dto.city().isBlank() || !dto.zipCode().isBlank() || !dto.country().isBlank()) updatedUser.setAddress(addressService.updateAddress(updatedAddress, dto));
        //payment update
        if (!dto.cardNumber().isBlank() ||!dto.cvv().isBlank() || dto.expirationDate() != null) updatedUser.setPayment(paymentService.updatePayment(updatedPayment, dto));

        //Roles update
        if(dto.role() != null && !updatedUser.getRole().equals(dto.role())) {
            if (authenticationService.hasAdminRights(authentication)) {
                updatedUser.setRole(dto.role());
            }
        }

        userRepository.save(updatedUser);
        return true;
    }

    public UserDetailsResponseDTO getUserDetails(long id, String adminEmail) {
        if (!getUserByEmail(adminEmail).getRole().equals(Role.ADMIN) && getUserByEmail(adminEmail).getId() != id) throw new RuntimeException("You are not a Administrator");
        return userMapper.toDTO(getUserById(id));
    }

    public boolean deleteUserById(long id, String adminEmail) {
        User initiatedUser = getUserByEmail(adminEmail);
       // if (!initiatedUser.getRole().equals(Role.ADMIN)) throw new RuntimeException("You are not a Administrator");
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
                userRepository.delete(user.get());
                return true;
        }
        else throw new RuntimeException("user was not found");
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addOrChangeProfileImages(long id, ImagesUser img) {
        User user = getUserById(id);
        user.setProfileImage(img);
        userRepository.save(user);
    }
}
