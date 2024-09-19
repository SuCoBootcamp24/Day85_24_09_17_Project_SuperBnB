package de.supercode.superbnb.repositorys;

import de.supercode.superbnb.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {

}
