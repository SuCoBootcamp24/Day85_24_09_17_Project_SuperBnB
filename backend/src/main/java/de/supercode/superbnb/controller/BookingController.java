package de.supercode.superbnb.controller;

import de.supercode.superbnb.dtos.BookingListByUserResponseDTO;
import de.supercode.superbnb.dtos.BookingRequestDTO;
import de.supercode.superbnb.dtos.BookingResponseDTO;
import de.supercode.superbnb.services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //GET /api/bookings: Liste aller Buchungen anzeigen (nur für Administratoren)



    //POST /api/bookings: Eine Ferienwohnung buchen
    @PostMapping
    public ResponseEntity<BookingResponseDTO> propertyBooking(@RequestBody BookingRequestDTO bookingRequest) {
        return ResponseEntity.ok(bookingService.propertyBooking(bookingRequest));
    }

    //GET /api/bookings/{id}: Liste von buchung des users anzeigen (später über Cookies)
    @GetMapping("{id}")
    public ResponseEntity<List<BookingListByUserResponseDTO>> getUserBookings(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getUserBookings(id));
    }

    //DELETE /api/bookings/{id}: Eine Buchung stornieren
    @DeleteMapping("{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        if(bookingService.deleteBooking(id)) return ResponseEntity.ok().build();
        else return ResponseEntity.notFound().build();
    }



}
