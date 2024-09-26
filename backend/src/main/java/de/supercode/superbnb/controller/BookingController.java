package de.supercode.superbnb.controller;

import de.supercode.superbnb.dtos.booking.BookingAdminListDTO;
import de.supercode.superbnb.dtos.booking.BookingListByUserResponseDTO;
import de.supercode.superbnb.dtos.booking.BookingRequestDTO;
import de.supercode.superbnb.dtos.booking.BookingResponseDTO;
import de.supercode.superbnb.services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }





    //GET /api/bookings: Liste aller Buchungen anzeigen (nur für Administratoren)
    @GetMapping("list")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<BookingAdminListDTO>> getAllBookings(Authentication authentication) {
        return ResponseEntity.ok(bookingService.getAllBookings(authentication));
    }



    //POST /api/bookings: Eine Ferienwohnung buchen
    @PostMapping
    public ResponseEntity<BookingResponseDTO> propertyBooking(@RequestBody BookingRequestDTO bookingRequest, Authentication authentication) {
        return ResponseEntity.ok(bookingService.propertyBooking(bookingRequest, authentication));
    }

    //GET /api/bookings/userbooking: Liste von buchung des users anzeigen (später über Cookies)
    @GetMapping("userbooking")
    public ResponseEntity<List<BookingListByUserResponseDTO>> getUserBookings(Authentication authentication) {
        return ResponseEntity.ok(bookingService.getUserBookings(authentication));
    }

    //DELETE /api/bookings/{id}: Eine Buchung stornieren
    @DeleteMapping("{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        if(bookingService.deleteBooking(id)) return ResponseEntity.ok().build();
        else return ResponseEntity.notFound().build();
    }



}
