package de.supercode.superbnb.repositorys;

import de.supercode.superbnb.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
