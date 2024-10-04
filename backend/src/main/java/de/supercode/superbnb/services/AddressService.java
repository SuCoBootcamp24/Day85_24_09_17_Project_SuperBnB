package de.supercode.superbnb.services;

import de.supercode.superbnb.dtos.user.UserUpdateRequestDTO;
import de.supercode.superbnb.entities.Address;
import de.supercode.superbnb.repositorys.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Optional<Address> getAddressById(long id) {
        return addressRepository.findById(id);
    }

    public Address updateAddress(Optional<Address> existAddress, UserUpdateRequestDTO dto) {
        Address updatedAddress = existAddress.orElseGet(() -> new Address());
        if (!dto.street().isBlank()) updatedAddress.setStreet(dto.street());
        if (!dto.houseNumber().isBlank())updatedAddress.setHouseNumber(dto.houseNumber());
        if (!dto.city().isBlank())updatedAddress.setCity(dto.city());
        if (!dto.zipCode().isBlank())updatedAddress.setZipCode(dto.zipCode());
        if (!dto.country().isBlank())updatedAddress.setCountry(dto.country());
        addressRepository.save(updatedAddress);
        return updatedAddress;
    }
}
