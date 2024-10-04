package de.supercode.superbnb.repositorys;

import de.supercode.superbnb.entities.person.Favorite;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {


    Optional<Favorite> findByUserId(long id);
}

