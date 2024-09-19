package de.supercode.superbnb.repositorys;

import de.supercode.superbnb.entities.person.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
