package de.supercode.superbnb.repositorys;

import de.supercode.superbnb.entities.person.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin,Long> {
}
