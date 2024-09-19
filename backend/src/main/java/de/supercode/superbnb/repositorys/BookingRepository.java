package de.supercode.superbnb.repositorys;

import de.supercode.superbnb.dtos.BookingResponseDTO;
import de.supercode.superbnb.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<BookingResponseDTO> findAllByUserId(Long id);

}
