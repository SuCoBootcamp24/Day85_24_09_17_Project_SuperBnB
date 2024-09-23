package de.supercode.superbnb.repositorys;

import de.supercode.superbnb.dtos.favorite.FavoriteListDTO;
import de.supercode.superbnb.entities.Favorite;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {


    Optional<Favorite> findByUserId(long id);
}

