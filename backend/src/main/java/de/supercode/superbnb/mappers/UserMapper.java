package de.supercode.superbnb.mappers;

import de.supercode.superbnb.dtos.address.AddressDetailsDTO;
import de.supercode.superbnb.dtos.payment.PaymentResponseDTO;
import de.supercode.superbnb.dtos.user.UserDetailsResponseDTO;
import de.supercode.superbnb.entities.Address;
import de.supercode.superbnb.entities.Payment;
import de.supercode.superbnb.entities.person.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public UserDetailsResponseDTO toDTO(User user) {

        Address address = user.getAddress();

        Payment payment = user.getPayment();

        return new UserDetailsResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                address == null? null : new AddressDetailsDTO(address.getId(), address.getStreet(), address.getHouseNumber(), address.getCity(), address.getZipCode(), address.getCountry()),
                payment == null? null : new PaymentResponseDTO(payment.getCardNumber(), payment.getCvv(), payment.getExpirationDate()),
                user.getRole()
        );
    }
}
