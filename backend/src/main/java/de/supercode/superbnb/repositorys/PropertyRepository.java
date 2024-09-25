package de.supercode.superbnb.repositorys;

import de.supercode.superbnb.entities.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("SELECT p FROM Property p WHERE " +
            "(p.address.city = :city OR :city IS NULL) AND " +
            "(p.address.country = :country OR :country IS NULL) AND " +
            "p.guestsCapacity >= :guests AND " +
            "p.available = true AND " +
            "NOT EXISTS (SELECT b FROM Booking b WHERE b.property = p AND " +
            "(b.checkInDate <= :checkOut AND b.checkOutDate >= :checkIn))")
    Page<Property> findAvailableProperties(@Param("city") String city,
                                           @Param("country") String country,
                                           @Param("checkIn") LocalDate checkIn,
                                           @Param("checkOut") LocalDate checkOut,
                                           @Param("guests") Integer guests,
                                           Pageable pageable);
}
