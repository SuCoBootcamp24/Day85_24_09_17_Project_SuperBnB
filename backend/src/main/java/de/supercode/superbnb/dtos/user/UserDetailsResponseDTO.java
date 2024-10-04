package de.supercode.superbnb.dtos.user;

import de.supercode.superbnb.dtos.address.AddressDetailsDTO;
import de.supercode.superbnb.dtos.payment.PaymentResponseDTO;
import de.supercode.superbnb.entities.person.Role;

public record UserDetailsResponseDTO(
        long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        AddressDetailsDTO address,
        PaymentResponseDTO payment,
        Role role
) {
}
